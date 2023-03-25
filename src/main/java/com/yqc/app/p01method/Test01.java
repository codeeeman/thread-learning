package com.yqc.app.p01method;

import com.yqc.util.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test01 {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log.info("t1 running");
        }, "t1");

        log.info("t1 status -> {}", t1.getState());// NEW
        t1.start();
        t1.start();
        log.info("t1 status -> {}", t1.getState());// RUNNABLE，此时再调用t1.start()会报IllegalThreadStateException
    }
}
