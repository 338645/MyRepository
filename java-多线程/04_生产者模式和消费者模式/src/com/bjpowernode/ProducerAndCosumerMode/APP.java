package com.bjpowernode.ProducerAndCosumerMode;

import com.bjpowernode.ProducerAndCosumerMode.entity.CosumerThread;
import com.bjpowernode.ProducerAndCosumerMode.entity.ProducerThread;
import com.bjpowernode.ProducerAndCosumerMode.entity.ValueOP;

public class APP {
    public static void main(String[] args) {
        ValueOP valueOP = new ValueOP();
        ProducerThread producer = new ProducerThread(valueOP);
        CosumerThread cosumer = new CosumerThread(valueOP);
        //生产和消费交替运行
        //producer.start();
        //cosumer.start();

        //多个消费者和多个生产者
        //当valueOP使用notify()而不是notifyAll()时候
        //可能由于消费者唤醒消费者，或生产者唤醒生产者，导致假死锁线程
        for (int i = 0; i < 10; i++) {
            new ProducerThread(valueOP).start();
            new CosumerThread(valueOP).start();
        }
    }
}
