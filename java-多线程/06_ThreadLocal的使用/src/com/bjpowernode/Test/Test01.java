package com.bjpowernode.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test01 {
    /**
     * 在多线程环境中，把一个字符串转换为日期对象
     */
    //字符串转换为日期的SimpleDateFormat对象
    //方法2使用ThreadLocal实现
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
    static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<>();

    /**
     * 问题描述：出现 java.lang.NumberFormatException: multiple points异常
     * 问题原因：SimpleDateFormat是线程不安全的，多个线程共享SimpleDateFormat会出现异常
     * 解决方案：
     * 1）为每个线程创建一个SimpleDateFormat
     * 2）用ThreadLocal对象储存SimpleDateFormat
     */

    static class ParseDate implements Runnable {
        //方法1
        //private SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        private int i = 0;

        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            String dayText = "2068年11月2日 08:28:" + i % 60;
            try {
                //Date dayFormat = sdf.parse(dayText);
                //方法2
                if (threadLocal.get() == null) {
                    threadLocal.set(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss"));
                }
                Date dayFormat = threadLocal.get().parse(dayText);
                System.out.println(i + "--" + dayFormat);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new ParseDate(i)).start();
        }
    }
}
