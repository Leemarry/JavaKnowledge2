package com.example.javaknowledge2.test.FileTest.File.example;

import com.example.javaknowledge2.test.FileTest.File.FileUtils;

public class FileExample {
    public static void main(String[] args) {
        String zipFilePath = "I:\\Administrator\\Pictures\\Pictures.zip";
        String outputFilePath = "I:\\Administrator\\Pictures";
        FileUtils.ZipFile(zipFilePath, outputFilePath,"一张图片多大.mp4");
        System.out.println("Unzip file success!");

    }
}
