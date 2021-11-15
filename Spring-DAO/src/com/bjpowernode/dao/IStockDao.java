package com.bjpowernode.dao;

public interface IStockDao {
    void insertStock(String sname, int amount, double price);

    int updateStock(String sname, int amount, boolean flag);
}
