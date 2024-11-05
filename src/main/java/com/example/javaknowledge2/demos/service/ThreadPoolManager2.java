package com.example.javaknowledge2.demos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolManager2 {

    private final ExecutorService executorService;

    public ThreadPoolManager2(int numThreads) {
        this.executorService = Executors.newFixedThreadPool(numThreads);
    }

    // 封装一个方法用于提交Runnable任务到线程池，并等待所有任务完成
    public void submitRunnableTasksAndWait(List<Runnable> tasks) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(tasks.size());
        for (Runnable task : tasks) {
            executorService.submit(() -> {
                try {
                    task.run();
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await(); // 等待所有任务完成
    }

    // 封装一个方法用于提交Callable任务到线程池并获取结果，但不等待所有任务完成
    public <T> List<Future<T>> submitCallableTasks(List<Callable<T>> tasks) {
        List<Future<T>> futures = new ArrayList<>(tasks.size());
        for (Callable<T> task : tasks) {
            futures.add(executorService.submit(task));
        }
        return futures; // 返回Future列表，调用者可以后续获取结果
    }

    // 封装一个方法等待Callable任务列表中的所有任务完成
    public <T> void awaitCallableTasksCompletion(List<Future<T>> futures) throws InterruptedException, ExecutionException {
        for (Future<T> future : futures) {
            future.get(); // 等待每个任务完成并可能抛出异常
        }
    }

    // 封装一个方法用于关闭ExecutorService
    public void shutdownExecutorService() {
        executorService.shutdown(); // 开始关闭流程
        try {
            // 等待所有任务完成，最多等待60秒
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                // 如果60秒后还有任务没有完成，则强制关闭
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            // 如果在等待过程中被中断，则尝试强制关闭
            executorService.shutdownNow();
            // 重新抛出中断异常
            Thread.currentThread().interrupt();
        }
    }
}