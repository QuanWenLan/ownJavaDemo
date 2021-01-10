package com.ett.jsoup;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.LocationTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Vin lan
 * @className Test
 * @description TODO 测试网页数据采集
 * @createTime 2021-01-05  12:09
 **/
public class GetSFAddressTest {
    public static void main(String[] args) {
        StringBuilder insertSql = new StringBuilder("INSERT INTO `local_pickup` (`pickup_id`, `area`, `address`, `pickup_type`, `type_name`)");
        insertSql.append(" VALUES \n");
        /*String sfBusinessStationAddress = getSFBusinessStationAddress("https://htm.sf-express.com/hk/tc/dynamic_function/S.F.Network/SF_business_station_address/", insertSql, SF_BUSINESS_STATION_ADDRESS);
        System.out.println(sfBusinessStationAddress + ";");
        System.out.println("==========================");
        StringBuilder insertSql2 = new StringBuilder("INSERT INTO `local_pickup` (`pickup_id`, `area`, `address`, `pickup_type`, `type_name`)");
        insertSql2.append(" VALUES \n");
        String sfStoreAddress = getSFBusinessStationAddress("https://htm.sf-express.com/hk/tc/dynamic_function/S.F.Network/SF_store_address/", insertSql2, SF_STORE_ADDRESS);
        System.out.println(sfStoreAddress + ";");
        System.out.println("==========================");
        StringBuilder insertSql3 = new StringBuilder("INSERT INTO `local_pickup` (`pickup_id`, `area`, `address`, `pickup_type`, `type_name`)");
        insertSql3.append(" VALUES \n");
        String sfETLockerAddress = getSFBusinessStationAddress("https://htm.sf-express.com/hk/tc/dynamic_function/S.F.Network/EF-Locker/", insertSql3, SF_ET_LOCKER);
        System.out.println(sfETLockerAddress + ";");*/
        // https://htm.sf-express.com/hk/tc/dynamic_function/S.F.Network/SF_service_partner_address/
        getSFServiceAddress("https://htm.sf-express.com/hk/tc/dynamic_function/S.F.Network/SF_service_partner_address/", insertSql);
    }

    private static final String SF_BUSINESS_STATION_ADDRESS = "BUSINESS";
    private static final String SF_STORE_ADDRESS = "STORE";
    private static final String SF_ET_LOCKER = "ET";
    private static final String SF_SERVICE_PARTNER_ADDRESS = "SERVICE";

    private static String getPickupTypeName(String pickupType, String whichAddress) {
        switch (whichAddress) {
            case SF_BUSINESS_STATION_ADDRESS:
                return getSfBusinessStationPickupTypeName(pickupType);
            case SF_STORE_ADDRESS:
                return getSfBusinessStorePickupTypeName(pickupType);
            case SF_ET_LOCKER:
                return getSfEtLockerPickupTypeName(pickupType);
            case SF_SERVICE_PARTNER_ADDRESS:
                return getSfEtLockerPickupTypeName(pickupType);
            default:
                return "";
        }
    }

    private static String getSfBusinessStationPickupTypeName(String pickupType) {
        if ("香港島".equals(pickupType)) {
            return "SFHK_BUS";
        } else if ("九龍".equals(pickupType)) {
            return "SFKW_BUS";
        } else if ("新界".equals(pickupType)) {
            return "SFNT_BUS";
        } else if ("澳門".equals(pickupType)) {
            return "SFMO_BUS";
        }
        return "";
    }

    private static String getSfBusinessStorePickupTypeName(String pickupType) {
        if ("香港島".equals(pickupType)) {
            return "SFHK";
        } else if ("九龍".equals(pickupType)) {
            return "SFKW";
        } else if ("新界".equals(pickupType)) {
            return "SFNT";
        } else if ("澳門".equals(pickupType)) {
            return "SFMO";
        }
        return "";
    }

    private static String getSfEtLockerPickupTypeName(String pickupType) {
        if ("香港島".equals(pickupType)) {
            return "SFHK_LOCKER";
        } else if ("九龍".equals(pickupType)) {
            return "SFKW_LOCKER";
        } else if ("新界".equals(pickupType)) {
            return "SFNT_LOCKER";
        } else if ("澳門".equals(pickupType)) {
            return "SFMO_LOCKER";
        }
        return "";
    }

