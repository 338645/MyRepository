package com.bjpowernode.ProducerAndCosumerMode.entity;

public class ValueOP {
    private String value = "";

    //修改value字段的值
    public void setValue() {
        synchronized (this) {
            while (!value.equals("")) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String newValue = System.currentTimeMillis() + "-" + System.nanoTime();
            this.value = newValue;
            System.out.println("set设置得值为：" + this.value);
            this.notifyAll();
        }
    }

    //定义方法读取值

    public String getValue() {
        synchronized (this) {
            while (value.equals("")) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("get的值是：" + this.value);
            this.value = "";
            this.notifyAll();
            return value;
        }
    }

    public String getValue(Boolean flag) {
        return "";
    }
}
