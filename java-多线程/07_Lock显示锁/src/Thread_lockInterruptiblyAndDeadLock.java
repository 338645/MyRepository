import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Thread_lockInterruptiblyAndDeadLock {
    /**
     * lockInterruptibly可以解决死锁
     */

    /*static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();*/
    static ReentrantLock lock1 = new ReentrantLock();
    static ReentrantLock lock2 = new ReentrantLock();

    static class MyRunnable implements Runnable {
        private int lockNums;

        public MyRunnable(int lockNums) {
            this.lockNums = lockNums;
        }

        @Override
        public void run() {
            try {
                if (lockNums % 2 == 1) {
                    //如果lockNums是奇数，就先获得锁1再获得锁2
                    lock1.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + "已经获得锁1，还需要获得锁2.......");
                    Thread.sleep(new Random().nextInt(500));
                    lock2.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + "同时获得锁1和锁2.......");
                } else {
                    //如果lockNums是偶数，就先获得锁2再获得锁1
                    lock2.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + "已经获得锁2，还需要获得锁1.......");
                    lock1.lockInterruptibly();
                    Thread.sleep(new Random().nextInt(500));
                    System.out.println(Thread.currentThread().getName() + "同时获得锁1和锁2.......");
                    Thread.sleep(new Random().nextInt(500));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
/*
                lock1.unlock();
                lock2.unlock();
*/
                //使用ReentrantLock的内置方法判断该锁是否被该线程持有
                if(lock1.isHeldByCurrentThread()){
                    lock1.unlock();
                }
                if(lock2.isHeldByCurrentThread()){
                    lock2.unlock();
                }
                System.out.println(Thread.currentThread().getName() + "已经退出....");
            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable(1));
        Thread thread1 = new Thread(new MyRunnable(2));
        thread.start();
        thread1.start();
        //在main线程等待3000毫秒，如果还有线程没有结束就中断该线程
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*if(!thread.getState().equals(Thread.State.TERMINATED)){
            thread.interrupt();
        }
        if(!thread1.getState().equals(Thread.State.TERMINATED)){
            thread1.interrupt();
        }*/
        //使用isAlive判断是否结束
        if(thread.isAlive()){
            thread.interrupt();
        }
/*
        if(thread1.isAlive()){
            thread1.interrupt();
        }
*/
    }

}
