package com.example.javaknowledge2.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ReadZipFileAndWriteToLocal {
    public static void main(String[] args) {
        // 压缩包文件路径
        String zipFilePath = "E:\\Amireux\\Downloads\\1224.zip";
        // 压缩包内要读取的指定文件名
        String targetFileName = "2号2.kmz";
        // 要写入到本地的文件路径（可自行调整）
        String outputFilePath = "E:\\Amireux\\Downloads\\2号2.kmz";

        try {
            // 创建ZipFile对象来代表压缩包
            ZipFile zipFile = new ZipFile(zipFilePath);
            // 获取压缩包内的指定文件对应的ZipEntry
            ZipEntry targetEntry = zipFile.getEntry(targetFileName);
            if (targetEntry!= null) {
                // 打开输入流读取压缩包内文件数据
                InputStream inputStream = zipFile.getInputStream(targetEntry);
                // 创建输出流，用于写入本地文件
                FileOutputStream outputStream = new FileOutputStream(new File(outputFilePath));

                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }

                inputStream.close();
                outputStream.close();
                System.out.println("文件提取并写入本地成功！");
            } else {
                System.out.println("在压缩包中未找到指定文件");
            }
            zipFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}