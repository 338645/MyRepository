public class MyThread7 extends Thread {
    @Override
    public void run() {
        System.out.println("线程的编号 = " + Thread.currentThread().getId() + "  线程的姓名 = " + Thread.currentThread().getName());
    }
}
