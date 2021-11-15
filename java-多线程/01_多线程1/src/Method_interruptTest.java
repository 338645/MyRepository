public class Method_interruptTest {
    /**
     * interrupt测试
     *
     * @param args
     */
    public static void main(String[] args) {
        MyThread10 t1 = new MyThread10();
        t1.setName("SubThread");
        Thread.currentThread().setName("MainThread");
        //t1.start();
        MyThread11 t2 = new MyThread11();
        t2.start();
        t2.setName("SubThread2");


        //主线程打印
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " is run....");
        }
        //中断子线程
        //t1.interrupt();//仅仅是给子线程设置了一个中断标记，没有真正的中断
        //如果想让子线程真正的中断

        //真正的中断
        t2.interrupt();
    }
}
