package com.yqc.app.p01method;

import com.yqc.util.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class Test02 {

    // 其它线程可以使用 interrupt 方法打断正在睡眠的线程，这时 sleep 方法会抛出 InterruptedException
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log.info("t1 enter sleep");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {// 被打断抛出InterruptedException
                log.info("t1 sleep is interrupted");
                e.printStackTrace();
            }
            log.info("t1 go on");
        }, "t1");

        log.info("t1 status -> {}", t1.getState());// NEW
        t1.start();
        ThreadUtil.sleep(500);
        log.info("t1 status -> {}", t1.getState());// TIMED_WAITING
        t1.interrupt();// 打断正在睡眠的它1
    }
}
