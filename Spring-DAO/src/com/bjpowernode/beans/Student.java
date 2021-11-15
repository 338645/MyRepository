package com.bjpowernode.beans;

public class Student {
    private Integer sid;
    private String sname;
    private int sage;
    private double score;

    public Student() {
    }

    public Student(String sname, int sage, double score) {
        this.sname = sname;
        this.sage = sage;
        this.score = score;
    }

    public Integer getSid() {
        return sid;
    }

    public String getSname() {
        return sname;
    }

    public int getSage() {
        return sage;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public void setSage(int sage) {
        this.sage = sage;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", sage=" + sage +
                ", score=" + score +
                '}';
    }
}
