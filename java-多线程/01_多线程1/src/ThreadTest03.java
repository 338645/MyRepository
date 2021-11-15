public class ThreadTest03 {
    public static void main(String[] args) {
        //非匿名内部类方法创建线程
        Thread thread = new Thread(new MyRunnable());
        thread.start();



        //调用Thread(Runnable)构造方法时，实参也可以传递匿名内部类对象
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 100; i++) {
                    System.out.println("sub Thread2----->" + i);
                    try {
                        Thread.sleep((long) (Math.random()*100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread1.start();

        //main线程打印字符串
        for (int i = 1; i <= 100; i++) {
            System.out.println("main Thread----->" + i);
            try {
                Thread.sleep((long) (Math.random()*100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
