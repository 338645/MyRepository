package com.bjpowernode.beans;

public class Stock {
    private Integer sid;
    private String sname;
    private int count;
    private double price;

    public Stock() {
    }

    public Stock(String sname, int count, double price) {
        this.sname = sname;
        this.count = count;
        this.price = price;
    }

    public Stock(Integer sid, String sname, int count, double price) {
        this.sid = sid;
        this.sname = sname;
        this.count = count;
        this.price = price;
    }

    public Integer getSid() {
        return sid;
    }

    public String getSname() {
        return sname;
    }

    public int getCount() {
        return count;
    }

    public double getPrice() {
        return price;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", count=" + count +
                ", price=" + price +
                '}';
    }
}
