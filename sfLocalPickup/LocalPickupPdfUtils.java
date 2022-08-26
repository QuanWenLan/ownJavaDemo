package com.lanwq.util;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LocalPickupPdfUtils {

    private final String HK_AREA_CODE_KEY = "852";
    private final String MAYBE_WRONG_HK_AREA_CODE_KEY = "825";
    private final String MARCO_AREA_CODE_KEY = "853";

    private static List<String> SF_HK_Area_Name_List;
    private static List<String> SF_KLN_Area_Name_List;
    private static List<String> SF_ISLAND_Area_Name_List;
    private static List<String> SF_NT_Area_Name_List;
    private static List<String> SF_MARCO_Area_Name_List;

    public static String HK;
    public static String KW;
    public static String IS_LANDS;
    public static String NT;
    public static String NTLie;
    public static String MO;

    private final static String PROPERTIES_PATH = "com/etwealth/mo/server/localPickupInfo.properties";
    public static Properties properties = new Properties();

    static {
        try {
            properties.load(new InputStreamReader(Objects.requireNonNull(LocalPickupPdfUtils.class.getClassLoader().getResourceAsStream(PROPERTIES_PATH)), StandardCharsets.UTF_8));

            String hkNameListPro = properties.getProperty("area.HK.nameList");
            String kwNameListPro = properties.getProperty("area.KW.nameList");
            String isLandsNameListPro = properties.getProperty("area.isLands.nameList");
            String ntNameListPro = properties.getProperty("area.NT.nameList");
            String moNameListPro = properties.getProperty("area.MO.nameList");

            SF_HK_Area_Name_List = Arrays.stream(hkNameListPro.contains(",") ? hkNameListPro.split(",") : new String[]{hkNameListPro}).collect(Collectors.toList());
            SF_KLN_Area_Name_List = Arrays.stream(kwNameListPro.contains(",") ? kwNameListPro.split(",") : new String[]{kwNameListPro}).collect(Collectors.toList());
            SF_ISLAND_Area_Name_List = Arrays.stream(isLandsNameListPro.contains(",") ? isLandsNameListPro.split(",") : new String[]{isLandsNameListPro}).collect(Collectors.toList());
            SF_NT_Area_Name_List = Arrays.stream(ntNameListPro.contains(",") ? ntNameListPro.split(",") : new String[]{ntNameListPro}).collect(Collectors.toList());
            SF_MARCO_Area_Name_List = Arrays.stream(moNameListPro.contains(",") ? moNameListPro.split(",") : new String[]{moNameListPro}).collect(Collectors.toList());

            HK = properties.getProperty("area.HK");
            KW = properties.getProperty("area.KW");
            IS_LANDS = properties.getProperty("area.isLands");
            NT = properties.getProperty("area.NT");
            NTLie = properties.getProperty("area.NTLie");
            MO = properties.getProperty("area.MO");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String propertyKey) {
        return properties.getProperty(propertyKey);
    }

    public static void main(String[] args) {
        System.out.println("load");
    }

    private HashMap<String, List<String>> sf_area_map = new HashMap<>();

    private static LocalPickupPdfUtils localPickupPdfUtils = null;

    private LocalPickupPdfUtils() {
        sf_area_map.put(HK, SF_HK_Area_Name_List);
        sf_area_map.put(KW, SF_KLN_Area_Name_List);
        sf_area_map.put(IS_LANDS, SF_ISLAND_Area_Name_List);
        sf_area_map.put(NT, SF_NT_Area_Name_List);
        sf_area_map.put(MO, SF_MARCO_Area_Name_List);
    }

    public static LocalPickupPdfUtils getInstance() {
        if (localPickupPdfUtils == null) {
            localPickupPdfUtils = new LocalPickupPdfUtils();
        }
        return localPickupPdfUtils;
    }

    public Collection<LocalPickup> pickupSiteParseForSF(InputStream input, String zone) throws Exception {

        List<String> areaNameList;
        if (sf_area_map.containsKey(zone)) {
            areaNameList = sf_area_map.get(zone);
        } else {
            return null;
        }
        HashMap<Integer, LocalPickup> map = new HashMap<>();

        String pdfFileInText = getContentFromPdfInputStream(input);

        String[] lines = pdfFileInText.split(System.getProperty("line.separator"));

        int areaStartLine = 0, areaEndLine = 0, dataLineIndex = 1, index = 0;
        String area = "";

        for (String line : lines) {
            //if the line which contain HK_AREA_CODE_KEY or MARCO_AREA_CODE_KEY is a valid line
            if (line.contains(HK_AREA_CODE_KEY) || line.contains(MAYBE_WRONG_HK_AREA_CODE_KEY) || line.contains(MARCO_AREA_CODE_KEY)) {

                //mark each area start line index
                if (areaStartLine == 0) {
                    areaStartLine = dataLineIndex;
                }

                String previousLine = "", nextLine = "";
                if (index + 1 < lines.length) {
                    previousLine = lines[index - 1];
                    nextLine = lines[index + 1];
                }

                // it means line is a time valid line
                boolean timeValid = false;
                if (checkDateForLine(line)) {
                    timeValid = true;
                }

                String[] field = line.split(" ");
                int n = 0;
                for (int i = 0; i < field.length; i++) {
                    if (!field[i].equals("")) {
                        field[n++] = field[i];
                    }
                }

                if (line.startsWith(HK_AREA_CODE_KEY) || line.startsWith(MAYBE_WRONG_HK_AREA_CODE_KEY) || line.startsWith(MARCO_AREA_CODE_KEY)) {
                    String code = field[0];
                    StringBuilder address = new StringBuilder(field[1]);

                    //if address contain the space then more than Normal_Column_Len_Without_Area column will be found,need to add other address split by space
                    final int normal_Column_Len_Without_Area = 4;
                    for (int j = 0; j <= field.length - normal_Column_Len_Without_Area; j++) {
                        String tempField = field[2 + j];
                        if (!checkDateForLine(tempField)) {
                            address.append(tempField);
                        }
                    }

                    // If the address column have more than one row,it will get the previous row and next row,and get the complete address
                    if (address.toString().contains(24 + hour)) {
                        address.append(previousLine);
                        address.append(nextLine);
                    }
                    address = new StringBuilder(address.toString()
                            .replaceAll("(\\d{2}( )?" + hour + "?)*", "")
                            .replaceAll("／*", "")
                            .replaceAll("(\\d{2}:\\d{2}[-–]?)*", ""));

                    String date = lines[4];
                    date = date.substring(1, date.length() - 1);
                    address.append("(").append(date).append(":");

                    if (timeValid) {
                        String s = getString(line);
                        if ("852BD1004".equals(code)) { // Do a special treatment for a "852BD1004" if res is not complete time
                            address.append(previousLine).append(" ");
                            address.append(nextLine).append(" ");
                        }
                        address.append(s);
                    } else {
                        address.append(previousLine).append(" ");
                        address.append(nextLine);
                    }
                    address.append(")");

                    LocalPickup localPickup = new LocalPickup();
                    localPickup.setLocalPickupId(code);
                    localPickup.setArea(area);
                    localPickup.setAddress(address.toString());
                    map.put(dataLineIndex, localPickup);
                } else {
                    area = field[0];
                    String code = field[1];
                    StringBuilder address = new StringBuilder(field[2]);

                    //if address contain the space then more than Normal_Column_Len_With_Area column will be found, need to add other address split by space
                    final int normal_Column_Len_With_Area = 5;
                    for (int j = 0; j <= field.length - normal_Column_Len_With_Area; j++) {
                        String tempField = field[3 + j];
                        if (!checkDateForLine(tempField)) {
                            address.append(tempField);
                        }
                    }

                    address = new StringBuilder(address.toString().replaceAll("(\\d{2}( )?" + hour + "?)*", "")
                            .replaceAll("／*", "")
                            .replaceAll("(\\d{2}:\\d{2}[-–]?)*", ""));

                    String date = lines[4];
                    date = date.substring(1, date.length() - 1);
                    address.append("(").append(date).append(":");

                    if (timeValid) {
                        String s = getString(line);
                        if ("852BD1004".equals(code)) { // Do a special treatment for a "852BD1004"
                            address.append(previousLine).append(" ");
                            address.append(nextLine).append(" ");
                        }
                        address.append(s);
                    } else {
                        address.append(previousLine).append(" ");
                        address.append(nextLine);
                    }
                    address.append(")");


                    if (areaEndLine == 0) {
                        if (properties.getProperty("area.oddNumber").contains(code)) { // area name is not middle align of each data area
                            areaEndLine = (dataLineIndex - areaStartLine) * 2 + areaStartLine + 1;
                        } else { // area name is middle align of each data area
                            areaEndLine = (dataLineIndex - areaStartLine) * 2 + areaStartLine;
                        }
                    }

                    LocalPickup localPickup = new LocalPickup();
                    localPickup.setLocalPickupId(code);
                    localPickup.setArea(area);
                    localPickup.setAddress(address.toString());
                    map.put(dataLineIndex, localPickup);
                }

                // update area
                if (areaStartLine != 0 && areaEndLine != 0 && areaEndLine == dataLineIndex) {
                    for (int j = areaStartLine; j <= areaEndLine; j++) {
                        map.get(j).setArea(area);
                    }
                    //reset
                    areaStartLine = 0;
                    areaEndLine = 0;
                }

                dataLineIndex++;
            } else {
                Iterator<String> ite = areaNameList.iterator();
                while (ite.hasNext()) {
                    String areaName = ite.next();
                    if (line.startsWith(areaName)) {
                        area = areaName;
                        if (areaEndLine == 0) {
                            areaEndLine = (dataLineIndex - areaStartLine) * 2 + areaStartLine - 1;
                            if (area.equals(properties.getProperty("area.yuenLong"))) {
                                areaEndLine += 9;
                            } else if (area.equals(properties.getProperty("area.TS"))) {
                                areaEndLine += 4;
                            }
                        }
                    }

                }
            }
            index++;
        }
        return map.values();

    }

    // for service pattern address
    private String hour = properties.getProperty("pattern.hour");
    private String week = properties.getProperty("pattern.week");
    private String weekDays = properties.getProperty("pattern.weekDays");
    private String rangeTo = properties.getProperty("pattern.rangeTo");
    private String rangeAnd = properties.getProperty("pattern.rangeAnd");
    private String publicHoliday = properties.getProperty("pattern.publicHoliday");
    private String publicHolidayRest = properties.getProperty("pattern.publicHolidayRest");
    private String rest = properties.getProperty("pattern.rest");
    private String pickup = properties.getProperty("pickup");
    private String send = properties.getProperty("send");
    private String localSend = properties.getProperty("localSend");
    private String volume = properties.getProperty("volume");
    private String weight = properties.getProperty("weight");

    /**
     * 08:00-20:00 or 8:00 -- 9:00
     */
    private final Pattern datePatternDigital = Pattern.compile("(\\d{1,2}:\\d{2}( )?([-–])( )?\\d{1,2}:\\d{2})+");
    /**
     * 0800-2000
     */
    private final Pattern datePatternDigital2 = Pattern.compile("(\\d{4}-\\d{4})+");
    /**
     * ([（(])?(星期[一二三四五六日])+[星期一二三四五六日及至、]*(公眾假期休息|公眾假期)?([)）])?(: |：| )?((\d{1,2}:\d{2}([-–])\d{1,2}:\d{2})|[休息])*
     */
    private final Pattern datePatternChinese = Pattern.compile("([（(])?(" + week + "[" + weekDays + "])+[" + week + weekDays + rangeAnd + rangeTo + "、]*(" + publicHolidayRest + "|" + publicHoliday + ")?([)）])?" +
            "(: |：| )?((\\d{1,2}:\\d{2}([-–])\\d{1,2}:\\d{2})|[" + rest + "])*");

    public Collection<LocalPickup> pickupSmallSiteParseForSF(InputStream input, String zone) throws Exception {

        List<String> areaNameList;
        if (sf_area_map.containsKey(zone)) {
            areaNameList = sf_area_map.get(zone);
        } else {
            return null;
        }

        HashMap<Integer, LocalPickup> map = new HashMap<>();
        String pdfFileInText = getContentFromPdfInputStream(input);
        String[] lines = pdfFileInText.split(System.getProperty("line.separator"));

        int areaStartLine = 0, areaEndLine = 0, dataLineIndex = 1, index = 0;
        String area = "";
        Pattern patternCodeKey = Pattern.compile("\\w{8,9}");
        for (String line : lines) {

            //if the line which contain HK_AREA_CODE_KEY or MARCO_AREA_CODE_KEY is a valid line
            if (line.contains(HK_AREA_CODE_KEY) || line.contains(MAYBE_WRONG_HK_AREA_CODE_KEY) || line.contains(MARCO_AREA_CODE_KEY)) {

                //mark each area start line index
                if (areaStartLine == 0) {
                    areaStartLine = dataLineIndex;
                }
                // Get the previous and next lines of the current line, add date column to the address
                String previousLine = "", nextLine = "";
                if (index + 1 < lines.length) {
                    previousLine = lines[index - 1];
                    nextLine = lines[index + 1];
                }

                String[] field = line.split(" ");
                int n = 0;
                for (int i = 0; i < field.length; i++) {
                    if (!field[i].equals("")) {
                        field[n++] = field[i];
                    }
                }
                area = field[0];
                String code = field[2]; // 852LB3001

                // if the "shop name" column contains a space,need to append them to a complete shop name, below just do a simple treatment
                // it is not a code,check whether to move forward or backward
                if (!(patternCodeKey.matcher(code)).find()) {
                    Matcher matcher = patternCodeKey.matcher(line);
                    if (matcher.find()) {
                        code = line.substring(matcher.start(), matcher.end());
                    }
                    // move forward,it means shop name is null or have a row break
                    if (patternCodeKey.matcher(field[1]).find()) {
                        String previousShopName = previousLine, nextShopName = nextLine;
                        Matcher datePreviousMatcher = datePatternChinese.matcher(previousLine);
                        if (datePreviousMatcher.find()) {
                            previousShopName = previousLine.substring(0, datePreviousMatcher.start());
                        }
                        Matcher dateNextMatcher = datePatternChinese.matcher(nextLine);
                        if (dateNextMatcher.find()) {
                            nextShopName = nextLine.substring(0, dateNextMatcher.start());
                        }
                        field[1] = previousShopName + nextShopName;
                        field[3] = field[2];
                    }
                    // move backward, it means shop name has a space.if it is more than one space, it will be a to do item
                    if (patternCodeKey.matcher(field[3]).find()) {
                        field[3] = field[1] + field[2];
                    }
                }
                StringBuilder address = new StringBuilder(field[1] + " " + field[3]);

                //if address contain the space then more than Normal_Column_Len_With_Area column will be found, need to add other address split by space
                for (int f = 0; f < field.length - 6; f++) {    // 6 is a valid data row for all columns
                    String tempField = field[4 + f];
                    if (checkDateForLine(line)) {
                    } else {
                        address.append(tempField);
                    }
                }
                // If the address column have more than one row(If the address includes pickup, it means there is a line break),it will get the previous row and next row,and get the complete address
                if (address.toString().contains(pickup)) {
                    if (previousLine.contains(week)) {
                        address.append(previousLine.substring(0, previousLine.indexOf(week)).replaceAll(" ", ""));
                    } else if (previousLine.contains(volume)) {
                        address.append(previousLine.substring(0, previousLine.indexOf(volume)).replaceAll(" ", ""));
                    }

                    if (nextLine.contains(week)) {
                        address.append(nextLine.substring(0, nextLine.indexOf(week)).replaceAll(" ", ""));
                    } else if (nextLine.contains(weight)) {
                        address.append(nextLine.substring(0, nextLine.indexOf(weight)).replaceAll(" ", ""));
                    }
                    address = new StringBuilder(address.toString().replaceAll("(" + pickup + "|" + send + "|" + localSend + "|)", "")
                            .replaceAll("(／|/)*", ""));
                }

                String date = lines[4];
                date = date.substring(1, date.length() - 1);
                address.append("(").append(date).append(":");

                // it means line is a time valid line
                boolean timeValid = false;
                if (checkDateForLine(line)) {
                    timeValid = true;
                }

                if (timeValid) {
                    String s = getString(line);
                    if (code.equals("852M3001")) {
                        address.append(getString(previousLine)).append(" ");
                        address.append(getString(nextLine));
                    }
                    address.append(s);
                } else {
                    address.append(getString(previousLine)).append(" ");
                    address.append(getString(nextLine));
                }
                address.append(")");

                LocalPickup localPickup = new LocalPickup();
                localPickup.setLocalPickupId(code);
                localPickup.setArea(area);
                localPickup.setAddress(address.toString());
                map.put(dataLineIndex, localPickup);

                //update area
                if (areaStartLine != 0 && areaEndLine != 0 && areaEndLine == dataLineIndex) {
                    for (int j = areaStartLine; j <= areaEndLine; j++) {
                        map.get(j).setArea(area);
                    }
                    //reset
                    areaStartLine = 0;
                    areaEndLine = 0;
                }

                dataLineIndex++;
            } else {
                Iterator<String> ite = areaNameList.iterator();
                while (ite.hasNext()) {
                    String areaName = ite.next();
                    if (line.startsWith(areaName)) {
                        area = areaName;
                        if (areaEndLine == 0) {
                            areaEndLine = (dataLineIndex - areaStartLine) * 2 + areaStartLine - 1;
                            if (area.equals(properties.getProperty("area.yuenLong"))) {
                                areaEndLine += 9;
                            } else if (area.equals(properties.getProperty("area.TS"))) {
                                areaEndLine += 4;
                            }
                        }
                    }

                }
            }
            index++;
        }
        return map.values();
    }


    private String getString(String line) {
        String res = "";
        Matcher matcher;
        if ((matcher = Pattern.compile("(\\d{2}( )?" + hour + ")").matcher(line)).find()) {
            int start = matcher.start(), end = matcher.end();
            res = line.substring(start, end);
        } else if ((matcher = datePatternDigital.matcher(line)).find() && !Pattern.compile("(" + week + "[" + weekDays + "])").matcher(line).find()) {
            int start = matcher.start(), end = matcher.end();
            res = line.substring(start, end);
        } else if ((matcher = datePatternDigital2.matcher(line)).find() && !Pattern.compile("(" + week + "[" + weekDays + "])").matcher(line).find()) {
            int start = matcher.start(), end = matcher.end();
            res = line.substring(start, end);
        } else if ((matcher = datePatternChinese.matcher(line)).find()) {
            int start = matcher.start(), end = matcher.end();
            res = line.substring(start, end);
        }
        return res;
    }

    private boolean checkDateForLine(String line) {
        return datePatternDigital.matcher(line).find() || datePatternDigital2.matcher(line).find() || datePatternChinese.matcher(line).find() ||
                Pattern.compile("(\\d{2}( )?" + hour + ")").matcher(line).find() || Pattern.compile("(" + week + "[" + weekDays + "])").matcher(line).find();
    }

    public String getContentFromPdfInputStream(InputStream input) throws IOException {
        String res = "";
        PDDocument document = PDDocument.load(input);
        //check if it's a encrypted file.
        if (!document.isEncrypted()) {
            PDFTextStripper tStripper = new PDFTextStripper();
            tStripper.setSortByPosition(true);
            res = tStripper.getText(document);
            document.close();
            return res;
        }
        return res;
    }

    public List<LocalPickup> getDoubleCheckAddress(Collection<LocalPickup> collection) {

        List<LocalPickup> double_check_list = new ArrayList<LocalPickup>();

        collection.forEach(localPickupBean -> {
            if (!localPickupBean.getAddress().contains(localPickupBean.getArea())) {
                double_check_list.add(localPickupBean);
            }
        });
        return double_check_list;
    }
}
