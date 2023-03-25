package com.yqc.app.p01method;

import com.yqc.util.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

@Slf4j
public class Test06 {

    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.info("park1 begin");
            LockSupport.park();
            log.info("park1 end");

            log.info("t1.interrupted--->{}", Thread.currentThread().isInterrupted());// t1线程【打断标记】为true

            log.info("park2 begin");

            LockSupport.park();
            log.info("park2 end");

            log.info("Thread.interrupted--->{}", Thread.interrupted());// t1线程【打断标记】为true，且【打断标记】被清空，为false

            log.info("park3 begin");
            // 此时t1【打断标记】为false，park()方法会生效
            LockSupport.park();
            log.info("park3 end");
        }, "t1");

        t1.start();
        ThreadUtil.sleep(2000);
        t1.interrupt();// 会打断park状态，t1继续执行
        ThreadUtil.sleep(2000);
        t1.interrupt();// 会打断park状态，t1继续执行
    }
}
