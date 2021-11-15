package com.bjpowernode.service;

import com.bjpowernode.beans.Account;
import com.bjpowernode.beans.Stock;
import com.bjpowernode.exception.BuyStockException;

public interface IBuyStockService {
    void openAccount(String aname, double money);

    void openStock(String sname, int amount, double price);

    void buyStock(Account account, int amount, Stock stock, boolean isBuy)throws BuyStockException;
}
