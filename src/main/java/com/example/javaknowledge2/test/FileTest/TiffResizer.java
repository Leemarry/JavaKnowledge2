package com.example.javaknowledge2.test.FileTest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

// 用于调整TIFF文件的大小并保存 存在黑边
public class TiffResizer {

    public static void resizeTiff(String inputPath, String outputPath, int newWidth, int newHeight) {
        try {
            InputStream in = new FileInputStream(inputPath);
            // 读取原始TIFF文件
            BufferedImage originalImage = ImageIO.read(in);

            // 计算新的宽度和高度（如果需要的话，可以保持宽高比）
            // 这里为了示例直接使用了给定的newWidth和newHeight

            // 创建一个新的BufferedImage来存储调整后的图像
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

            // 使用Graphics2D来绘制缩放后的图像
            resizedImage.getGraphics().drawImage(originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);

            // 使用LZW或其他压缩算法写入TIFF文件
            // 注意：ImageIO默认可能不支持TIFF的LZW压缩，这可能需要额外的库或自定义处理
            // 这里我们仅使用ImageIO的默认行为，它可能会使用JPEG或其他压缩
            ImageIO.write(resizedImage, "TIFF", new File(outputPath));

            System.out.println("TIFF file resized and saved to: " + outputPath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        String inputPath = "F:\\GeoServerData\\loveFact.tif";
        String outputPath = "F:\\GeoServerData\\loveFactoutput.tif";
        int newWidth = 1075; // 根据需要调整
        int newHeight = 927; // 根据需要调整，或者根据原始宽高比计算

        resizeTiff(inputPath, outputPath, newWidth, newHeight);
    }
}
