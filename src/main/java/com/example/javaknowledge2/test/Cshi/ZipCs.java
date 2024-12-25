package com.example.javaknowledge2.test.Cshi;

import java.io.*;
import java.sql.SQLOutput;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class ZipCs {
    public static void main(String[] args) {
        // 压缩包文件路径
        String zipFilePath = "E:\\Amireux\\Pictures\\ooo.zip";
        // 压缩包内要读取的指定文件名
        String targetFileName = "ooo/1 (1).png";
        // 要写入到本地的文件路径（可自行调整）
        String outputFilePath = "E:\\Amireux\\Pictures\\ooo.png";
        // 调用查找并写入方法
         FindAndWrite(zipFilePath, targetFileName, outputFilePath);

        String zipFilePath2 = "E:\\Amireux\\Pictures\\dahongtian.kmz";
        String targetFileName2 = "waylines.wpml";
        readZipFile(zipFilePath2, targetFileName2);
//        unzipAndExtract(zipFilePath2, "E:\\Amireux\\Pictures\\", targetFileName2);
    }

    public static void unzipAndExtract(String zipFilePath, String outputDir, String targetFileName) {
        byte[] buffer = new byte[1024];
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry!= null) {
                String fileName = zipEntry.getName();
                if (fileName.toLowerCase().endsWith(targetFileName.toLowerCase())) {
                    File newFile = new File(outputDir + File.separator + targetFileName);
                    try (FileOutputStream fos = new FileOutputStream(newFile)) {
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    }
                }
                zis.closeEntry();
                zipEntry = zis.getNextEntry();
            }
            zis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 读取kmz文件内的指定文件内容
    public static void readZipFile(String zipFilePath, String targetFileName) {
        try(ZipFile zipFile = new ZipFile(zipFilePath)){

            ZipEntry entry = zipFile.getEntry("wpmz/waylines.wpml");
            if(entry==null){
                System.out.println("文件不存在！");
                return;
            }
            // 读取文件内容ok
            String content = "";
//            try(InputStreamReader inputStreamReader = new InputStreamReader(zipFile.getInputStream(entry))){
//                int len = 0;
//                char[] chars = new char[1024];
//                while ((len = inputStreamReader.read(chars))!= -1) {
//                    content += new String(chars, 0, len);
//                }
//            }
            try (InputStream inputStream = zipFile.getInputStream(entry);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    content += line + "\n";
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(content);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
//            if(inputStream!=null){
//                try {
//                    inputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
        }

    }
    // 查找指定压缩包内的文件并写入到本地
    public static void FindAndWrite(String zipFilePath, String targetFileName, String outputFilePath) {
        try(ZipInputStream zipInputStream =new ZipInputStream(new FileInputStream(zipFilePath))){
            ZipEntry entry=null;
            while ((entry=zipInputStream.getNextEntry())!=null){
                if(entry.getName().equals(targetFileName)){
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    try(FileOutputStream outputStream = new FileOutputStream(outputFilePath)){
                        while ((len = zipInputStream.read(buffer)) != -1){
                            outputStream.write(buffer, 0, len);
                        }
                    }
                    break;
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
