import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.ByteBuffer;

public class Thread_PipeComucationTest {
    /**
     * 通过Pipe管道流实现线程之间的通信
     *
     * @param args
     */
    public static void main(String[] args) {
        //新建两个PipedOutputStream和PipedInputStream
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream();
        try {
            in.connect(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                writeData(out);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                readData(in);
            }
        }).start();
    }

    //向管道流里边写数据
    public static void writeData(PipedOutputStream out) {
        try {
            for (int i = 0; i < 100; i++) {
                String data = "data-->" + i;
                out.write(data.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //向管道流中读取数据
    public static void readData(PipedInputStream in) {
        String format = "data-->i";
        //byte[] bytes = new byte[format.getBytes().length];
        byte[] bytes = new byte[1024*8*4];

        try {
            int len = in.read(bytes);
            while (len > 0) {
                System.out.println(new String(bytes, 0, len));
                len = in.read(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
