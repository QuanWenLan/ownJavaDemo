package com.lanwq.other;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Vin lan
 * @className EscapeReg
 * @description 转义和正则
 * @createTime 2021-03-19  17:09
 **/
public class EscapeReg {
    public static void main(String[] args) {
/*        testEscape();
        String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);

        String s3 = new String("1")  + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);
        String s1 = "1";
        String s2 = "2";
        System.out.println(s1 + s2);*/
        String ss = "abc";
        String ss2 = "abc";
        System.out.println(ss.hashCode() + "," + ss2.hashCode());
        System.out.println(ss == ss2);
        ss = "ab";
        System.out.println(ss.hashCode() + "," + ss2.hashCode());
        System.out.println(ss == ss2);
        System.out.println("<a href='https://www.healthsmart.com.hk/product_details.php?item=WN0800063'>".replaceFirst("https://www.healthsmart.com.hk/product_details.php\\?item=WN0800063", "a"));
//        testHref();
    }

    private static final Pattern PICKUP_ID_PATTERN = Pattern.compile("[\\dA-Z]*");
    private static final Pattern p2 = Pattern.compile("^#\\d+");

    /**
     * 测试转义
     */
    private static void testEscape() {
        System.out.println("\\");
        System.out.println("\\\\");
        String testStr = "Room 501, Wing Tuch Commercial Centre,\n" +
                "177-183 Wing Lok Street,\n" +
                "Sheung Wan\\/";
        String afterReplace = testStr.replaceAll("\\n|%n", " ");
        System.out.println(afterReplace);
        // 正则表达式里面会再进行转义一次
        String s = afterReplace.replaceAll("[\\\\/]", " bbb");
        System.out.println(s);

        System.out.println("正则： " + PICKUP_ID_PATTERN.matcher("852M").find());
        System.out.println(p2.matcher("#4").matches());
    }

    private static Pattern HREF_PATTERN = Pattern.compile("(https://www.healthsmart.com.hk/product_details.php\\?){1}");

