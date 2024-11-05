package com.example.javaknowledge2.demos.controller;

import com.example.javaknowledge2.demos.service.ThreadPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class MyController {

    //http://127.0.0.1:8099/updateGoods
    @PostMapping("/updateGoods")
    @ResponseBody
    public void updateGoods(
            @RequestPart("meta")  Meta meta,
            @RequestPart("files") MultipartFile[] files) {
        System.out.println("meta"+meta);
        System.out.println("file"+files);
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "文件为空";
        }

        try {
            // 获取文件名并保存到指定目录
            String fileName = file.getOriginalFilename();
            File dest = new File("E:\\Amireux\\Music\\temp\\" + fileName); // 替换为你的保存路径
            file.transferTo(dest);

            return "文件上传成功：" + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return "文件上传失败：" + e.getMessage();
        }
    }

    //http://127.0.0.1:8080/submit-task
    @GetMapping("/submit-task")
    public String submitTaskToThreadPool() throws Exception {
        // 假设你有一个方式来获取或创建ThreadPoolServiceImpl的实例（比如通过Spring）
        ThreadPoolService threadPoolService = new ThreadPoolService(5);
        // 创建一个Runnable任务
        Runnable task = () -> {
            // 模拟耗时操作
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("异步任务执行完成！");
        };

        // 提交任务到线程池
        threadPoolService.submitTask(task);

        // 返回响应，任务将在后台异步执行
        return "任务已提交到线程池！";
    }


    @GetMapping("/submit-task20")
    public String submitTaskToThreadPool20() throws Exception {
        // 假设你有一个方式来获取或创建ThreadPoolServiceImpl的实例（比如通过Spring）
        int num= 5;int numThread =3;
        ExecutorService executorService = Executors.newFixedThreadPool(numThread);
        AtomicInteger taskCount = new AtomicInteger(0);
        for (int i = 0; i < num; i++) {
            // 创建一个Runnable任务
            Runnable task = () -> {
                // 模拟耗时操作
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }finally {
                    taskCount.decrementAndGet();
                }
                System.out.println("异步任务执行完成！");
            };
            // 提交任务到线程池
            executorService.submit(task);
            taskCount.incrementAndGet();
        }

        // 等待所有任务完成
        while (taskCount.get() > 0) {
            Thread.sleep(100);
        }
        // 关闭线程池
        executorService.shutdown();
        // 返回响应，任务将在后台异步执行
        return "任务已提交到线程池！";
    }
}
