public class MyThread11 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            //在每次执行循环的时候判断中断标记,使用isInterrupt()方法会返回当前线程是否有
            //中断标记
            if(this.isInterrupted()){
                System.out.println(Thread.currentThread().getName() + "已被中断");
                //break;//循环中断run方法执行完毕
                return;//直接结束当前run方法
            }
            System.out.println(Thread.currentThread().getName()+ " is run.....");
        }
    }
}
