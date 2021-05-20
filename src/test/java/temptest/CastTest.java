package temptest;

import org.junit.Test;

/**
 * @author Vin lan
 * @className CastTest
 * @description
 * @createTime 2021-04-21  14:06
 **/
public class CastTest {

    @Test
    public void test() {
/*        Object[] a = new Object[2];F
        a[0] = null;
        a[1] = 1;
        Integer b = (Integer) a[0];
        System.out.println(b);*/

        String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);

        String s3 = new String("1")  + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);
    }
}
