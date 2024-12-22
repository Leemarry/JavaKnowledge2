package com.example.javaknowledge2.test.FileTest.Image;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class ImageScaler {
    public static void main(String[] args) {
//        try {
//            // 读取原始图片
//            File input = new File("E:\\Amireux\\Pictures\\Camera Roll\\17283937185975.jpeg");
//            BufferedImage originalImage = ImageIO.read(input);
//
//            // 计算缩放后的尺寸
//            int scaledWidth = (int) (originalImage.getWidth() * 0.5);
//            int scaledHeight = (int) (originalImage.getHeight() * 0.5);
//
//            // 创建一个空的BufferedImage用于存放缩放后的图片
//            BufferedImage scaledImage = new BufferedImage(scaledWidth, scaledHeight, originalImage.getType());
//
//            // 使用Graphics2D进行缩放
//            Graphics2D g2d = scaledImage.createGraphics();
//            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//            g2d.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
//            g2d.dispose();
//
//            // 保存缩放后的图片到文件
//            File output = new File("E:\\Amireux\\Pictures\\Camera Roll\\1.jpeg");
//            ImageIO.write(scaledImage, "jpg", output);
//
//            System.out.println("图片缩放成功！");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        try{
            File input = new File("E:\\Amireux\\Pictures\\Camera Roll\\17283937185975.jpeg");
            InputStream is = new FileInputStream(input);
            byte[] bytes =  compressImage(is, 100, 100);
            FileOutputStream fos = new FileOutputStream("E:\\Amireux\\Pictures\\Camera Roll\\11.jpeg");
            fos.write(bytes);
            fos.close();
            System.out.println("图片压缩成功！");
        }catch(Exception e){
            e.printStackTrace();
        }

    }


    // 写方法：按比例压缩图片 返回压缩后的图片流
    public static byte[] compressImage(InputStream is, int width,int height){
        try{
            BufferedImage src = ImageIO.read(is);
            Image dest = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = tag.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(dest, 0, 0, null);
            g.dispose();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(tag, "jpg", os);
            return os.toByteArray();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

}