package com.bjpowernode.ProducerAndCosumerMode2.entity;

public class ProducerThread extends Thread {
    static MyStack stack;

    public ProducerThread(MyStack stack) {
        this.stack = stack;
    }

    @Override
    public void run() {
        while(true){
            stack.push();
        }
    }
}
