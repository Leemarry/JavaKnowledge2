//package com.example.javaknowledge2.test.FileTest.Tif;
//
//import org.apache.commons.imaging.ImageReadException;
//import org.apache.commons.imaging.ImageWriteException;
//import org.apache.commons.imaging.common.ImageMetadata;
//import org.apache.commons.imaging.formats.tiff.TiffImageMetadata;
//import org.apache.commons.imaging.formats.tiff.write.TiffOutputField;
//import org.apache.commons.imaging.formats.tiff.write.TiffOutputSet;
//import org.apache.commons.imaging.Imaging;
//import org.apache.commons.imaging.ImagingConstants;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.List;
//
//public class TiffResizer {
//
//    public static void resizeTiff(File inputFile, File outputFile, int targetWidth, int targetHeight) throws IOException, ImageReadException, ImageWriteException {
//        // 使用Apache Commons Imaging读取TIFF文件
//        BufferedImage image = null;
//        try (FileInputStream fis = new FileInputStream(inputFile)) {
//            image = Imaging.getBufferedImage(fis);
//        }
//
//        // 检查是否成功读取图像
//        if (image == null) {
//            throw new RuntimeException("Failed to read TIFF image");
//        }
//
//        // 计算缩放比例（这里简化为等比例缩放）
//        double scaleX = (double) targetWidth / image.getWidth();
//        double scaleY = (double) targetHeight / image.getHeight();
//        double scale = Math.min(scaleX, scaleY);
//
//        // 创建一个新的缩放后的图像
//        int newWidth = (int) (image.getWidth() * scale);
//        int newHeight = (int) (image.getHeight() * scale);
//        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
//        resizedImage.getGraphics().drawImage(image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
//
//        // 这里没有直接使用Apache Commons Imaging来写TIFF，因为它在写TIFF时可能不如ImageIO方便（尽管ImageIO对TIFF的支持有限）
//        // 但你可以使用Apache Commons Imaging的API来添加特定的TIFF元数据或压缩选项
//        // 如果你需要更高级的TIFF处理，你可能需要查阅Apache Commons Imaging的文档
//
//        // 使用ImageIO保存缩放后的图像为TIFF（注意：这可能不会生成小于10MB的文件）
//        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
//            // 注意：ImageIO可能不支持TIFF的所有压缩选项
//            ImageIO.write(resizedImage, "TIFF", fos);
//            // 如果需要更复杂的TIFF处理（如压缩），你可能需要使用Apache Commons Imaging的特定API
//        }
//
//        // 注意：上面的代码没有直接控制文件大小到小于10MB
//        // 要实现这一点，你可能需要尝试不同的分辨率、颜色深度或压缩设置，并检查输出文件的大小
//    }
//
//    public static void main(String[] args) {
//        try {
//            File inputFile = new File("path/to/your/input.tif");
//            File outputFile = new File("path/to/your/output.tif");
//            resizeTiff(inputFile, outputFile, 800, 600); // 假设你希望输出图像的分辨率为800x600
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}