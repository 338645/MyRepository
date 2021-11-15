package com.bjpowernode.Test;

public class Test03 {
    /**
     * 可重入锁
     *当前线程获得锁对象，再次申请锁对象的时候，可以再次获得锁对象，该锁称为可重入锁
     * 如果假设锁不可重入，可能会导致死锁
     * @param args
     */
    public static Object lock = new Object();

    public static void main(String[] args) {
        new SubThread().start();
    }

    static class SubThread extends Thread {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("获得锁第" + 1 + "次");
                synchronized (lock) {
                    //在调用第二个输出时，当前线程还是持有锁对象
                    System.out.println("第2次获得锁");
                    synchronized (lock) {
                        //在调用第三个输出的时候，当前线程还是持有锁对象
                        System.out.println("第3次获得锁");
                    }
                }
            }
        }
    }

}
