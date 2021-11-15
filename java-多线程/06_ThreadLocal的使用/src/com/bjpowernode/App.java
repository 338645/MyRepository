package com.bjpowernode;

public class App {
    static ThreadLocal threadLocal = new ThreadLocal();


    public static void main(String[] args) {
        new SubThread().start();
        new SubThread().start();
    }

    static class SubThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                //设置线程关联的值
                threadLocal.set(Thread.currentThread().getName() + "-" + i);
                //调用get方法调用相关的值
                System.out.println(Thread.currentThread().getName() + "Value= " + threadLocal.get());
            }
        }
    }
}
