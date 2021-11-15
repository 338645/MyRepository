public class Method_getIdTest {
    public static void main(String[] args) {
        MyThread7 t1 = new MyThread7();
        t1.start();
        System.out.println("MainThreadName = " + Thread.currentThread().getName() + "   MainThreadId = " + Thread.currentThread().getId());
        for (int i = 0; i < 50; i++) {
            new MyThread7().start();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
