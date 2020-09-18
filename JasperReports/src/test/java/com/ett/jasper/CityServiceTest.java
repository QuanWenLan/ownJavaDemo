package com.ett.jasper;

import com.ett.jasper.po.Student;
import com.ett.jasper.po.Subject;
import com.ett.jasper.po.User;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;


public class CityServiceTest {

    @Test
    public void getAllUser() {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-*.xml");
        applicationContext.start();

        CityService cityService = (CityService) applicationContext.getBean("cityService");

        List<User> allUser = cityService.getAllUser();

        System.out.println(allUser);

        try {
            // 读取文件
            ClassPathResource classPathResource = new ClassPathResource("jasper/demo_user.jrxml");
            InputStream jrxmlInputStream = classPathResource.getInputStream();
            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlInputStream);

            // 输出文件
            OutputStream outputStream = new FileOutputStream(new File("user.pdf"));

            // 填充数据
            JRDataSource dataSource = new JRBeanCollectionDataSource(allUser);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        } catch (IOException | JRException e) {
            e.printStackTrace();
        }

    }

    //子报表
    @Test
    public void testSub() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-*.xml");
        applicationContext.start();
        Map<String, Object> parameters = new HashMap<String, Object>(16);

        //组装list数据源
        Subject sub1 = new Subject("语文", "张老师", 80.0);
        Subject sub2 = new Subject("数学", "王老师", 90.0);
        Subject sub3 = new Subject("物理", "孙老师", 46.0);
        Subject sub4 = new Subject("政治", "李老师", 50.0);
        List<Student> list = new ArrayList<Student>();

        Student stu1 = new Student();
        stu1.setStuNo(101);
        stu1.setStuName("小明");
        List<Subject> sublist1 = new ArrayList<Subject>();
        sublist1.add(sub1);
        sublist1.add(sub2);
        stu1.setSubjectList(sublist1);

        Student stu2 = new Student();
        stu2.setStuNo(102);
        stu2.setStuName("小鱼");
        List<Subject> sublist2 = new ArrayList<Subject>();
        sublist2.add(sub2);
        sublist2.add(sub3);
        sublist2.add(sub4);
        stu2.setSubjectList(sublist2);

        Student stu3 = new Student();
        stu3.setStuNo(103);
        stu3.setStuName("小东");
        List<Subject> sublist3 = new ArrayList<Subject>();
        sublist3.add(sub4);
        sublist3.add(sub3);
        stu3.setSubjectList(sublist3);

        list.add(stu1);
        list.add(stu2);
        list.add(stu3);

        try {
            InputStream mainJrxmlIs = new ClassPathResource("jasper/DemoReport6_main.jrxml").getInputStream();
            InputStream subJrxmlIs = new ClassPathResource("jasper/DemoReport6_sub.jrxml").getInputStream();

            JasperDesign mainDesign = JRXmlLoader.load(mainJrxmlIs);
            JasperDesign subDesign = JRXmlLoader.load(subJrxmlIs);
            // 当编译的文件夹不存在的时候，这里并不会进行文件夹的创建
            /* //1:创建存放编译成jasper文件的文件夹
            String path = new File("").getCanonicalPath() + "/jasper/";
            File file = new File(path);
            if(!file.exists()) {
                boolean mkdir = file.mkdir();
            }*/
            //2：直接在项目的编译出来的根路径创建文件夹，自己创建
            String path = this.getClass().getResource("/").getPath() + "/jasper/";
            //由jrxml文件编译后生产jasper文件的路径
            JasperCompileManager.compileReportToFile(mainDesign, path + "DemoReport6_main.jasper");
            JasperCompileManager.compileReportToFile(subDesign, path + "DemoReport6_sub.jasper");

            parameters.put("title", "学生成绩表");
            parameters.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            parameters.put("SUBREPORT_DIR", path);

            JasperPrint jasperPrint1 = JasperFillManager.fillReport(path + "/DemoReport6_main.jasper", parameters, new JRBeanCollectionDataSource(list));

            //生成html文件（位置：D:/HTML/student.html）
            JasperExportManager.exportReportToHtmlFile(jasperPrint1, "D:/HTML/student2.html");
            JasperExportManager.exportReportToPdfFile(jasperPrint1, "D:/HTML/student3.pdf");
        } catch (JRException e) {
            e.printStackTrace();
        }

    }
}
