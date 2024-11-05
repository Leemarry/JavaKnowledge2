package com.example.javaknowledge2.demos.service;


import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class ThreadPoolService {

    private final ExecutorService executorService;

    // 默认线程池大小为3
    public ThreadPoolService() {
        this(3);
    }

    // 允许通过参数设置线程池大小
    public ThreadPoolService(int poolSize) {
        this.executorService = Executors.newFixedThreadPool(poolSize);
    }



    // 封装一个方法用于提交Runnable任务到线程池
    public Future<?> submitTask(Runnable task) {
        return executorService.submit(task);
    }

    // 封装一个方法用于提交Callable任务到线程池并获取结果
    public <T> Future<T> submitTask(Callable<T> task) {
        return executorService.submit(task);
    }

    // 当应用程序关闭时，关闭线程池
    @PreDestroy
    public void shutdown() {
        System.out.println("Shutting down thread pool...");
        executorService.shutdown(); // 尝试停止所有正在执行的任务，等待它们完成后关闭
        // 如果你想立即停止所有任务，可以使用executorService.shutdownNow();
    }
}