public class Thread_ThreadGroup_BasicOperation {
    /**
     * 演示线程组的基本操作
     */
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup group = new ThreadGroup("group");

        Runnable r = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("------当前线程：" + Thread.currentThread());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread t1 = new Thread(r, "t1");
        Thread t2 = new Thread(group, r, "t2");
        t1.start();
        t2.start();
        //打印一下线程组的相关属性
        Thread.sleep(3000);
        System.out.println("main线程组中的活动线程数量：" + mainGroup.activeCount() + "\n" +
                ",main线程组中活动的线程组的数量：" + mainGroup.activeGroupCount() + "\n" +
                ",main线程组的父线程组：" + mainGroup.getParent() + "\n" +
                "main线程组是否为守护线程：" + mainGroup.isDaemon() + "\n" +
                "mian线程组是否为group线程的父线程：" + mainGroup.parentOf(group));
        mainGroup.list();
        System.out.println("group线程组中的活动线程数量：" + group.activeCount() + "\n" +
                ",group程组中活动的线程组的数量：" + group.activeGroupCount() + "\n" +
                ",group线程组的父线程组：" + group.getParent() + "\n" +
                "group线程组是否为守护线程：" + group.isDaemon() + "\n" +
                "group线程组是否为group线程的父线程：" + group.parentOf(group));
    }
}
