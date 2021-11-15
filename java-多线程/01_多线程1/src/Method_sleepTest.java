public class Method_sleepTest {
    public static void main(String[] args) {
        MyThread6 T1 = new MyThread6();
        System.out.println("main_begin = " + System.currentTimeMillis());
        T1.start();
        System.out.println("main_end = " + System.currentTimeMillis());//main方法没有使用sleep方法睡眠，所以两个输出命令之间几乎没有时间间隔
        T1.run();//当前线程为main方法所以，执行线程为main，时间间隔为2000好买哦
        //在run方法中如果有异常需要处理，只能捕获处理，因为run方法是重写的，不能抛出比父线程更多的异常。
    }
}
