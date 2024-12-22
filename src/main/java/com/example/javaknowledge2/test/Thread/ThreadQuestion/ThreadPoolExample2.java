package com.example.javaknowledge2.test.Thread.ThreadQuestion;

import com.example.javaknowledge2.demos.service.ThreadPoolManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ThreadPoolExample2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建一个线程池管理器，假设我们有4个线程
        ThreadPoolManager manager = new ThreadPoolManager(4);

        // 创建一个Callable任务列表
        List<Callable<Integer>> tasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            tasks.add(() -> {
                System.out.println("Task " + taskId + " is running by thread " + Thread.currentThread().getName());
                // 模拟任务执行时间并返回结果
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return taskId * taskId; // 返回一个计算结果
            });
        }

        // 提交Callable任务并收集结果
        List<Future<Integer>> futures = manager.submitCallableTasks(tasks);
        //#region 可以执行一些其他操作，比如等待其他任务完成，或者暂停线程等
        System.out.println("ppp");
        //#endregion

        // 等待所有任务完成并收集结果
        List<Integer> results = new ArrayList<>();
        for (Future<Integer> future : futures) {
            results.add(future.get()); // get()方法会阻塞直到任务完成
        }


        // 打印结果
        for (int result : results) {
            System.out.println("Result: " + result);
        }

        // 关闭线程池
        manager.shutdownExecutorService();
    }
}
