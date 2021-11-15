public class ThreadConstructorTest01 {
    /**
     * 测试工作区
     *
     * @param args
     */
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.setName("ThreadOne");
        myThread.start();
        /**
         * 线程由start()来启动，启动线程的实质就是请求JVM运行相应的线程，这个线程具体
         * 在什么时候运行，有线程调度器（Scheduler）来决定
         * 注意：start()方法调用结束，并不意味子线程开始运行，通知线程进入就绪状态
         * 先进入运行的线程会执行run()方法。
         * 开启了多个线程，start()的调用顺序不一定是线程运行的顺序。
         * new Thread ---> 新建线程 ---> Thread类变量名.start()---> 通知jvm虚拟机该线程进入就绪状态
         */
    }
}