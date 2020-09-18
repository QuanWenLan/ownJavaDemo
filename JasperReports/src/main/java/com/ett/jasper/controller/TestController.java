package com.ett.jasper.controller;

import com.ett.jasper.CityService;
import com.ett.jasper.po.City;
import com.ett.jasper.po.Student;
import com.ett.jasper.po.Subject;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Vin lan
 * @className TestController
 * @description TODO
 * @createTime 2020-09-03  15:01
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private CityService cityService;

    @RequestMapping("/tt")
    @ResponseBody
    public List<City> getTest(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        return cityService.getAllCityInfo();
    }

    @RequestMapping("/sub")
    @ResponseBody
    protected String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String,Object> parameters = new HashMap<String,Object>(16);

        //组装list数据源
        Subject sub1 = new Subject("语文","张老师",80.0);
        Subject sub2 = new Subject("数学","王老师",90.0);
        Subject sub3 = new Subject("物理","孙老师",46.0);
        Subject sub4 = new Subject("政治","李老师",50.0);
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
        stu2.setStuName("小雨");
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

        String mainjrxmlPath = request.getServletContext().getRealPath("/")+"/jasper/DemoReport6_main.jrxml";
        String subjrxmlPath = request.getServletContext().getRealPath("/")+"/jasper/DemoReport6_sub.jrxml";
        //由jrxml文件编译后生产jasper文件的路径
        String mainjasperPath = request.getServletContext().getRealPath("/")+"/jasper/DemoReport6_main.jasper";
        String subjasperPath = request.getServletContext().getRealPath("/")+"/jasper/DemoReport6_sub.jasper";

        try {
            //编译jrxml生产jasper文件
            JasperCompileManager.compileReportToFile(mainjrxmlPath, mainjasperPath);
            JasperCompileManager.compileReportToFile(subjrxmlPath, subjasperPath);

            parameters.put("title", "学生成绩表");
            parameters.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            String SUBREPORT_DIR = request.getServletContext().getRealPath("/")+"/jasper/";
            parameters.put("SUBREPORT_DIR",SUBREPORT_DIR);

            JasperPrint jasperPrint = JasperFillManager.fillReport(mainjasperPath, parameters, new JRBeanCollectionDataSource(list));
            //生成html文件（位置：D:/HTML/student.html）
            JasperExportManager.exportReportToHtmlFile(jasperPrint, "d:/HTML/student.html");
        } catch (JRException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
