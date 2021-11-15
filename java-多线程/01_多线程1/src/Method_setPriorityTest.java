public class Method_setPriorityTest {
    public static void main(String[] args) {
        MyThread9 ThreadA = new MyThread9();
        MyThread9 ThreadB = new MyThread9();
        ThreadA.setPriority(1);
        ThreadA.setName("ThreadA");
        ThreadB.setName("ThreadB");
        ThreadB.setPriority(10);
        ThreadA.start();
        ThreadB.start();
    }
}
