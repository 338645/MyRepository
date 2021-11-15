package com.bjpowernode.ProducerAndCosumerMode2.entity;

import java.util.ArrayList;
import java.util.List;

public class MyStack {
    private List<Object> list = new ArrayList<>();
    //private static final int MAX = 1;//集合的最大容量
    private int MAX;

    public MyStack(int MAX) {
        this.MAX = MAX;
    }

    //入栈操作
    public synchronized void push() {
        //判断栈是否已满
        while (isFull()) {
            System.out.println("The Stack is Full.....");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //生产者模式，不需用户输入数据
        String data = "data-->" + Math.random();
        System.out.println(Thread.currentThread().getName() + " add " + data);
        list.add(data);
        this.notifyAll();
    }

    //出栈方法
    public synchronized void pop() {
        while (isEmpty()) {
            System.out.println("The Stack is Empty....");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //消费者模式
        String data = (String) list.remove(0);
        System.out.println(Thread.currentThread().getName() + " pop " + data);
        this.notifyAll();
    }

    //判断栈是否已满
    public synchronized boolean isFull() {
        if (list.size() >= MAX) {
            return true;
        }
        return false;
    }

    //判断栈是否已空
    public synchronized boolean isEmpty() {
        if (list.size() == 0) {
            return true;
        }
        return false;
    }

}
