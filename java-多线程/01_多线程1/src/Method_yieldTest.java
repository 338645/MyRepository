public class Method_yieldTest {
    public static void main(String[] args) {
        MyThread8 t1 = new MyThread8();
        t1.start();

        //在main线程中计算累加和
        long begin = System.currentTimeMillis();
        long sum = 0;
        for (int i = 0; i < 1000000; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("Main运行时间 = " + (end - begin));

    }
}
