public class MyThread extends Thread {
    /**
     * 重写run方法
     * run方法中的命令就是子线程的执行体
     */
    private int count = 0;

    @Override
    public void run() {
        while (true) {
            if (count == 100) {
                break;
            }
            System.out.println(this.getName() + " : " + count++);
        }
    }
}
