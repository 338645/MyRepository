public class Thread_ThreadGroupEnumerateTest {
    /**
     * enumerate()复制线程组
     */
    public static void main(String[] args) {
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();

        ThreadGroup group1 = new ThreadGroup("group1");
        ThreadGroup group2 = new ThreadGroup(group1, "group2");

        Runnable r = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("-----当前线程：" + Thread.currentThread());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread t1 = new Thread(r, "t1");
        Thread t2 = new Thread(group1, r, "t2");
        Thread t3 = new Thread(group2, r, "t3");
        t1.start();
        t2.start();
        t3.start();

        Thread[] threads = new Thread[mainGroup.activeCount()];
        //把子线程组中的线程也复制到数组中
        //mainGroup.enumerate(threads);
        //不把子线程组中的线程复制到数组中
        mainGroup.enumerate(threads, false);
        for (int i = 0; i < threads.length; i++) {
            System.out.println(threads[i]);
        }

        ThreadGroup[] threadGroups = new ThreadGroup[mainGroup.activeGroupCount()];
        //把子线程中的线程组也复制到数组中
        //mainGroup.enumerate(threadGroups);
        //不把子线程组中的线程组复制到数组中
        mainGroup.enumerate(threadGroups, false);
        for (int i = 0; i < threadGroups.length; i++) {
            System.out.println(threadGroups[i]);
        }
    }
}
