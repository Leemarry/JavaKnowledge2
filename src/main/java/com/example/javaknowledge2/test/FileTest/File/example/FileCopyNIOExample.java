package com.example.javaknowledge2.test.FileTest.File.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileCopyNIOExample {
    public static void main(String[] args) {
        String sourceFilePath =  "I:\\Administrator\\Pictures\\一张图片多大.mp4";  // 源文件路径
        String destinationFilePath =  "I:\\Administrator\\Pictures\\一张图片多大8.mp4";  // 源文件路径

        try (FileInputStream fis = new FileInputStream(sourceFilePath);
             FileOutputStream fos = new FileOutputStream(destinationFilePath);
             FileChannel sourceChannel = fis.getChannel();
             FileChannel destinationChannel = fos.getChannel()) {

            long size = sourceChannel.size();
            long position = 0;
            long transferred = 0;

            while (transferred < size) {
                transferred += sourceChannel.transferTo(position, size - transferred, destinationChannel);
                position += transferred;
            }

            System.out.println("File copied successfully using NIO.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}