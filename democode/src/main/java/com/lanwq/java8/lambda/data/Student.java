package com.lanwq.java8.lambda.data;

/**
 * @author Vin lan
 * @className Student
 * @description
 * @createTime 2023-04-06  14:25
 **/
public class Student {
    //年级
    private String grade;
    //班级
    private String classNumber;
    //姓名
    private String name;
    //年龄
    private int age;
    //地址
    private String address;
    //数学成绩
    private int mathScores;
    //语文成绩
    private int chineseScores;

    public Student(String grade, String classNumber, String name, int age, String address, int mathScores, int chineseScores) {
        this.grade = grade;
        this.classNumber = classNumber;
        this.name = name;
        this.age = age;
        this.address = address;
        this.mathScores = mathScores;
        this.chineseScores = chineseScores;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMathScores() {
        return mathScores;
    }

    public void setMathScores(int mathScores) {
        this.mathScores = mathScores;
    }

    public int getChineseScores() {
        return chineseScores;
    }

    public void setChineseScores(int chineseScores) {
        this.chineseScores = chineseScores;
    }
}
