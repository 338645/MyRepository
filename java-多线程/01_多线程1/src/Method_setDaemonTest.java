public class Method_setDaemonTest {
    /**
     * 守护线程测试
     */
    public static void main(String[] args) {
        DaemonThread1 dT = new DaemonThread1();
        //设置线程为守护线程(特殊的子线程)
        dT.setDaemon(true);
        dT.setName("DaemonThread");
        Thread.currentThread().setName("MainThread");
        dT.start();

        //主线程打印
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " is run...");
        }
    }
}
