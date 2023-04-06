package com.yqc.app.p02monitor;

import com.yqc.bean.Counter;
import com.yqc.util.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test01 {
    public static int count = 0;

    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                counter.add();
            }
            log.info("t1 end...");
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                counter.sub();
            }
            log.info("t2 end...");
        }, "t2");

        t1.start();
        t2.start();
        ThreadUtil.sleep(2000);
        log.info("count--->{}", counter.getCount());
    }
}
