package com.yqc.bean;

import com.yqc.util.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Monitor {
    private Thread monitorThread;

    // 启动监控线程
    public void start() {
        monitorThread = new Thread(() -> {
            Thread currentThread = Thread.currentThread();

            while (true) {
                // 判断是否被其他线程打断
                if (currentThread.isInterrupted()) {
                    log.info("监控线程即将停止，进行停止前操作");
                    break;
                }

                // 未被其他线程打断，执行监控操作
                log.info("执行一次监控数据采集操作");

                // 每个1秒执行一次监控数据采集
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    // 阻塞状态下被打断，会清除【被打断标记】，所以再打断一次
                    currentThread.interrupt();
                }
            }
        }, "monitor");

        monitorThread.start();
    }

    // 停止监控线程
    public void stop() {
        monitorThread.interrupt();
    }

    public static void main(String[] args) {
        Monitor moniter = new Monitor();
        moniter.start();
        ThreadUtil.sleep(4000);
        moniter.stop();
    }
}