    // http://192.168.2.35:9002/portal/#!/link/productDetail/WN-07477?categoryType1=exclusive&categoryType2=webbernaturals
    private static void testHref() {
        String constantStr = "營養師提醒烹<a href='https://www.healthsmart.com.hk/product_details.php?item=WN0800063'><font color='purple'>維柏健webber naturals[特強紫錐花]</font></a>，被譽為傷風剋星，能對抗鼻水、鼻塞、喉嚨痛，舒緩氣管不適及鼻敏癥狀；天然草本成分，不含藥性。產品詳情<a href='https://www.healthsmart.com.hk/product_details.php?item=WN0800063'>按此</a>。</p><p><img src='https://www.healthsmart.com.hk/site_share/WN0800063_S.jpg'></p><br>守護健康由日常做起，<a href='https://www.healthsmart.com.hk/product_details.php?lv1=exclusive&lv2=webbernaturals&item=WN0800031'><font color='purple'>維柏健webber naturals[活養細胞茄紅素]</font></a>，被譽為「頑症剋星」，是關注細胞異變的保健首選，有效抵禦病毒入侵，保護及修復細胞DNA，對抗頑症，阻延擴散，減低復發，維持咽喉、食道、胃部、腸道及前列健康。產品詳情<a href='https://www.healthsmart.com.hk/product_details.php?lv1=exclusive&lv2=webbernaturals&item=WN0800031'>按此</a>。</p><p><img src='https://www.healthsmart.com.hk/site_share/WN0800031_S.jpg'></p><br><p><a href=' https://www.healthsmart.com.hk/product_details.php?item=WN0800121'><font color='purple'>維柏健webber naturals[健骨維他命D3 (1000IU)]</font></a>，有助增強抵抗力，加強鈣質吸收，強健骨骼及牙齒，維持神經系統正常運作。每日1粒，容易吞嚥，特別適合飲食不均、抵抗力差、缺少運動、少接觸太陽、吸煙、飲酒、愛喝咖啡等人士。產品詳情<a href=' https://www.healthsmart.com.hk/product_details.php?item=WN0800121'>按此</a>。<p><img src='https://www.healthsmart.com.hk/site_share/WN0800121_S.jpg'></p>";
        String s = "營養師提醒烹<a href='https://www.healthsmart.com.hk/product_details.php?item=WN0800063'><font color='purple'>維柏健webber naturals[特強紫錐花]</font></a>，被譽為傷風剋星，能對抗鼻水、鼻塞、喉嚨痛，舒緩氣管不適及鼻敏癥狀；天然草本成分，不含藥性。產品詳情<a href='https://www.healthsmart.com.hk/product_details.php?item=WN0800063'>按此</a>。</p><p><img src='https://www.healthsmart.com.hk/site_share/WN0800063_S.jpg'></p><br>守護健康由日常做起，<a href='https://www.healthsmart.com.hk/product_details.php?lv1=exclusive&lv2=webbernaturals&item=WN0800031'><font color='purple'>維柏健webber naturals[活養細胞茄紅素]</font></a>，被譽為「頑症剋星」，是關注細胞異變的保健首選，有效抵禦病毒入侵，保護及修復細胞DNA，對抗頑症，阻延擴散，減低復發，維持咽喉、食道、胃部、腸道及前列健康。產品詳情<a href='https://www.healthsmart.com.hk/product_details.php?lv1=exclusive&lv2=webbernaturals&item=WN0800031'>按此</a>。</p><p><img src='https://www.healthsmart.com.hk/site_share/WN0800031_S.jpg'></p><br><p><a href=' https://www.healthsmart.com.hk/product_details.php?item=WN0800121'><font color='purple'>維柏健webber naturals[健骨維他命D3 (1000IU)]</font></a>，有助增強抵抗力，加強鈣質吸收，強健骨骼及牙齒，維持神經系統正常運作。每日1粒，容易吞嚥，特別適合飲食不均、抵抗力差、缺少運動、少接觸太陽、吸煙、飲酒、愛喝咖啡等人士。產品詳情<a href=' https://www.healthsmart.com.hk/product_details.php?item=WN0800121'>按此</a>。<p><img src='https://www.healthsmart.com.hk/site_share/WN0800121_S.jpg'></p>";
        Matcher matcher = HREF_PATTERN.matcher(s);
        int matcherStart = 0;
        while (matcher.find(matcherStart)) {
            String matchedStr = matcher.group(1);
            System.out.println("==> " + matchedStr);
            matcherStart = matcher.end();
            int suitableIndex = s.substring(matcherStart).indexOf("'");
            int endIndex = matcherStart + suitableIndex;
            String paramHref = s.substring(matcherStart, endIndex);
            System.out.println("原来的参数列表链接：" + paramHref);
            StringBuilder needBuildParameters = new StringBuilder();
            String[] parameters = paramHref.split("&");
            String productId = "";
            if (parameters.length > 0) {
                for (int i = 0; i < parameters.length; i++) {
                    String singleParam = parameters[i];
                    String[] concreteParam = singleParam.split("=");
                    if (concreteParam.length > 0) {
                        if ("lv1".equals(concreteParam[0])) {
                            needBuildParameters.append("categoryType1=").append(concreteParam[1]);
                        } else if ("lv2".equals(concreteParam[0])) {
                            needBuildParameters.append("categoryType2=").append(concreteParam[1]);
                        } else if ("lv3".equals(concreteParam[0])) {
                            needBuildParameters.append("categoryType3=").append(concreteParam[1]);
                        } else if ("item".equals(concreteParam[0])) {
                            productId = concreteParam[1];
                        }
                    }
                    if (i != parameters.length - 1) {
                        needBuildParameters.append("&");
                    }
                }
            }
            if (needBuildParameters.length() > 0) {
                needBuildParameters = new StringBuilder("?" + needBuildParameters);
            }
            System.out.println("构建的参数列表：" + needBuildParameters.toString());
            System.out.println("原先链接：" + matchedStr + paramHref);
            System.out.println("现在链接：" + "http://192.168.2.35:9002/portal/#!/link/productDetail/" + productId + needBuildParameters);
            String replaceMatchedStr = matchedStr.replace("?", "\\?") + paramHref;
            System.out.println("替换后正则：" + replaceMatchedStr);
            constantStr = constantStr.replaceFirst(replaceMatchedStr, "http://192.168.2.35:9002/portal/#!/link/productDetail/" + productId + needBuildParameters);
            System.out.println("s->>>" + s);
            matcherStart = endIndex;
            System.out.println("结束一轮");
        }

        // https://www.cnblogs.com/ElEGenT/p/13158108.html
        /*String txt = "abc123<root>这是root1</root><root>这是root2</root>";
        Matcher matcher2 = Pattern.compile("<root>(.*?)</root>").matcher(txt);
        int matcher_start = 0;
        while (matcher.find(matcher_start)){
            System.out.println(matcher.group(1));
            matcher_start = matcher.end();
        }*/
    }
}
