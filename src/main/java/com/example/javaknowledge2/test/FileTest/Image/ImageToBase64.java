package com.example.javaknowledge2.test.FileTest.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

public class ImageToBase64 {

    public static String convertImageToBase64(String imagePath) {

        // 使用try-with-resources自动管理资源
        try (FileInputStream imageInFile = new FileInputStream(imagePath)) {
            // 读取文件内容到字节数组
            byte imageData[] = new byte[(int) new File(imagePath).length()];
            imageInFile.read(imageData);

            // 对字节数组进行Base64编码
            return Base64.getEncoder().encodeToString(imageData);

        } catch (IOException ex) {
            System.out.println("读取文件时发生错误：" + ex.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        // 假设你有一个图片文件路径
        String imagePath = "E:\\Amireux\\Pictures\\Camera Roll\\17283937185975.jpeg";
        //
        // C:\Users\Administrator\Downloads\DJI_20240708184802_0010_F.JPG

        // 检查文件路径是否为空
        if (imagePath == null || imagePath.isEmpty()) {
            throw new IllegalArgumentException("图片路径不能为空");
        }

        String imageBase64 = convertImageToBase64(imagePath);

        if (imageBase64 != null) {
            System.out.println("Base64编码的图片字符串：");
            System.out.println(imageBase64);
        }
    }

}
