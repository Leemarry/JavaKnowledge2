package com.example.javaknowledge2.test.Cshi;

import java.io.*;
import java.util.zip.*;

public class ExtractSpecificFile {
    public static void main(String[] args) {
        String zipFilePath = "E:\\Amireux\\Downloads\\1224.zip";
        String entryName = "2号2.kmz";
        String outputPath = "E:\\Amireux\\Downloads\\2号2.kmz";

        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry zipEntry = zis.getNextEntry();
            System.out.println("zipEntry: " + zipEntry.getName());
            while (zipEntry != null) {
                if (zipEntry.getName().equals(entryName)) {
                    try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outputPath))) {
                        byte[] bytesIn = new byte[4096];
                        int read;
                        while ((read = zis.read(bytesIn)) != -1) {
                            bos.write(bytesIn, 0, read);
                        }
                    }
                    break; // 假设只需要一个文件，找到后退出循环
                }
                zipEntry = zis.getNextEntry();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}