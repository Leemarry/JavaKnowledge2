package com.example.javaknowledge2.test.Thread.example;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadMain2 {
    private static ExecutorService executorService = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    private static AtomicInteger taskCount = new AtomicInteger(0);
    private static CountDownLatch latch;
    public static void main(String[] args) {
        //当前时间
        long currentTimeMillis = System.currentTimeMillis();
        latch = new CountDownLatch(2); // 初始化为2，因为有2个任务
        for (int i = 0; i < 2; i++) {
            beforePublishCloud2();
        }

        // 计算任务执行时间
        long endTimeMillis = System.currentTimeMillis();
        long executeTimeMillis = endTimeMillis - currentTimeMillis;
        System.out.println("executeTimeMillis:" + executeTimeMillis+"ms");
        System.out.println("main");
        executorService.shutdown(); // 所有任务完成后关闭服务


    }
    public static Boolean beforePublishCloud2() {
        try {
            // 有返回值 Callable
            Callable<Boolean> task = new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    // 在这里编写具体的任务逻辑
                    Thread.sleep(10000);
                    System.out.println("beforePublishCloud2");
                    return true;
                }
            };
            Future<Boolean> future = executorService.submit(task);
            taskCount.incrementAndGet();
            // 等待任务完成，并获取结果
            Boolean result = future.get();

            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally{
            if(taskCount.getAndIncrement()==0){
                executorService.shutdown();
            }
        }
    }

    public static void beforePublishCloud21() {
        Callable<Boolean> task = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                // 随机睡眠1-10秒
                long sleepTime = (long) (Math.random() * 10000);
                Thread.sleep(sleepTime);
                System.out.println("beforePublishCloud2:"+sleepTime);
                latch.countDown(); // 每个任务完成后减少计数
                // 返回随机布尔值，用于测试
                return Math.random() < 0.5;
            }
        };

        executorService.submit(task);
        taskCount.incrementAndGet(); // 仅用于跟踪提交的任务数量，不用于关闭服务决策
    }


    public static Boolean beforePublishCloud() {
        try {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("beforePublishCloud");
                    Thread.currentThread().interrupt();
                }
            });
            thread.start();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static Boolean beforePublishCloud3() {
        try {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("beforePublishCloud3");
                    Thread.currentThread().interrupt();
                }
            });
            thread.start();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }



}
