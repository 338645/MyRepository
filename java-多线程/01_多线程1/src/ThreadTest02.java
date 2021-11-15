public class ThreadTest02 {
    public static void main(String[] args) throws InterruptedException {

        MyThread2 myThread2 = new MyThread2();
        myThread2.start();
        for (int i = 1;i<10;i++) {
            System.out.println("main : "+ i );
            int time = (int) (Math.random()*1000);
            Thread.sleep(time);
        }
    }
}