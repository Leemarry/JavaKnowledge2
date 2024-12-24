package com.example.javaknowledge2.test.FileTest.File.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileCopyExample {
    public static void main(String[] args) {
        String sourceFilePath = "I:\\Administrator\\Pictures\\一张图片多大.mp4";  // 源文件路径
        String destinationFilePath = "I:\\Administrator\\Pictures\\一张图片多大2.mp4";  // 目标文件路径
        try {
            // 读取源文件内容
            byte[] fileContent = Files.readAllBytes(Paths.get(sourceFilePath));

            // 将内容写入目标文件
            Files.write(Paths.get(destinationFilePath), fileContent);

            System.out.println("文件复制成功！");
        } catch (IOException e) {
            System.err.println("文件操作失败: " + e.getMessage());
        }
    }
}