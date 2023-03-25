package com.yqc.app.p01method;

import com.yqc.util.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test04 {

    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.info("t1 start");
            ThreadUtil.sleep(1000);
            count = 10;
            log.info("t1 end");
        }, "t1");

        t1.start();
        t1.join();// 阻塞，同步t1线程，等t1线程执行完后再继续执行
        log.info("count--->{}", count);// 10
    }
}
