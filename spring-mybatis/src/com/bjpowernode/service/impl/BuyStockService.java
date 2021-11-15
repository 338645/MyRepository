package com.bjpowernode.service.impl;

import com.bjpowernode.beans.Account;
import com.bjpowernode.beans.Stock;
import com.bjpowernode.dao.IAccountDao;
import com.bjpowernode.dao.IStockDao;
import com.bjpowernode.exception.BuyStockException;
import com.bjpowernode.service.IBuyStockService;

public class BuyStockService implements IBuyStockService {
    private IAccountDao adao;
    private IStockDao sdao;

    public void setAdao(IAccountDao adao) {
        this.adao = adao;
    }

    public void setSdao(IStockDao sdao) {
        this.sdao = sdao;
    }

    @Override
    public void openAccount(String aname, double money) {
        adao.insertAccount(aname, money);
    }

    @Override
    public void openStock(String sname, int amount, double price) {
        sdao.insertStock(sname, amount, price);
    }

    @Override
    public void buyStock(Account account, int amount, Stock stock, boolean isBuy) throws BuyStockException {
        boolean flag = true;
        if ((account.getBalance() - stock.getPrice() * amount) < 0) {
            throw new RuntimeException("余额不足");
        }
        if (!isBuy) {
            flag = false;
        }
        if (adao.updateAccount(account.getAname(), amount, stock.getPrice(), flag) == 0) {
            throw new BuyStockException("购买股票异常：用户更新失败");
        }
        if (sdao.updateStock(stock.getSname(), amount, flag) == 0) {
            throw new BuyStockException("购买股票异常：股票更新失败");
        }
    }
}
