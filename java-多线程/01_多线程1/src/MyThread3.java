public class MyThread3 extends Thread {
    public MyThread3() {
        System.out.println("当前线程名称：" + Thread.currentThread().getName());
    }

    @Override
    public void run() {
        System.out.println("当前线程的名称：" + Thread.currentThread().getName());
    }
}
