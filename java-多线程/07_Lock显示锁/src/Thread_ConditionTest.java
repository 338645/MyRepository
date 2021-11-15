import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Thread_ConditionTest {
    /**
     * condition实现生产者/消费者设计模式，1）两个线程交替打印
     * 2）多对多打印
     */
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean flag = true;
    private int executeNums = 0;

    static class ProducerThread extends Thread {
        private Thread_ConditionTest tc;

        public ProducerThread(Thread_ConditionTest tc) {
            this.tc = tc;
        }

        @Override
        public void run() {
            tc.printOne();
        }
    }

    static class ConsumerThread extends Thread {
        private Thread_ConditionTest tc;

        public ConsumerThread(Thread_ConditionTest tc) {
            this.tc = tc;
        }

        @Override
        public void run() {
            tc.printTwo();
        }
    }

    //只打印-----的方法
    public void printOne() {
        try {
            while (true) {
                if (lock.tryLock()) {
                    //获得锁
                    while (flag) {
                        System.out.println(Thread.currentThread().getName() + " is waiting....");
                        condition.await();
                    }
                    System.out.println(Thread.currentThread().getName() + "----------------");
                    flag = true;
                    condition.signalAll();
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
            executeNums++;
        }
    }

    //只打印********
    public void printTwo() {
        try {
            while (true) {
                if (lock.tryLock()) {
                    //获得锁
                    while (!flag) {
                        System.out.println(Thread.currentThread().getName() + " is waiting....");
                        condition.await();
                    }
                    System.out.println(Thread.currentThread().getName() + "***************");
                    flag = false;
                    condition.signalAll();
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
            executeNums++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread_ConditionTest tc = new Thread_ConditionTest();
        /**
         * 线程多对多打印
         */
        /*for (int i = 0; i < 100; i++) {
            new ConsumerThread(tc).start();
            new ProducerThread(tc).start();
            //只用signal有可能导致假死
        }*/

        /**
         * 一对一线程打印
         */
        /*new Thread(new Runnable() {*/
        /*    @Override*/
        /*    public void run() {*/
        /*        for (int i = 0; i < 100; i++) {*/
        /*            tc.printOne();*/
        /*        }*/
        /*    }*/
        /*}).start();*/
        /*new Thread(new Runnable() {*/
        /*    @Override*/
        /*    public void run() {*/
        /*        for (int i = 0; i < 100; i++) {*/
        /*            tc.printTwo();*/
        /*        }*/
        /*    }*/
        /*}).start();*/

        /**
         * 多对多线程打印
         */
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        tc.printOne();
                    }
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        tc.printTwo();
                    }
                }
            }).start();
        }
        Thread.sleep(3000);
        System.out.println(tc.executeNums);
    }

}