    private static String getSFBusinessStationAddress(String url, StringBuilder insertSql, String whichAddress) {
        try {
            Connection connect = Jsoup.connect(url);
            Document document = connect.get();

            Elements newsDetail = document.getElementsByClass("news-detail");
            String typeName = newsDetail.select(".news-title").first().text();
            System.out.println(typeName);

            Elements divElements = document.getElementsByClass("news-content content-editor");
            for (Element div : divElements) {
                Elements allChildrenElements = div.children();
                String pickupType = "";
                for (Element content : allChildrenElements) {
                    if (Objects.equals("h3", content.tagName())) {
                        pickupType = content.text();
                    }
                    if ("".equals(pickupType) && Objects.equals("p", content.tagName())) {
                        pickupType = content.text();
                    }
                    String insertPickupType = getPickupTypeName(pickupType, whichAddress);
                    if (Objects.equals("table", content.tagName()) || content.is(".tableHeaderData")) {
                        Elements tbodyElement = content.getElementsByTag("tbody");
                        Elements trElements = tbodyElement.first().children();

                        // loop first-second tr to get the table header
                        String rowspanStr = trElements.first().children().first().attr("rowspan");
                        int headerRowSpan = 0;
                        if (!"".equals(rowspanStr)) {
                            headerRowSpan = Integer.parseInt(rowspanStr);
                        }

                        StringBuilder businessHoursWork = new StringBuilder();
                        StringBuilder businessHoursRest = new StringBuilder();
                        {
                            Element tr;
                            if (headerRowSpan == 0) { // no rowspan
                                tr = trElements.get(0);
                            } else {  // have rowspan
                                tr = trElements.get(headerRowSpan = headerRowSpan - 1);
                            }

                            String str1 = tr.children().last().text();
                            String str2 = tr.children().last().previousElementSibling().text();
                            if (whichAddress.equals(SF_ET_LOCKER)) {
                                if (str1.contains("（") && str1.contains("）"))
                                    str1 = str1.substring(str1.indexOf("（") + 1, str1.indexOf("）"));
                                if (str2.contains("（") && str2.contains("）"))
                                    str2 = str2.substring(str2.indexOf("（") + 1, str2.indexOf("）"));
                                businessHoursWork.append(str2).append(": ");
                                businessHoursRest.append(str1).append(": ");
                            } else {
                                businessHoursWork.append(str2).append(": ");
                                businessHoursRest.append(str1).append(": ");
                            }
                        }

                        List<String> contentList = new ArrayList<>(trElements.size());
                        // loop left tr to get the table content
                        final int len = trElements.size();
                        int tempRowspanIndex = len, shortOne = 0;
                        Element rowspanTd = null;
                        boolean nextShortColumnRow = false;
                        for (int i = headerRowSpan + 1; i < tempRowspanIndex; i++) {
                            Element trElement = trElements.get(i);
                            // loop td
                            Elements tdOrThElements = trElement.children();
                            // get the first td,whether have rowspan, if it is,the row crossed will be one column short
                            Element firstTd = tdOrThElements.first();
                            if (firstTd.hasAttr("rowspan")) {
                                int tdRowspan = Integer.parseInt(firstTd.attr("rowspan"));
                                tempRowspanIndex = i + tdRowspan;
                                nextShortColumnRow = true;
                                rowspanTd = firstTd;
                            }

                            if (!nextShortColumnRow) rowspanTd = firstTd;

                            String area = rowspanTd.text();
                            if (tdOrThElements.size() >= 5) {
                                String pickupId = tdOrThElements.get(1 - shortOne).text();
                                String address = whichAddress.equals(SF_ET_LOCKER) ? tdOrThElements.get(2 - shortOne).text() :
                                        tdOrThElements.get(3 - shortOne).text();
                                String businessHoursWorkStr = tdOrThElements.get(4 - shortOne).text();
                                String businessHoursRestStr = tdOrThElements.get(5 - shortOne).text();
                                String addressAndBusinessHours = address + "(" + businessHoursWork + businessHoursWorkStr + " " + businessHoursRest + businessHoursRestStr + ")";

                                contentList.add(pickupId);
                                contentList.add(area);
                                contentList.add(addressAndBusinessHours);
                                contentList.add(insertPickupType);
                                contentList.add(typeName);

                                String contentStr = contentList.stream().map(s -> "'" + s + "'").collect(Collectors.joining(",", "", ""));
                                insertSql.append("(");
                                insertSql.append(contentStr);
                                insertSql.append("), ");
                                insertSql.append("\n");
                                contentList.clear();
                            }

                            if (i == tempRowspanIndex - 1) {
                                nextShortColumnRow = false;
                                tempRowspanIndex = len;
                            }

                            if (nextShortColumnRow) {
                                shortOne = 1;
                            } else {
                                shortOne = 0;
                            }

                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return insertSql.append(";").substring(0, insertSql.lastIndexOf(","));
    }

    private static String getSFServiceAddress(String url, StringBuilder insertSql) {
        try {
            Connection connect = Jsoup.connect(url);
            Document document = connect.get();

            Elements newsDetail = document.getElementsByClass("news-detail");
            String typeName = newsDetail.select(".news-title").first().text();
            System.out.println(typeName);

            Elements divElements = document.getElementsByClass("news-content content-editor");
            for (Element div : divElements) {
                Elements allChildrenElements = div.children();
                String pickupType = "";
                for (Element content : allChildrenElements) {
                    if (Objects.equals("p", content.tagName()) && content.children().size() == 0) {
                        pickupType = content.text();
                    } else {
                        Element a = content.children().first();
                        if (a.hasAttr("href")) {
                            String href = a.attr("href");
                            StringBuilder hrefSB = new StringBuilder(href);
                            if("http".equals(href.substring(0, hrefSB.indexOf(":")))) {
                                hrefSB.insert(hrefSB.indexOf(":"), "s");
                            }
                            System.out.println(hrefSB);
                            URL pdfUrl = new URL(hrefSB.toString());
                            PdfReader reader = new PdfReader(pdfUrl);
                            StringBuffer buff = new StringBuffer();
                            PdfReaderContentParser parser = new PdfReaderContentParser(reader);
                            int num = reader.getNumberOfPages();
                            TextExtractionStrategy strategy;
                            for (int i = 1; i <= num; i++) {
                                strategy = parser.processContent(i, new LocationTextExtractionStrategy());
                                buff.append(strategy.getResultantText());
                            }
                            System.out.println(buff);
                        }
                        System.out.println(pickupType);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
