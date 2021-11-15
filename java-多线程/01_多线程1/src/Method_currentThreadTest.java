public class Method_currentThreadTest {
    public static void main(String[] args) {
        Thread.currentThread().setName("MainThread");
        System.out.println("main线程名称：" + Thread.currentThread().getName());
        MyThread3 myThread3 = new MyThread3();//构造方法是由main线程调用的
        myThread3.setName("subThread");


        //myThread3.start();
        // run方法是由myThread3线程调用的，所以run方法中的当前就是subThread


        //myThread3.run();
        // 当前线程是main线程，在那个线程调用run方法，当前线程就是那个线程
    }
}
