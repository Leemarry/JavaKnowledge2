package com.example.javaknowledge2.test.FileTest.File;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class FileUtils {
    // 拷贝 文件 到 另一个文件
    public static void FileCopy(String inputFile, String outputFile) {
        try{
            InputStream in = new FileInputStream(inputFile);
            OutputStream out = new FileOutputStream(outputFile);
            byte[] buffer = new byte[1024*1024];
            int readLen = 0;
            readLen = in.read(buffer);
            while(readLen!= -1){
                out.write(buffer, 0, readLen);
                readLen = in.read(buffer);
            }
            in.close();
            out.close();

        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    // 读取压缩包中的文件 到 本地
    public static void UnzipFile(String zipFilePath, String outputFilePath) {
        try {
            File file = new File(zipFilePath);
            if (!file.exists()) {
                throw new RuntimeException("文件不存在");
            }
            ZipInputStream in = new ZipInputStream(new FileInputStream(zipFilePath));
            ZipEntry entry = null;
            while ((entry = in.getNextEntry()) != null) {
                String fileName = entry.getName();
                if (fileName.endsWith("/")) {
                    continue;
                }
                File outFile = new File(outputFilePath + File.separator + fileName);
                if (!outFile.exists()) {
                    outFile.mkdirs();
                }
                FileOutputStream out = new FileOutputStream(outFile);
                byte[] buffer = new byte[1024 * 1024];
                int readLen = 0;
                while ((readLen = in.read(buffer)) != -1) {
                    out.write(buffer, 0, readLen);
                }
                out.close();
            }
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // 压缩包读取指定文件 将压缩包中的文件 写入到本地
    public static void ZipFile(String zipFilePath, String outputFilePath, String fileName) {
        try {
            File file = new File(outputFilePath);
            if (!file.exists()) {
                throw new RuntimeException("文件不存在");
            }
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFilePath));
            File inputFile = new File(outputFilePath + File.separator + fileName);
            if (!inputFile.exists()) {
                throw new RuntimeException("文件不存在");
            }
            out.putNextEntry(new ZipEntry(fileName));
            FileInputStream in = new FileInputStream(inputFile);
            byte[] buffer = new byte[1024 * 1024];
            int readLen = 0;
            while ((readLen = in.read(buffer)) != -1) {
                out.write(buffer, 0, readLen);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
