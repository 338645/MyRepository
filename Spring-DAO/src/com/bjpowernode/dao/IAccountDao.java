package com.bjpowernode.dao;

public interface IAccountDao {
    void insertAccount(String aname, double money);

    int updateAccount(String aname, int amount, double price, boolean flag);
}
