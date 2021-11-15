package com.bjpowernode.test;

import com.bjpowernode.service.IBuyStockService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    private ApplicationContext ac;
    private IBuyStockService service;

    @Before
    public void Before() {
        ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = (IBuyStockService) ac.getBean("buyStockService");
    }

    @Test
    public void Test01() {
        //service.openAccount("老刘", 6000);
        //service.openStock("奇游汽油",0,1.8);
    }

}
