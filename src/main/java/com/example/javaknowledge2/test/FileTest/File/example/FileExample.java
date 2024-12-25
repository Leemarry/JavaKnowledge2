package com.example.javaknowledge2.test.FileTest.File.example;

import com.example.javaknowledge2.test.FileTest.File.FileUtils;

public class FileExample {
    public static void main(String[] args) {
        String zipFilePath = "I:\\Administrator\\Pictures\\Pictures.zip";
        String outputFilePath = "I:\\Administrator\\Pictures\\photo";
        String inputOneFilePath = "I:\\Administrator\\Pictures\\2.png";
        FileUtils.AddFileToZip(inputOneFilePath, zipFilePath);
        System.out.println("Unzip file success!");

    }
}
