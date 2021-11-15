package com.bjpowernode.ProducerAndCosumerMode.entity;

public class CosumerThread extends Thread {
    private ValueOP valueOP;

    public CosumerThread(ValueOP valueOP) {
        this.valueOP = valueOP;
    }

    @Override
    public void run() {
        while (true) {
            valueOP.getValue();
        }
    }
}
