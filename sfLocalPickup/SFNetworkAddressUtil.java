package com.lanwq.util;

import com.etwealth.mo.server.erp.bean.LocalPickup;
import com.etwealth.mo.server.erp.response.LocalPickupAddressResponse;
import com.etwealth.mo.server.erp.response.LocalPickupPdfResponse;
import com.etwealth.mo.server.erp.service.PublicWebService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author Vin lan
 * @className SFNetworkAddress
 * @description TODO get the S.F.Express Network Address
 * @createTime 2021-01-07  11:46
 **/
public class SFNetworkAddressUtil {
    private static Logger log = LoggerFactory.getLogger(SFNetworkAddressUtil.class);
    private static SFNetworkAddressUtil sfNetworkAddressUtil = null;

    public static final String SF_BUSINESS_STATION_ADDRESS = "BUSINESS";
    public static final String SF_STORE_ADDRESS = "STORE";
    public static final String SF_ET_LOCKER = "ET";
    public static final String SF_SERVICE_PARTNER_ADDRESS = "SERVICE";

    public static void main(String[] args) throws IOException {
        SFNetworkAddressUtil instance = SFNetworkAddressUtil.getInstance();
        LocalPickupAddressResponse address = instance.getSFServicePartnerAddress("https://htm.sf-express.com/hk/tc/dynamic_function/S.F.Network/SF_service_partner_address/", SF_SERVICE_PARTNER_ADDRESS);
        File f = new File("insert.txt");
        if (!f.exists()) {
            f.createNewFile();
        }
        BufferedWriter output = new BufferedWriter(new FileWriter(f));
        for (String s : address.getSfServicePartnerStringList()) {
            output.write(s);
        }
        output.close();
    }

    private String getPickupType(String pickupType, String whichAddress) {
        switch (whichAddress) {
            case SF_BUSINESS_STATION_ADDRESS:
                return getSfBusinessStationPickupType(pickupType);
            case SF_STORE_ADDRESS:
            case SF_SERVICE_PARTNER_ADDRESS:
                return getSfBusinessStoreOrServicePickupType(pickupType);
            case SF_ET_LOCKER:
                return getSfEtLockerPickupType(pickupType);
            default:
                return "";
        }
    }

    /**
     * SF_business_station_address
     */
    private String getSfBusinessStationPickupType(String pickupType) {
        if (LocalPickupPdfUtils.HK.equals(pickupType)) {
            return "SFHK_BUS";
        } else if (LocalPickupPdfUtils.KW.equals(pickupType)) {
            return "SFKW_BUS";
        } else if (LocalPickupPdfUtils.NT.equals(pickupType)) {
            return "SFNT_BUS";
        } else if (LocalPickupPdfUtils.MO.equals(pickupType)) {
            return "SFMO_BUS";
        }
        return "";
    }

    /**
     * SF_store_address or SF_service_partner_address's prefix
     */
    private String getSfBusinessStoreOrServicePickupType(String pickupType) {
        if (LocalPickupPdfUtils.HK.equals(pickupType)) {
            return "SFHK";
        } else if (LocalPickupPdfUtils.KW.equals(pickupType)) {
            return "SFKW";
        } else if (LocalPickupPdfUtils.NT.equals(pickupType) || LocalPickupPdfUtils.IS_LANDS.equals(pickupType)
                || LocalPickupPdfUtils.NTLie.equals(pickupType)) {
            return "SFNT";
        } else if (LocalPickupPdfUtils.MO.equals(pickupType)) {
            return "SFMO";
        }
        return "";
    }

    /**
     * EF-Locker
     */
    private String getSfEtLockerPickupType(String pickupType) {
        if (LocalPickupPdfUtils.HK.equals(pickupType)) {
            return "SFHK_LOCKER";
        } else if (LocalPickupPdfUtils.KW.equals(pickupType)) {
            return "SFKW_LOCKER";
        } else if (LocalPickupPdfUtils.NT.equals(pickupType)) {
            return "SFNT_LOCKER";
        } else if (LocalPickupPdfUtils.MO.equals(pickupType)) {
            return "SFMO_LOCKER";
        }
        return "";
    }

