public class Test {
    /**
     * 测试线程没获得锁之后进入什么状态
     * 答案：线程没获得锁之后进入BLOCKED阻塞状态
     */
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + " locked");
                    while (true) {

                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + " locked");
                    while (true) {

                    }
                }
            }
        });
        t1.start();
        t2.start();
        Thread.sleep(1000);
        //线程没有获得锁的时候进入阻塞状态
        System.out.println(t2.getState());
    }
}
