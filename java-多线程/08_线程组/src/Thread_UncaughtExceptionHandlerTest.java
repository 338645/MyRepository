import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Thread_UncaughtExceptionHandlerTest {
    /**
     * 设置UncaughtExceptionHandler回调接口来处理线程运行中出现的异常
     */
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                //打印异常信息
                System.out.println(t.getName() + "线程出现了异常，异常为：" + e.fillInStackTrace());
            }
        });
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "开始运行：" + System.currentTimeMillis());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //算术异常
                System.out.println(12 / 0);
            }
        });
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                String text = null;
                //空指针异常
                System.out.println(text.toString());
            }
        });
        t2.start();
    }

}
