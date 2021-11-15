public class MyThread6 extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("run threadName" + Thread.currentThread().getName() + " ,begin = " + System.currentTimeMillis());
            Thread.sleep(2000);//当前线程睡眠2000毫秒

            System.out.println("run threadName" + Thread.currentThread().getName() + " ,begin = " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
