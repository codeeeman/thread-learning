package com.yqc.app.p01method;

import com.yqc.util.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class Test03 {

    // 其它线程可以使用 interrupt 方法打断正在睡眠的线程，这时 sleep 方法会抛出 InterruptedException
    public static void main(String[] args) {



        Thread t1 = new Thread(() -> {
            int count01 = 0;
            while (true) {
                //Thread.yield();
                System.out.println("t1---------->" + ++count01);
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            int count02 = 0;
            while (true) {
                System.out.println("t2---->" + ++count02);
            }
        }, "t1");

        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);

        t1.start();
        t2.start();
    }
}
