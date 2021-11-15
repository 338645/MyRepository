package com.bjpowernode.Test;


import java.util.Date;
import java.util.Random;

public class Test02 {
    /**
     * ThreadLocal的初始值
     * 通过继承ThreadLocal并重写initialValue方法可以设置初始值
     */
    //static ThreadLocal threadLocal = new ThreadLocal();
    static ThreadLocal threadLocal = new SubThreadLocal();

    //定义ThreadLocal子类
    static class SubThreadLocal extends ThreadLocal<Date> {
        //重写initialValue()方法定义初始值
        @Override
        protected Date initialValue() {
            return new Date(System.currentTimeMillis() - 1000 * 60 * 15);
        }
    }

    static class SubThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                //第一次调用get方法会返回null
                System.out.println("-----------------------" + Thread.currentThread().getName() + "\n" + "value= " + threadLocal.get() + "\n" +
                        "-----------------------");
                if (threadLocal.get() == null) {
                    threadLocal.set(new Date());
                }
                try {
                    Thread.sleep(new Random().nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SubThread subThread1 = new SubThread();
        subThread1.start();
        SubThread subThread2 = new SubThread();
        subThread2.start();
    }
}
