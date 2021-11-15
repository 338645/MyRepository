public class MyThread2 extends Thread {
    @Override
    public void run() {
        for(int i = 0;i<10;i++){
            try {
                System.out.println(this.getName() + " : "+ i );
                int time = (int) (Math.random()*1000);
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