    /**
     * SF_service_partner_address's suffix
     */
    private String getSfServicePickupTypeSuffix(String suffixStr) {
        String typeNameSuffix = "";
        if (suffixStr.contains("7-11")) {
            typeNameSuffix = "711";
        } else if (suffixStr.contains("OK")) {
            typeNameSuffix = "OK";
        } else if (suffixStr.contains("Shell")) {
            typeNameSuffix = "SHELL";
        } else {
            typeNameSuffix = "SMALL";
        }
        return typeNameSuffix;
    }


    private String getArea(String containAreaStr) {
        if (containAreaStr.contains(LocalPickupPdfUtils.HK)) {
            return LocalPickupPdfUtils.HK;
        } else if (containAreaStr.contains(LocalPickupPdfUtils.KW)) {
            return LocalPickupPdfUtils.KW;
        } else if (containAreaStr.contains(LocalPickupPdfUtils.NT)) {
            return LocalPickupPdfUtils.NT;
        } else if (containAreaStr.contains(LocalPickupPdfUtils.IS_LANDS)) {
            return LocalPickupPdfUtils.IS_LANDS;
        } else if (containAreaStr.contains(LocalPickupPdfUtils.MO)) {
            return LocalPickupPdfUtils.MO;
        } else {
            return "";
        }
    }

    private static final Pattern PICKUP_ID_PATTERN = Pattern.compile("[\\dA-Z]{4,}");

