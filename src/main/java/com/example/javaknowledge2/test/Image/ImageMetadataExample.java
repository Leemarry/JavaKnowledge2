//package com.example.javaknowledge2.test.Image;
//
//import javax.imageio.ImageIO;
//import javax.imageio.ImageReader;
//import javax.imageio.stream.ImageInputStream;
//import javax.imageio.metadata.IIOMetadata;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.Iterator;
//
//public class ImageMetadataExample {
//    public static void main(String[] args) {
//        try {
//            File file = new File("F:\\3d\\model\\格林美\\images\\survey\\0ab3c9a969b07b84b856acda90cb7ae843207bc2.JPG");
//            FileInputStream fis = new FileInputStream(file);
//
//            Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("jpg");
//            if (readers.hasNext()) {
//                ImageReader reader = readers.next();
//                reader.setInput(ImageIO.createImageInputStream(fis), false);
//                IIOMetadata metadata = reader.getImageMetadata(0);
//                String[] names = metadata.getMetadataFormatNames();
//
//                for (String name : names) {
//                    System.out.println("Metadata format: " + name);
//                }
//
//                // 示例：打印EXIF信息（如果图片有的话）
//                if (metadata.isStandardMetadataFormatSupported()) {
//                    String exif = (String) metadata.getAsTree("javax_imageio_exif_1.0");
//                    System.out.println("EXIF: " + exif);
//                }
//            }
//
//            fis.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}