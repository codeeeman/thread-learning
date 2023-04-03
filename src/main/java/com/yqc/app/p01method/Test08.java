package com.yqc.app.p01method;

import com.yqc.util.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test08 {

    private static int count = 0;

    public static void main(String[] args) {
        Object obj = new Object();

        // NEW
        Thread t1 = new Thread(() -> {}, "t1");

        // RUNNABLE
        Thread t2 = new Thread(() -> {
            while (true) {}
        }, "t2");
        t2.start();

        // TIMED_WAITING（有时间的等待）
        Thread t3 = new Thread(() -> {
            synchronized (obj) {
                ThreadUtil.sleep(10000000);
            }
        }, "t3");
        t3.start();

        // WAITING（无时间的等待）
        Thread t4 = new Thread(() -> {
            try {
                t3.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t4");
        t4.start();

        // BLOCKED（因未竞争到锁而阻塞）
        Thread t5 = new Thread(() -> {
            synchronized (obj) {
                log.info("t5竞争到锁了");
            }
        }, "t5");
        t5.start();

        // TERMINATED
        Thread t6 = new Thread(() -> {}, "t6");
        t6.start();

        ThreadUtil.sleep(2000);
        log.info("t1线程状态---->{}", t1.getState());
        log.info("t2线程状态---->{}", t2.getState());
        log.info("t3线程状态---->{}", t3.getState());
        log.info("t4线程状态---->{}", t4.getState());
        log.info("t5线程状态---->{}", t5.getState());
        log.info("t6线程状态---->{}", t6.getState());
    }
}
