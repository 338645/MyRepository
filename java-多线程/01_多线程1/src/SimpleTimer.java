/**
 * 使用线程休眠实现一个简易的计时器
 */
public class SimpleTimer {
    public static void main(String[] args) {
        int remaining = 60;//从60秒开始计时
        if (args.length == 1) {
            remaining = Integer.parseInt(args[0]);
        }
        while (true) {
            System.out.println("remaining = " + remaining--);
            if (remaining < 0) {
                break;
            }
            try {
                Thread.sleep(1000);//线程休眠
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("done!");
    }
}
