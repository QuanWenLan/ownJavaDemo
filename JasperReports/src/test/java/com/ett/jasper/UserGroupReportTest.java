package com.ett.jasper;

import com.ett.jasper.po.usergroup.UserGroup;
import com.ett.jasper.repository.CityRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Vin lan
 * @className UserGroupReportTest
 * @description TODO
 * @createTime 2020-09-10  15:26
 **/
public class UserGroupReportTest {

    @Test
    public void testGroupReport() {
        try {
            ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-*.xml");
            applicationContext.start();

            CityRepository cityRepository = (CityRepository) applicationContext.getBean("cityRepository");

            // 获取jrxml文件
            ClassPathResource jrxmlResource = new ClassPathResource("userGroupListReport/template.jrxml");
            // 编译成JasperReport
            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlResource.getInputStream());
            // 创建MAP数据源
            Map<String, Object> parameters = new HashMap<>(16);
            parameters.put("companyName", "Kim Eng Securities (Hong Kong) Limited");
            parameters.put("title", "User Group Listing");
            parameters.put("fromScope", "BSS_Admin");
            parameters.put("toScope", "Super User");
            // 获取resource bundles
            String path = this.getClass().getResource("/userGroupListReport").getPath();
            String path2 = this.getClass().getClassLoader().getResource("userGroupListReport").getPath();
            String localeBasePath = "userGroupListReport.locale";
            ResourceBundle resourceBundle = ResourceBundle.getBundle(localeBasePath, Locale.US);

            parameters.put(JRParameter.REPORT_LOCALE, Locale.US);
            parameters.put(JRParameter.REPORT_RESOURCE_BUNDLE, resourceBundle);

            // 创建list数据源
            List userListsByGroup = cityRepository.getUserListsByGroup(parameters);
            List<UserGroup> userGroups = new ArrayList<>(userListsByGroup.size());
            for (Object u : userListsByGroup) {
                Object[] oo = (Object[]) u;
                userGroups.add(new UserGroup((String) oo[0], (String) oo[1], (String) oo[2], (String) oo[3], (Date) oo[4]));
            }

            generateReport("D:/HTML/userGroup.pdf", parameters, userGroups, "userGroupListReport/template.jrxml", "");
        } catch (JRException | IOException e) {
            e.printStackTrace();
        }


    }

    // utility method of generate report
    public void generateReport(String destFileName, Map<String, Object> map, List list, String mainJrxmlPath, String subJrxmlPath) {
        try {
            ClassPathResource mainJrxmlResource = null;
            ClassPathResource subJrxmlResource = null;
            if (!"".equals(mainJrxmlPath)) {
                // 获取jrxml文件
                mainJrxmlResource = new ClassPathResource(mainJrxmlPath);
                subJrxmlResource = new ClassPathResource(subJrxmlPath);
            }
            Assert.assertNotNull(mainJrxmlResource);
//            Assert.assertNotNull(subJrxmlResource);
            // 编译成JasperReport
            JasperReport mainJasperReport = JasperCompileManager.compileReport(mainJrxmlResource.getInputStream());

            String toFile = JasperCompileManager.compileReportToFile(subJrxmlPath);

            JasperPrint jasperPrint = JasperFillManager.fillReport(mainJasperReport, map, new JRBeanCollectionDataSource(list));
            JasperExportManager.exportReportToPdfFile(jasperPrint, destFileName);
        } catch (JRException | IOException e) {
            e.printStackTrace();
        }
    }


}
