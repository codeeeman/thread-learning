package com.yqc.app.p01method;

import com.yqc.util.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

@Slf4j
public class Test07 {

    private static int count = 0;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
               if (Thread.currentThread().isInterrupted()) {
                   break;
               }
            }

            log.info("{}---->线程结束", Thread.currentThread().getName());
        }, "t1");
        // 设置t1线程为守护线程，当其他线程结束后，t1线程会强制终止
        t1.setDaemon(true);
        t1.start();

        ThreadUtil.sleep(2000);
        log.info("{}---->线程结束", Thread.currentThread().getName());
    }
}
