public class ThreadGroup_setDaemonTest {
    /**
     * setDaemon(true)设置守护线程组
     */
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup group = new ThreadGroup("group");
        group.setDaemon(true);
        //向线程组中添加三个线程
        for (int i = 0; i < 3; i++) {
            new Thread(group, new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 20; i++) {
                        System.out.println(Thread.currentThread().getName() + "-" + i);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
        Thread.sleep(5000);
        //守护线程中三个线程不是守护线程。
        System.out.println(Thread.currentThread().getName() + " end ");
    }
}
