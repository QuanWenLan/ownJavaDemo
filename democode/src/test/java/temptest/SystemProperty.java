package temptest;

import org.junit.Test;

import java.util.Enumeration;
import java.util.Properties;

/**
 * @author Vin lan
 * @className SystemProperty
 * @description
 * @createTime 2021-11-15  14:34
 **/
public class SystemProperty {

    @Test
    public void getAllSystemProperties() {
        Properties properties = System.getProperties();

        Enumeration<?> enumeration = properties.propertyNames();
        while (enumeration.hasMoreElements()) {
            Object o = enumeration.nextElement();
            System.out.println("name: " + o.toString() + ", value:" + properties.get(o));
        }
        Runtime runtime = Runtime.getRuntime();
        System.out.println("可用 CPU 核心数：" + runtime.availableProcessors());
        System.out.println("最大内存数量:" + runtime.maxMemory());
    }
}
