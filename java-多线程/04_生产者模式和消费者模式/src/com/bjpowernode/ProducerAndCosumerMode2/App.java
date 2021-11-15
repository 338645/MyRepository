package com.bjpowernode.ProducerAndCosumerMode2;

import com.bjpowernode.ProducerAndCosumerMode2.entity.ConsumerThread;
import com.bjpowernode.ProducerAndCosumerMode2.entity.MyStack;
import com.bjpowernode.ProducerAndCosumerMode2.entity.ProducerThread;

public class App {
    public static void main(String[] args) {
/*
        //测试生产模式,1-1生产-消费
        MyStack myStack = new MyStack();
        new ProducerThread(myStack).start();
        new ConsumerThread(myStack).start();
*/


        //一对多
        MyStack myStack = new MyStack(1);
        ConsumerThread consumerThread1 = new ConsumerThread(myStack);
        ConsumerThread consumerThread2 = new ConsumerThread(myStack);
        ProducerThread producerThread = new ProducerThread(myStack);
        producerThread.setName("生产者1号");
        consumerThread1.setName("消费者1号");
        consumerThread2.setName("消费者2号");
        producerThread.start();
        consumerThread1.start();
        consumerThread2.start();
        /*
        //多对多测试
        MyStack myStack = new MyStack();
        for (int i = 0; i < 100; i++) {
            new ProducerThread(myStack).start();
        }
        for (int i = 0; i < 100; i++) {
            new ConsumerThread(myStack).start();
        }
*/

    }
}
