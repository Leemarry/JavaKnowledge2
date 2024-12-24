package com.example.javaknowledge2.test.FileTest.File.example;

import java.io.*;

public class FileCopyExample1 {
    public static void main(String[] args) {
        // 定义一个字符串变量 sourceFilePath，存储源文件的路径
        String sourceFilePath = "I:\\Administrator\\Pictures\\一张图片多大.mp4";
// 定义一个字符串变量 destinationFilePath，存储目标文件的路径
        String destinationFilePath = "I:\\Administrator\\Pictures\\一张图片多大2.mp4";

        try{
            // 创建一个 FileInputStream 对象 inputStream，用于从源文件读取数据
            InputStream inputStream = new FileInputStream(sourceFilePath);
            // 创建一个 FileOutputStream 对象 outputStream，用于向目标文件写入数据
            OutputStream outputStream = new FileOutputStream(destinationFilePath);
            // 创建一个大小为 1M 的字节数组作为缓冲区
            byte[] buffer = new byte[1024*1024];
            // 定义一个变量 bufferLength，用于存储每次读取的字节数
            int bufferLength = 0;
            // 循环从输入流读取数据到缓冲区，并将读取的字节数存储在 bufferLength 中
            // 当读取到文件末尾时，read 方法会返回 -1，循环结束
//            while ((bufferLength = inputStream.read(buffer))!= -1){
//                System.out.println("正在写入数据..."+bufferLength);
//                // 将缓冲区中的数据写入输出流，从索引 0 开始，写入 bufferLength 个字节
//                outputStream.write(buffer,0,bufferLength);
//            }
//            while (bufferLength!= -1){
//                // 将缓冲区中的数据写入输出流，从索引 0 开始，写入 bufferLength 个字节
//                outputStream.write(buffer,0,bufferLength);
//                // 继续读取下一部分数据，更新 bufferLength 的值
//                bufferLength = inputStream.read(buffer);
//            }
            bufferLength = inputStream.read(buffer);
            while (bufferLength!= -1){
                // 将缓冲区中的数据写入输出流，从索引 0 开始，写入 bufferLength 个字节
                outputStream.write(buffer,0,bufferLength);
                // 继续读取下一部分数据，更新 bufferLength 的值
                bufferLength = inputStream.read(buffer);
            }
            System.out.println("数据写入完成！"+bufferLength);
            // 关闭输入流
            inputStream.close();
            // 关闭输出流
            outputStream.close();

        }catch(IOException e){
            // 打印异常堆栈信息，以便调试
            e.printStackTrace();
        }


    }
}