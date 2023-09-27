package temptest;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Vin lan
 * @className gg
 * @description
 * @createTime 2021-06-18  14:54
 **/
public class gg {

    @Test
    public void test1() {
        BigDecimal num = BigDecimal.ZERO;
//        System.out.println(num.add(null));
        A a = new A();
        System.out.println(a.getA());
    }

    @Test
    public void test2() {
        String s = new String("<p>我發現「肉毒桿菌」Botox 經巳無孔不入，不再僅是整容醫生除皺紋和瘦面的法寶了。 <br>家務助理菲妮最近?牙骹痛，頰面科的專家醫生說﹕「假如服食放鬆肌肉和消炎藥再不見效，就更要注射Botox，讓肌肉鬆弛，柔軟啦。哦，若要真打此一針，是不是要左右兩面兼顧，以免變成鴛鴦面呢?<p> <p>最近看到醫學新知，說Botox 還可以有瘦腿、止汗和治療半邊臉痙攣的助效。 <br>而最近看到和扮靚情報，又說美國，尢其是紐約市那一群要靚唔要命的靚太和OL，為減少穿高跟鞋時的腳痛，寧可在趾上打Botox，雖然花費折合數千港元，但可仿效「色慾都市」電視劇中的女主角姬莉，足登四吋半，甚至五吋高鞋到處跑，還是值得的，高跟鞋確可增添女人味和自信心，難怪、難怪。<p> <p>美國人用Botox ，一如我們用化品般普遍，百貨公司甚至有專櫃販賣。醫生將它注射入肌肉或皮層之下，微微隆起，皺紋使見消失，用得最多似的位置是額頭、眉心和眼尾。有人甚至將它注入嘴唇，讓它變厚、看來增添性感呢。<p><p>亦有人在出席重大宴會前或見工前，都去弄它幾針，裝修門面。不過聽過羅省一椿真人真事，有一中年女士特意打Botox，才去見工。資方雖好，卻未被取錄，原因是嫌她表情生硬，唔識笑喎。<p> <p>這是Botox的缺點一，缺點二是三個月至半年，效果便褪。缺點三是用得過量，效力會減，而且可能引致吞嚥和呼吸困難等副作用。<p><p>所以，天然物總是較好的。要?春常駐，除睡眠足，營養好，心境開朗之外，也可以輔以補充品。「樂本．健」代理的加拿大至尊名牌，便有男女可用的﹕<a href='/condition_details/supplements/WN0800009.aspx' class='greenText-Bold'>「大豆異黃酮」Soy Isoflavone Complex </a>及<a href='/condition_details/supplements/WN0800005.aspx' class='greenText-Bold'>「左旋精胺酸」L-Arginine</a>。外塗用的<a href='/condition_details/beauty/HHC0800001.aspx' class='greenText-Bold'>「白茶精華美顏乳霜」Anti-aging Cream</a>天然美肌護膚品則可以滋潤成熟肌膚，喚醒細胞更新。要知詳細資料，可上網址﹕www.healthsmart.com.hk。<p>");
        System.out.println(s.length());
        String s1 = "CASH\n" +
                "CREDITCARD\n" +
                "WECHAT\n" +
                "COD\n" +
                "1MC\n" +
                "5D5C\n" +
                "5D5BS\n" +
                "TT\n" +
                "PREPAY\n" +
                "CBD\n" +
                "BANKIN\n" +
                "PPS\n" +
                "PPSPAID\n" +
                "EPS\n" +
                "MISC\n" +
                "UNIONPAY\n" +
                "APPLE\n" +
                "BOC\n" +
                "GOOGLE\n" +
                "JCB\n" +
                "SAMSUNG\n" +
                "ALI\n" +
                "UNIONAPP\n" +
                "FPS\n" +
                "WALI\n" +
                "WALIHK\n" +
                "WCC\n" +
                "WWECHAT\n" +
                "WUNIONPAY\n" +
                "WPPS\n" +
                "CHAIN\n" +
                "CQ";
        String s2 = s1.replaceAll("\n", ",");
        System.out.println(Arrays.stream(s2.split(",")).collect(Collectors.joining(",", "'", "'")));
    }
}

class A {
    private  BigDecimal a;

    public BigDecimal getA() {
        return a;
    }

    public void setA(BigDecimal a) {
        this.a = a;
    }
}