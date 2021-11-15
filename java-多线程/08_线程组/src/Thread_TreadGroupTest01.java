public class Thread_TreadGroupTest01 {
    /**
     * 演示创建线程组
     */
    public static void main(String[] args) {
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        System.out.println(mainGroup);
        //如果不指定线程组的父线程，则自动归属于当前线程所属的线程组
        ThreadGroup daemon1 = new ThreadGroup("daemon1");
        //如果指定线程组的父线程，则归属于父线程
        ThreadGroup daemon2 = new ThreadGroup(daemon1, "daemon2");
        System.out.println(daemon2);
        System.out.println(daemon1.getParent() == mainGroup);
        System.out.println(daemon2.getParent() == daemon1);

        //创建线程时指定所属线程组
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread());
            }
        };
        //如果创建线程组如果没有指定线程组，则默认归属于父线程的线程组中
        Thread t1 = new Thread(r, "t1");
        System.out.println(t1);
        //如果创建线程组时候有指定线程组，则归属于父线程
        Thread t2 = new Thread(daemon1, r, "t2");
        System.out.println(t2);
        Thread t3 = new Thread(daemon2, r, "t3");
        System.out.println(t3);
    }
}
