import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class Thread_HookThreadTest {
    /**
     * Hook钩子线程演示
     */
    public static void main(String[] args) {
        //第一步：注入一个hook线程
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("JVM退出，会调用当前Hook线程，在Hook线程中删除.lock文件");
            getLockFile().toFile().delete();
        }));
        //程序运行时，检查lock文件是否存在，如果lock文件存在，则抛出异常
        if (getLockFile().toFile().exists()) {
            throw new RuntimeException("程序已启动");
        } else {
            //文件不存在，说明程序是第一次启动，创建一个lock文件
            try {
                getLockFile().toFile().createNewFile();
                System.out.println("程序正在启动，正在创建lock文件......");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //模拟程序正在运行
        for (int i = 0; i < 5; i++) {
            System.out.println("程序正在运行....");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static Path getLockFile() {
        return Paths.get("", "tmp.lock");
    }
}

