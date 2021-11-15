public class MyRunnable implements Runnable {
    private int count = 1;

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            System.out.println("sub Thread----->" + i);
            try {
                Thread.sleep((long) (Math.random()*100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