    public LocalPickupAddressResponse getSFBusinessStationAddress(String url, StringBuilder insertSql, String whichAddress) {
        LocalPickupAddressResponse pickupAddressResponse = new LocalPickupAddressResponse();
        List<String> ids = pickupAddressResponse.getIds();
        String exceptionPickUpId = "", exceptionRow = "";
        try {
            Document document = null;
            try {
                Connection connect = Jsoup.connect(url);
                document = connect.get();
            } catch (Exception e) {
                log.error("{} get SF address occurs error: {}, type: {}", this.getClass().getSimpleName(), url, whichAddress);
                pickupAddressResponse.setCode(LocalPickupAddressResponse.UpdateCode.Error);
                pickupAddressResponse.getMissingData().add(url + " : " + whichAddress);
                pickupAddressResponse.setReturnMsg(e.getMessage() + String.format("\n get url connection error: %s, type: %s", url, whichAddress));
                return pickupAddressResponse;
            }

            assert document != null;
            Elements newsDetail = document.getElementsByClass("news-detail");
            String typeName = LocalPickupPdfUtils.getProperty(whichAddress);
            Elements divElements = document.getElementsByClass("news-content content-editor");
            for (Element div : divElements) {
                Elements allChildrenElements = div.children();
                String pickupType = "";
                for (Element content : allChildrenElements) {
                    if (Objects.equals("h3", content.tagName()) || Objects.equals("p", content.tagName())) {
                        pickupType = "".equals(content.text()) ? pickupType : content.text();
                    }

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
                            // no rowspan
                            if (headerRowSpan == 0) {
                                tr = trElements.get(0);
                            } else {  // have rowspan
                                tr = trElements.get(headerRowSpan = headerRowSpan - 1);
                            }

                            String str1 = tr.children().last().text();
                            String str2 = tr.children().last().previousElementSibling().text();
                            if (whichAddress.equals(SF_ET_LOCKER)) {
                                if (str1.contains("（") && str1.contains("）")) {
                                    str1 = str1.substring(str1.indexOf("（") + 1, str1.indexOf("）"));
                                }
                                if (str2.contains("（") && str2.contains("）")) {
                                    str2 = str2.substring(str2.indexOf("（") + 1, str2.indexOf("）"));
                                }
                                businessHoursWork.append(str2).append(": ");
                                businessHoursRest.append(str1).append(": ");
                            } else {
                                businessHoursWork.append(str2).append(": ");
                                businessHoursRest.append(str1).append(": ");
                            }
                        }

                        // loop left tr to get the table content
                        final int len = trElements.size();
                        int tempRowspanIndex = len, shortOne = 0;
                        Element rowspanTd = null;
                        boolean nextShortColumnRow = false;
                        for (int i = headerRowSpan + 1; i < tempRowspanIndex; i++) {
                            Element trElement = trElements.get(i);
                            // loop td
                            Elements tdOrThElements = trElement.children();
                            exceptionRow = tdOrThElements.toString();
                            // get the first td,whether have rowspan, if it is,the row crossed will be one column short
                            Element firstTd = tdOrThElements.first();
                            if (tdOrThElements.size() >= 5 || tdOrThElements.size() == 1) {
                                if (firstTd.hasAttr("rowspan")) {
                                    int tdRowspan = Integer.parseInt(firstTd.attr("rowspan"));
                                    tempRowspanIndex = i + tdRowspan;
                                    nextShortColumnRow = true;
                                    rowspanTd = firstTd;
                                }
                            }

                            // for only one row record
                            if (!nextShortColumnRow) {
                                rowspanTd = firstTd;
                            }

                            String area = rowspanTd.text();
                            if (tdOrThElements.size() >= 4) {
                                String pickupId = exceptionPickUpId = tdOrThElements.get(1 - shortOne).text();
                                if (!nextShortColumnRow && tdOrThElements.size() == 5 && !PICKUP_ID_PATTERN.matcher(pickupId).find()) {
                                    log.info("{} get SF address, but some data that didn't fit the parsing requirements, not suitable data is : {}", this.getClass().getSimpleName(), pickupId);
                                    pickupAddressResponse.getMissingData().add(pickupId);
                                    pickupAddressResponse.setCode(LocalPickupAddressResponse.UpdateCode.SkipPart);
                                    pickupAddressResponse.setReturnMsg("Skipped some data that didn't fit the parsing requirements");
                                    continue;
                                }
                                String address = tdOrThElements.get(tdOrThElements.size() - 3).text();
                                String businessHoursWorkStr = tdOrThElements.get(tdOrThElements.size() - 2).text();
                                String businessHoursRestStr = tdOrThElements.get(tdOrThElements.size() - 1).text();
                                String addressAndBusinessHours = address + "(" + businessHoursWork + businessHoursWorkStr + " " + businessHoursRest + businessHoursRestStr + ")";

                                LocalPickup localPickup = new LocalPickup();
                                localPickup.setLocalPickupId(pickupId);
                                localPickup.setArea(area);
                                localPickup.setAddress(addressAndBusinessHours);
                                localPickup.setPickupType(getPickupType(pickupType, whichAddress));
                                localPickup.setTypeName(typeName);
                                ids.add(pickupId);

                                insertSql.append("(");
                                insertSql.append(convertToInsertString(localPickup));
                                insertSql.append("), ");
                                insertSql.append("\n");
                            }

                            // the td row crossed loop ends
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
                        log.info("complete parse url address: {}, and local pickup type:{}", url, pickupType);
                    }
                }
            }
        } catch (Exception e) {
            log.error("{} get SF address occurs exception, wrong row pickup ID:{}, \n exception Row: {}", this.getClass().getSimpleName(), exceptionPickUpId, exceptionRow);
            pickupAddressResponse.setCode(LocalPickupAddressResponse.UpdateCode.Error);
            pickupAddressResponse.setReturnMsg(e.getMessage() + String.format("\n wrong row pickup ID:%s, \n exception Row: %s", exceptionPickUpId, exceptionRow));
            e.printStackTrace();
        }
        pickupAddressResponse.setInsertSql(insertSql.toString().substring(0, insertSql.lastIndexOf(",")) + ";");
        return pickupAddressResponse;
    }

    public LocalPickupAddressResponse getSFServicePartnerAddress(String url, String whichAddress) {
        String exceptionPdfUrl = "", exceptionTypeName = "";
        LocalPickupAddressResponse pickupAddressResponse = new LocalPickupAddressResponse();
        List<String> ids = pickupAddressResponse.getIds();
        // May greater than the maximum string length
        List<String> resultStrList = new ArrayList<>();
        try {
            Document document = null;
            try {
                Connection connect = Jsoup.connect(url);
                document = connect.get();
            } catch (Exception e) {
                log.error("{} get SF address occurs error: {}, type: {}", this.getClass().getSimpleName(), url, whichAddress);
                pickupAddressResponse.setCode(LocalPickupAddressResponse.UpdateCode.Error);
                pickupAddressResponse.getMissingData().add(url + " : " + whichAddress);
                pickupAddressResponse.setReturnMsg(e.getMessage() + String.format("\n get url connection error:%s, type: %s", url, whichAddress));
                return pickupAddressResponse;
            }
            assert document != null;
            Elements newsDetail = document.getElementsByClass("news-detail");
            String allTypeName = newsDetail.select(".news-title").first().text();

            Elements divElements = document.getElementsByClass("news-content content-editor");
            for (Element div : divElements) {
                Elements allChildrenElements = div.children();
                String pickupType = "";
                for (Element content : allChildrenElements) {
                    if (Objects.equals("p", content.tagName()) && content.children().size() == 0) {
                        if (!"".equals(content.text())) pickupType = content.text();
                    } else {
                        Element a = content.children().first();
                        if (!"a".equals(a.tagName())) {
                            a = content.children().first().children().first();
                        }
                        if (a.hasAttr("href")) {
                            StringBuilder tempSbSql = new StringBuilder("INSERT INTO `local_pickup` (`pickup_id`, `area`, `address`, `pickup_type`, `type_name`)");
                            tempSbSql.append(" VALUES \n");
                            String href = a.attr("href");
                            StringBuilder hrefSb = new StringBuilder(href);
                            if ("http".equals(href.substring(0, hrefSb.indexOf(":")))) {
                                hrefSb.insert(hrefSb.indexOf(":"), "s");
                            }
                            String aText = a.text();
                            log.info("content:{}, original href: {}", a.text(), hrefSb);
                            exceptionPdfUrl = hrefSb.toString();
                            String sfOriginalUrl = PublicWebService.getInstance().getSfOriginalUrl();
                            String sfReplaceUrl = PublicWebService.getInstance().getSfReplaceUrl();
                            String pdfUrlStr = hrefSb.toString();
                            if (pdfUrlStr.contains(sfOriginalUrl)) {
                                pdfUrlStr = pdfUrlStr.replaceFirst(sfOriginalUrl, sfReplaceUrl);
                            }
                            log.info("content:{}, after replace url href: {}", a.text(), pdfUrlStr);
                            InputStream pdfInput = null;
                            try {
                                URL pdfUrl = new URL(pdfUrlStr);
                                URLConnection connection = pdfUrl.openConnection();
                                pdfInput = connection.getInputStream();
                            } catch (IOException e) {
                                log.error("get pdf input stream error, msg: {}", e.getMessage());
                                pickupAddressResponse.setReturnMsg(e.getMessage());
                                e.printStackTrace();
                            }

                            Collection<LocalPickup> localPickups;
                            if (href.contains("ASP")) {
                                localPickups = LocalPickupPdfUtils.getInstance().pickupSmallSiteParseForSF(pdfInput, getArea(aText));
                            } else {
                                localPickups = LocalPickupPdfUtils.getInstance().pickupSiteParseForSF(pdfInput, getArea(aText));
                            }
                            try {
                                if (pdfInput != null) {
                                    pdfInput.close();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            for (LocalPickup pickup : localPickups) {
                                if (aText.contains("-")) {
                                    aText = aText.substring(aText.indexOf("-"));
                                }
                                pickup.setPickupType(getPickupType(getArea(aText), whichAddress) + "_" + getSfServicePickupTypeSuffix(pickupType));
                                String typeName;
                                if (pickupType.contains(".") || pickupType.contains(":")) {
                                    typeName = pickupType.substring(pickupType.indexOf(".") + 1, pickupType.indexOf(":")).trim();
                                } else {
                                    typeName = pickupType;
                                }
                                if (typeName.contains(LocalPickupPdfUtils.getProperty("SMALL"))) {
                                    typeName = LocalPickupPdfUtils.getProperty("SMALL");
                                }
                                exceptionTypeName = typeName;
                                pickup.setTypeName(typeName);
                                tempSbSql.append("(");
                                tempSbSql.append(convertToInsertString(pickup));
                                tempSbSql.append("), ");
                                tempSbSql.append("\n");
                                ids.add(pickup.getLocalPickupId());
                            }
                            resultStrList.add(tempSbSql.toString().substring(0, tempSbSql.lastIndexOf(",")) + ";\n");
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("{} get SF address occurs exception, parse pdf error url: {}, type: {}", this.getClass().getSimpleName(), exceptionPdfUrl, exceptionTypeName);
            pickupAddressResponse.setCode(LocalPickupAddressResponse.UpdateCode.Error);
            pickupAddressResponse.getMissingData().add(exceptionTypeName + " : " + exceptionPdfUrl);
            pickupAddressResponse.setReturnMsg(e.getMessage() + String.format("\n parse pdf error:%s, type: %s", exceptionPdfUrl, exceptionTypeName));
            e.printStackTrace();
        }
        pickupAddressResponse.setSfServicePartnerStringList(resultStrList);
        return pickupAddressResponse;
    }

    public LocalPickupPdfResponse getSfServicePartnerAddressByPdf(String aText, String pickupType, String pdfUrlStr, String whichAddress) {
        log.info("begin parse pdf url, type: {}, url : {}", aText, pdfUrlStr);
        LocalPickupPdfResponse response = new LocalPickupPdfResponse();

        String sfOriginalUrl = PublicWebService.getInstance().getSfOriginalUrl();
        String sfReplaceUrl = PublicWebService.getInstance().getSfReplaceUrl();
        String afterReplaceUrl = "";
        if (pdfUrlStr.contains(sfOriginalUrl)) {
            afterReplaceUrl = pdfUrlStr.replaceFirst(sfOriginalUrl, sfReplaceUrl);
        }

        InputStream pdfInput;
        try {
            URL pdfUrl = new URL(afterReplaceUrl);
            URLConnection connection = pdfUrl.openConnection();
            pdfInput = connection.getInputStream();
        } catch (IOException e) {
            log.error("get pdf input stream error, msg: {}", e.getMessage());
            response.setReturnMsg(e.getMessage());
            e.printStackTrace();
            return  response;
        }

        String exceptionTypeName = "";
        List<String> ids = response.getIds();
        try {
            Collection<LocalPickup> localPickups;
            if (pdfUrlStr.contains("ASP")) {
                localPickups = LocalPickupPdfUtils.getInstance().pickupSmallSiteParseForSF(pdfInput, getArea(aText));
            } else {
                localPickups = LocalPickupPdfUtils.getInstance().pickupSiteParseForSF(pdfInput, getArea(aText));
            }
            pdfInput.close();

            StringBuilder tempSbSql = new StringBuilder("INSERT INTO `local_pickup` (`pickup_id`, `area`, `address`, `pickup_type`, `type_name`)");
            tempSbSql.append(" VALUES \n");
            for (LocalPickup pickup : localPickups) {
                if (aText.contains("-")) {
                    aText = aText.substring(aText.indexOf("-"));
                }
                pickup.setPickupType(getPickupType(getArea(aText), whichAddress) + "_" + getSfServicePickupTypeSuffix(pickupType));
                String typeName;
                if (pickupType.contains(".") || pickupType.contains(":")) {
                    typeName = pickupType.substring(pickupType.indexOf(".") + 1, pickupType.indexOf(":")).trim();
                } else {
                    typeName = pickupType;
                }
                if (typeName.contains(LocalPickupPdfUtils.getProperty("SMALL"))) {
                    typeName = LocalPickupPdfUtils.getProperty("SMALL");
                }
                exceptionTypeName = typeName;
                pickup.setTypeName(typeName);
                tempSbSql.append("(");
                tempSbSql.append(convertToInsertString(pickup));
                tempSbSql.append("), ");
                tempSbSql.append("\n");

                ids.add(pickup.getLocalPickupId());
                response.setInsertSql(tempSbSql.toString().substring(0, tempSbSql.lastIndexOf(",")) + ";\n");
                response.setPickupType(pickup.getPickupType());
                response.setTypeName(typeName);
            }
        } catch (Exception e) {
            log.error("{} get SF address occurs exception, parse pdf error url: {}, type: {}", this.getClass().getSimpleName(), pdfUrlStr, exceptionTypeName);
            response.setCode(LocalPickupAddressResponse.UpdateCode.Error);
            response.setReturnMsg(e.getMessage() + String.format("\n parse pdf error:%s, type: %s", pdfUrlStr, aText));
            response.getErrorPdf().add(aText + " : " + pdfUrlStr.substring(pdfUrlStr.lastIndexOf("/") + 1));
            e.printStackTrace();
        }
        log.info("end parse pdf url, type: {}, url : {}", aText, pdfUrlStr);
        return response;
    }

    public List<LocalPickupPdfResponse> getSfServicePartnerPdfUrlAddress(String url, String whichAddress) {
        Document document;
        try {
            Connection connect = Jsoup.connect(url);
            document = connect.get();
        } catch (Exception e) {
            log.error("{} get SF address occurs error: {}, type: {}", this.getClass().getSimpleName(), url, whichAddress);
            LocalPickupPdfResponse pdfResponse = new LocalPickupPdfResponse();
            pdfResponse.setCode(LocalPickupAddressResponse.UpdateCode.Error);
            pdfResponse.getMissingData().add(url + " : " + whichAddress);
            pdfResponse.setReturnMsg(e.getMessage() + String.format("\n get url connection error:%s, type: %s", url, whichAddress));
            return Collections.singletonList(pdfResponse);
        }

        List<LocalPickupPdfResponse> pdfResponses = new ArrayList<>();
        String exceptionPdfUrl = "", exceptionTypeName = "";
        String downloadUrl = url.substring(0, url.indexOf("/dynamic_function")) + "/download";
        assert document != null;
        Elements newsDetail = document.getElementsByClass("news-detail");
        String allTypeName = newsDetail.select(".news-title").first().text();

        Elements divElements = document.getElementsByClass("news-content content-editor");
        for (Element div : divElements) {
            Elements allChildrenElements = div.children();
            String pickupType = "";
            LocalPickupPdfResponse pdfResponse = null;
            for (Element content : allChildrenElements) {
                try {
                    if (Objects.equals("p", content.tagName()) && content.children().size() == 0) {
                        if (!"".equals(content.text())) {
                            pickupType = content.text();
                            pdfResponse = new LocalPickupPdfResponse();
                            pdfResponse.setPickupType(pickupType);
                            pdfResponses.add(pdfResponse);
                        }
                    } else {
                        Element a = content.children().first();
                        if (!"a".equals(a.tagName())) {
                            a = content.children().first().children().first();
                        }
                        if (a.hasAttr("href")) {
                            String href = a.attr("href");
                            exceptionPdfUrl = href;
                            String aText = a.text();
                            exceptionTypeName = aText;
                            StringBuilder hrefSb = new StringBuilder(href);
                            int colonIndex = hrefSb.indexOf(":");
                            int ellipsisIndex = href.indexOf("src/test");
                            if (colonIndex != -1 && "http".equals(href.substring(0, colonIndex))) {
                                hrefSb.insert(hrefSb.indexOf(":"), "s");
                            } else if (ellipsisIndex != -1) {
                                String pdfName = href.substring(hrefSb.lastIndexOf("/"));
                                hrefSb.replace(0, hrefSb.length(), downloadUrl + pdfName);
                            }
                            exceptionPdfUrl = hrefSb.toString();
                            log.info("content:{}, original href: {}", a.text(), hrefSb);
                            if (pdfResponse != null) {
                                pdfResponse.getaTexts().add(aText);
                                pdfResponse.getPdfUrls().add(exceptionPdfUrl);
                            }
                        }
                    }
                } catch (Exception e) {
                    log.error("{} get SF address occurs exception, parse html content error, url: {}, type: {}", this.getClass().getSimpleName(), exceptionPdfUrl, exceptionTypeName);
                    if (pdfResponse != null) {
                        pdfResponse.setCode(LocalPickupAddressResponse.UpdateCode.Error);
                        pdfResponse.setReturnMsg(e.getMessage() + String.format("\n parse html content error:%s, type: %s", exceptionPdfUrl, exceptionTypeName));
                        pdfResponse.getErrorPdf().add(exceptionTypeName + " : " + exceptionPdfUrl.substring(exceptionPdfUrl.lastIndexOf("/") + 1));
                    }
                    e.printStackTrace();
                }
            }
        }
        return pdfResponses;
    }

    private String convertToInsertString(LocalPickup bean) {
        return "'" + bean.getLocalPickupId().replaceAll("'", "") + "', "
                + "'" + bean.getArea().replaceAll("'", "") + "', "
                + "'" + bean.getAddress().replaceAll("'", "") + "', "
                + "'" + bean.getPickupType().replaceAll("'", "") + "', "
                + "'" + bean.getTypeName().replaceAll("'", "") + "'";
    }

    public static SFNetworkAddressUtil getInstance() {
        if (sfNetworkAddressUtil == null) {
            sfNetworkAddressUtil = new SFNetworkAddressUtil();
        }
        return sfNetworkAddressUtil;
    }
}
