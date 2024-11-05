package com.example.javaknowledge2.test.FileTest;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TifCompressor {

    public static byte[] compressTif(String filePath) {
        try {
            BufferedImage image = ImageIO.read(new File(filePath));

            ImageWriter writer = ImageIO.getImageWritersByFormatName("TIFF").next();
            ImageWriteParam param = writer.getDefaultWriteParam();
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionType("LZW");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageOutputStream ios = ImageIO.createImageOutputStream(baos);
            writer.setOutput(ios);
            writer.write(null, new javax.imageio.IIOImage(image, null, null), param);

            ios.close();
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String filePath = "F:\\GeoServerData\\loveFact.tif";
        String outputPath = "F:\\GeoServerData\\loveFactoutput.tif";

        // 压缩TIFF文件
        byte[] compressedBytes = compressTif(filePath);
        if (compressedBytes != null) {
            // 将压缩后的字节写入到输出文件
            try (FileOutputStream fos = new FileOutputStream(outputPath)) {
                fos.write(compressedBytes);
                System.out.println("TIFF file compressed and saved to: " + outputPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed to compress TIFF file.");
        }
    }
}
