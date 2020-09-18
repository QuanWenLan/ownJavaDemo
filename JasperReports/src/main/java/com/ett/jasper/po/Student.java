package com.ett.jasper.po;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vin lan
 * @className Student
 * @description TODO
 * @createTime 2020-09-09  11:06
 **/
public class Student {

    private Integer stuNo;

    private String stuName;

    private List<Subject> subjectList = new ArrayList<Subject>();

    public Student() {
    }

    public Integer getStuNo() {
        return stuNo;
    }

    public void setStuNo(Integer stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuNo='" + stuNo + '\'' +
                ", stuName='" + stuName + '\'' +
                ", subjectList=" + subjectList +
                '}';
    }
}
