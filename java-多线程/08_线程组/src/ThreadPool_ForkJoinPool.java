import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ThreadPool_ForkJoinPool {
    /**
     * 演示ForkJoinPool线程池的使用
     * 使用线程池模拟数列求和
     */
    //计算数列的和,需要返回结果,定义任务继承RecursiveTask
    private static class CountTask extends RecursiveTask<Long> {
        private static final int TASKNUM = 100;//定义每次大任务分解为100个
        private static final int THRESHOLD = 10000;//定义数据规模的阈值，允许计算10000个数内的和，超过该阈值的数列需要分解
        private long start; //数列的起始值
        private long end;   //数列的结束值

        public CountTask(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            long sum = 0;//保存计算的结果
            //判断任务是否超过阈值,超过阈值就需要继续分解
            if (end - start < THRESHOLD) {
                //直接计算
                for (long i = start; i <= end; i++) {
                    sum += i;
                }
            } else {
                //超过阈值，继续分解，约定每次每次分解为100个小任务,计算每个任务的计算量
                //如果计算【0,2000000】范围内的数列的和，把该范围的数列分解为100个小任务，每个任务计算2000个即可。
                //注意，如果任务的划分层次很深，即阈值设置得太小，每个任务的计算量很小，层次就会很深，可能出现两种情况：
                //1）系统内的线程数量会越积越多，导致性能下降严重，2）分解次数过多，方法调用过多可能会导致栈溢出。
                long step = (start + end) / 100;
                //先创建一个存储这个任务的集合
                ArrayList<CountTask> subTaskList = new ArrayList<>(100);
                long pos = start;   //任务的起始位置
                for (int i = 0; i < TASKNUM; i++) {
                    long lastOne = pos + step;//每个任务的结束位置
                    //调整最后一个任务的结束位置
                    if (lastOne > end) {
                        lastOne = end;
                    }
                    //创建一个子任务
                    CountTask subTask = new CountTask(pos, lastOne);
                    subTaskList.add(subTask);
                    subTask.fork();
                    pos += step + 1;
                }
                //等待所有的子任务结束后，合并计算结果
                for (CountTask countTask : subTaskList) {
                    sum += countTask.join();
                    //join会一直等待子任务执行完毕返回执行结果
                }
            }
            return sum;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask countTask = new CountTask(0, 200000L);
        ForkJoinTask<Long> result = forkJoinPool.submit(countTask);
        Long aLong = result.get();
        System.out.println(aLong);
    }
}
