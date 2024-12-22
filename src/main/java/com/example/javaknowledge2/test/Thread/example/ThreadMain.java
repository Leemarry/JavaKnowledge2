package com.example.javaknowledge2.test.Thread.example;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadMain {
    private static ExecutorService executorService = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    private static AtomicInteger taskCount = new AtomicInteger(0);
    private static CountDownLatch latch;

    public static void main(String[] args) {
        latch = new CountDownLatch(2); // 初始化为2，因为有2个任务
        List<Future<Boolean>> futureList = new ArrayList<>(); // 用于存储任务的Future对象

        // 当前时间
        long currentTime = System.currentTimeMillis();
        for (int i = 0; i < 2; i++) {
            Future<Boolean> future = beforePublishCloud21();
            futureList.add(future);
        }

        try {
            latch.await(); // 等待所有任务完成
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("总耗时：" + (endTime - currentTime) + "ms");

        // 获取并处理每个任务的返回结果
        for (Future<Boolean> future : futureList) {
            try {
                Boolean result = future.get();
                System.out.println("任务返回结果: " + result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println("main");
        executorService.shutdown(); // 所有任务完成后关闭服务

    }

    public static Future<Boolean> beforePublishCloud21() {
        Callable<Boolean> task = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                // 随机睡眠1-10秒
                long sleepTime = (long) (Math.random() * 10000);
                Thread.sleep(sleepTime);
                System.out.println("beforePublishCloud2:" + sleepTime);
                latch.countDown(); // 每个任务完成后减少计数
                // 返回随机布尔值，用于测试
                return Math.random() < 0.5;
            }
        };

        Future<Boolean> future = executorService.submit(task);
        taskCount.incrementAndGet(); // 仅用于跟踪提交的任务数量，不用于关闭服务决策
        return future;
    }
}