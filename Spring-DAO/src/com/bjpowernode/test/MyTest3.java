package com.bjpowernode.test;

import com.bjpowernode.beans.Account;
import com.bjpowernode.beans.Stock;
import com.bjpowernode.exception.BuyStockException;
import com.bjpowernode.service.IBuyStockService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest3 {
    private ApplicationContext ac;
    private IBuyStockService service;

    @Before
    public void Before() {
        ac = new ClassPathXmlApplicationContext("spring-transaction2.xml");
        service = (IBuyStockService) ac.getBean("buyStockService");
    }

    @Test
    public void Test01() throws BuyStockException {
        //service.openAccount("张三", 10000);
        //service.openStock("北京动力节点", 0, 1.5);
        Account account = new Account("张三", 10000);
        Stock stock = new Stock("北京动力节点", 0, 1.5);
        service.buyStock(account, 1001, stock, true);
    }
}
