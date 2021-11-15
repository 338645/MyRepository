public class Method_currentThreadTest02 {
    public static void main(String[] args) throws InterruptedException {
        MyThread4 myThread4 = new MyThread4();
        //因为this是指向当前的实例对象，所以在构造方法中的this.getName()指向的是这个新建线程的名称
        myThread4.setName("T1");
        myThread4.start();

        Thread.sleep(500);

        //Thread有参构造方法中传递的是Runnable接口的实现类
        Thread thread = new Thread(myThread4);

        thread.start();
        //run方法是由thread调用的，所以当前代码的线程为Thread-1，但是run是属于T1线程里的方法，所以
        //this指向的是T1线程这个实例对象，所以this.getName()显示的是T1
    }
}
