package com.example.javaknowledge2.test.ThreadQuestion;

import com.example.javaknowledge2.demos.service.ThreadPoolManager;

import java.util.ArrayList;
import java.util.List;

public class ThreadPoolExample {

    public static void main(String[] args) throws InterruptedException {
        // 创建一个线程池管理器，假设我们有4个线程
        ThreadPoolManager manager = new ThreadPoolManager(4);

        // 创建一个Runnable任务列表
        List<Runnable> tasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            tasks.add(() -> {
                System.out.println("Task " + taskId + " is running by thread " + Thread.currentThread().getName());
                // 模拟任务执行时间
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // 提交任务并等待它们全部完成
        manager.submitRunnableTasksAndWait(tasks);


        // 所有任务都已完成，现在可以进行后续操作
        System.out.println("All tasks have completed.");

        // 关闭线程池
        manager.shutdownExecutorService();
    }
}