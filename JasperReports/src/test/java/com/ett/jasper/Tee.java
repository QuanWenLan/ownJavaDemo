package com.ett.jasper;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vin lan
 * @className Tee
 * @description
 * @createTime 2022-01-25  18:01
 **/
public class Tee {
    @Test
    public void test4() {
        List<Map<String, String>> records = new ArrayList<Map<String, String>>();
        for (int i = 1; i < 10; i++) {
            Map<String, String> columns = new HashMap<>();
            for (int j = 1; j <= 10; j++) {
// The HashMap Key must save with ColumnProperty Name
                columns.put("Column" + j, "Record " + i + " Column " + j + " data.");
            }
            records.add(columns);
        }
        for (int i = 0; i < records.size(); i++) {
            System.out.println(records.get(i));
            System.out.println("=====");
        }
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(records);
    }
}
