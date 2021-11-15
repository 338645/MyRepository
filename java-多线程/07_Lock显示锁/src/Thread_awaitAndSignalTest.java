import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Thread_awaitAndSignalTest {
    /**
     * 多个condition实现通知部分线程
     * 使用比notify和wait更灵活
     */
    static class Service {
        static ReentrantLock lock = new ReentrantLock();
        private Condition[] conditions = new Condition[10];
        private int lenght = 0;
        private List<Condition> runnableConditions = new ArrayList<>();
        //private Condition conditionA = lock.newCondition();
        //private Condition conditionB = lock.newCondition();

        private boolean isFull() {
            if (lenght < 10) {
                return true;
            }
            return false;
        }

        private boolean isCanSignal(Condition condition) {
            if (runnableConditions.contains(condition)) {
                return true;
            }
            return false;
        }

        public void waitConditionT() {
            Condition condition;
            try {
                if (isFull()) {
                    condition = lock.newCondition();
                    conditions[lenght] = condition;
                    lenght++;
                    runnableConditions.add(condition);
                } else {
                    condition = conditions[new Random().nextInt(10)];
                    runnableConditions.add(condition);
                }
                System.out.println(Thread.currentThread().getName() + " begin wait:" + System.currentTimeMillis() + condition);
                if (lock.tryLock()) {
                    condition.await();
                }
                System.out.println(Thread.currentThread().getName() + " end wait:" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        }

        public void signalCondition(int i) {
            Condition condition;
            condition = conditions[i];
            if (!isCanSignal(condition)) {
                System.out.println("The Condition can't signal");
                return;
            }
            System.out.println(Thread.currentThread().getName() + " signal Condition" + i + ":" + System.currentTimeMillis() + "-" + condition);
            try {
                while (true) {
                    if (lock.tryLock()) {
                        condition.signal();
                        runnableConditions.remove(condition);
                        lenght--;
                        return;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();

        new Thread(new Runnable() {
            @Override
            public void run() {
                service.waitConditionT();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                service.waitConditionT();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                service.waitConditionT();
            }
        }).start();
        Thread.sleep(3000);
        service.signalCondition(0);
        service.signalCondition(1);
        service.signalCondition(2);
    }
}
