import java.util.concurrent.atomic.AtomicInteger;

public class Thread_ThreadSafeAtomic {
    /**
     * 线程安全的原子性问题
     */
    public static void main(String[] args) {
        //启动两个线程，不断的调用getNum()方法
        MyInt myInt = new MyInt();
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        /*锁实现原子性
                        synchronized (myInt){
                            System.out.println(Thread.currentThread().getName() + "->" + myInt.getNum());
                            try {
                                Thread.sleep(100);
                                myInt.notifyAll();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        */
                        //AtomicInteger实现原子性
                        System.out.println(Thread.currentThread().getName() + "->" + myInt.getNum());
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }

    static class MyInt {
        //int num;
        AtomicInteger num = new AtomicInteger();
        public int getNum() {
            /**
             * ++自增操作的实现步骤
             *  读取num的值
             *  num自增
             *  把自增操作后的值再赋值给num变量
             */
            return num.getAndIncrement();
        }
        //java提供了一个线程安全的类AtomicInteger
    }
}
