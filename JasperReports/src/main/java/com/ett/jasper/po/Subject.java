package com.ett.jasper.po;

/**
 * @author Vin lan
 * @className Subject
 * @description TODO
 * @createTime 2020-09-09  11:07
 **/
public class Subject {
    private String subjectName;

    private String teacherName;

    private Double score;

    public Subject(String subjectName, String teacherName, Double score) {
        this.subjectName = subjectName;
        this.teacherName = teacherName;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectName='" + subjectName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", score=" + score +
                '}';
    }

    public Subject() {
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
