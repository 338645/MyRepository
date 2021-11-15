public class Method_isAliveTest01 {
    public static void main(String[] args) throws InterruptedException {
        MyThread5 myThread5 = new MyThread5();
        System.out.println("begin = " + myThread5.isAlive());
        myThread5.start();
        System.out.println("end = " + myThread5.isAlive());//这一行的结果不一定，是随机的，如果线程已结束返回false，还未结束返回true
        Thread.sleep(2000);
        myThread5.setName("false");
    }
}
