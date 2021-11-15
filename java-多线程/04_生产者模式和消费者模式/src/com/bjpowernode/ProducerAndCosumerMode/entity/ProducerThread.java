package com.bjpowernode.ProducerAndCosumerMode.entity;

import java.util.Random;

public class ProducerThread extends Thread {
    private ValueOP valueOP;

    public ProducerThread(ValueOP valueOP) {
        this.valueOP = valueOP;
    }

    @Override
    public void run() {
        while (true) {
            valueOP.setValue();
        }
    }
}
