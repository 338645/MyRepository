package com.bjpowernode.ProducerAndCosumerMode2.entity;

public class ConsumerThread extends Thread {
    static MyStack stack;

    public ConsumerThread(MyStack stack) {
        this.stack = stack;
    }

    @Override
    public void run() {
        while (true) {
            stack.pop();
        }
    }
}
