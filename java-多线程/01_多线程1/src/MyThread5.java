public class MyThread5 extends Thread {
    public MyThread5() {
        this.setName("ture");
    }

    @Override
    public void run() {
        while(this.getName().equals("ture")){
            System.out.println("子线程正在运行!");
        }
    }
}
