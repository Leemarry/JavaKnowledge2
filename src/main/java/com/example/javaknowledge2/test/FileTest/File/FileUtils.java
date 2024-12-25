package com.example.javaknowledge2.test.FileTest.File;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class FileUtils {
    //#region 文件拷贝
    // 拷贝 文件 到 另一个文件
    public static void FileCopy(String inputFile, String outputFile) {
        try {
            InputStream in = new FileInputStream(inputFile);
            OutputStream out = new FileOutputStream(outputFile);
            byte[] buffer = new byte[1024 * 1024];
            int readLen = 0;
            readLen = in.read(buffer);
            while (readLen != -1) {
                out.write(buffer, 0, readLen);
                readLen = in.read(buffer);
            }
            in.close();
            out.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 大文件拷贝
    public static void BigFileCopy(String inputFile, String outputFile) {
        try (FileInputStream fis = new FileInputStream(inputFile);
             FileOutputStream fos = new FileOutputStream(outputFile);
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

    //#endregion 文件拷贝

    //#region 文件解压
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

    // 压缩包读取指定文件 将压缩包中的指定文件 写入到本地
    public static void ZipFile1(String zipFilePath, String outputFilePath, String fileName) {
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
    //#endregion 文件解压

    //#region 文件压缩
    // 压缩 单个文件 到 一个压缩包内
    public static void ZipFile(String inputFilePath, String outputZipFilePath) {
        try{
            File file = new File(inputFilePath);
            if(!file.exists()){
                throw new RuntimeException("文件不存在");
            }
            String filename = file.getName();
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outputZipFilePath));
            InputStream in = new FileInputStream(inputFilePath);
            out.putNextEntry(new ZipEntry(filename));
            byte[] buffer = new byte[1024*1024];
            int readLen = 0;
            while ((readLen = in.read(buffer))!=-1){
                out.write(buffer,0,readLen);
            }
            in.close();
            out.close();
            System.out.println("File compressed successfully.");
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    // 向一个已有压缩包内添加文件 不影响原有压缩包内部文件
    public static void AddFileToZip(String inputFilePath, String outputZipFilePath, String fileName) {
        try{
            File file = new File(inputFilePath);
            if (!file.exists()) {
                throw new RuntimeException("文件不存在");
            }
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outputZipFilePath, true));
            InputStream in = new FileInputStream(inputFilePath);
            out.putNextEntry(new ZipEntry(fileName));
            byte[] buffer = new byte[1024 * 1024];
            int readLen = 0;
            while ((readLen = in.read(buffer)) != -1) {
                out.write(buffer, 0, readLen);
            }
            in.close();
            out.close();
            System.out.println("File added to zip successfully.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // 向一个已有压缩包内添加文件 不影响原有压缩包内部文件
    public static void AddFileToZip(String inputFilePath, String outputZipFilePath){
        try{
            File inputFile  = new File(inputFilePath);
            if(!inputFile.exists()){
                throw new RuntimeException("文件不存在");
            }
            InputStream in = new FileInputStream(inputFile);
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outputZipFilePath, true));
            String fileName = inputFile.getName();
            out.putNextEntry(new ZipEntry(fileName));
            byte[] buffer = new byte[1024 * 1024];
            int readLen = 0;
            while ((readLen = in.read(buffer)) != -1) {
                out.write(buffer, 0, readLen);
            }
            in.close();
            out.close();
            System.out.println("File added to zip successfully.");

        }catch (IOException e){

        }

    }

    // 压缩文件 到 压缩包 (压缩整个文件夹)
    public static void ZipFiles(String inputFilePath, String outputFilePath) {
        try{
            File file = new File(inputFilePath);
            if (!file.exists()) {
                throw new RuntimeException("文件不存在");
            }
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outputFilePath));
            File[] files = file.listFiles();
            for (File f : files) {
                if (f.isFile()) {
                    out.putNextEntry(new ZipEntry(f.getName()));
                    FileInputStream in = new FileInputStream(f);
                    byte[] buffer = new byte[1024 * 1024];
                    int readLen = 0;
                    while ((readLen = in.read(buffer)) != -1) {
                        out.write(buffer, 0, readLen);
                    }
                    in.close();
                }
            }
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //#endregion 文件压缩

    //#region 压缩包

    //#endregion 压缩包
    public static byte[] InputStreamToByteArray (InputStream inputStream) throws IOException {
        // 使用 ByteArrayOutputStream 来存储从 InputStream 读取的字节
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024]; // 缓冲区大小，可以根据需要调整
        int bytesRead;
        // 循环读取 InputStream 直到没有更多数据可读
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, bytesRead);
        }
        // 将 ByteArrayOutputStream 转换为字节数组
        byte[] byteArray = byteArrayOutputStream.toByteArray();

        // 关闭 ByteArrayOutputStream 和 InputStream（如果不再需要）
        byteArrayOutputStream.close();
        if (inputStream != null) {
            inputStream.close();
        }
        return byteArray;
    }



}
