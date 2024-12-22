package com.example.javaknowledge2.test.Thread;
import java.util.List;
import java.util.concurrent.*;

import java.util.concurrent.*;

public class Cs {

    public static void main(String[] args) throws InterruptedException {
        // 创建一个固定大小的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // 提交任务并获取 Future 对象
        Future<?> future = executorService.submit(new Runnable() {
            @Override
            public void run() {
                read();
            }
        });

        // 等待一些时间进行示例
        Thread.sleep(5000); // 休眠5秒，让任务有时间执行

        System.out.println("尝试强制关闭线程池...");
        // 强制关闭线程池
        List<Runnable> pendingTasks = executorService.shutdownNow();

        // 输出未执行任务的数量
        System.out.println("未执行的任务数量: " + pendingTasks.size());

        // 判断任务的完成状态
        if (future.isDone()) {
            System.out.println("提交的任务已经完成.");
        } else {
            System.out.println("提交的任务仍在运行.");
        }
    }

    public static void read() {
        try {
            while (true) {
                Thread.sleep(3000); // 暂停3秒
                System.out.println("read");
            }
        } catch (InterruptedException e) {
            System.out.println("任务被中断，结束读操作.");
            // 这里可以做一些清理操作
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}