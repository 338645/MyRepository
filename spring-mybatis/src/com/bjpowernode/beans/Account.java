package com.bjpowernode.beans;

public class Account {
    private Integer aid;
    private String aname;
    private double balance;

    public Account() {
    }

    public Account(String aname, double balance) {
        this.aname = aname;
        this.balance = balance;
    }

    public Account(Integer aid, String aname, double balance) {
        this.aid = aid;
        this.aname = aname;
        this.balance = balance;
    }

    public Integer getAid() {
        return aid;
    }

    public String getAname() {
        return aname;
    }

    public double getBalance() {
        return balance;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String  toString() {
        return "Account{" +
                "aid=" + aid +
                ", aname='" + aname + '\'' +
                ", balance=" + balance +
                '}';
    }
}
