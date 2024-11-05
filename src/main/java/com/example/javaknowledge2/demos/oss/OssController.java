package com.example.javaknowledge2.demos.oss;

;
import com.example.javaknowledge2.demos.oss.service.IOssService;
import com.example.javaknowledge2.demos.oss.service.impl.MinIOServiceImpl;
import com.example.javaknowledge2.demos.oss.storage.OssConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;


@RestController
public class OssController {

    @Autowired
    private IOssService ossService;

    @RequestMapping("/html1")
    public String html() {
        return "index.html";
    }

    // http://127.0.0.1:8099/uploadFile?path=D:/test.txt
    @RequestMapping("/uploadFile")
    public  String uploadFile(String path) {
        File file = new File(path);
        FileInputStream inputStream = null;
        try {
            String fileName = file.getName();
            String objectKey = "ceshi/"+fileName;
            inputStream = new FileInputStream(file);
//            MinIOServiceImpl minIOService = new MinIOServiceImpl();
            ossService.putObject(OssConfiguration.bucket, objectKey, inputStream);
            return "redirect:/html/"+objectKey;
        } catch (Exception e) {
            e.printStackTrace();
            return "error11233";
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    // 这是自己随便写的 上传文件 并压缩 到OSS  没有问题
    @RequestMapping("/uploadFiles")
    public  String uploadFiles(@RequestParam("file") MultipartFile file) {
        InputStream inputStream = null;
        try {
            String fileName = file.getName();
            String objectKey = "ceshi/"+fileName;
            inputStream = file.getInputStream();
            // 压缩并获取压缩后的数据流
            ByteArrayOutputStream compressedOutputStream = new ByteArrayOutputStream();
            resizeTiff(inputStream, compressedOutputStream);
            ossService.putObject(OssConfiguration.bucket, objectKey, inputStream);
            return "redirect:/html/"+objectKey;
        } catch (Exception e) {
            e.printStackTrace();
            return "error11233";
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void resizeTiff(InputStream inputStream, OutputStream outputStream) {
        try {
            BufferedImage originalImage = ImageIO.read(inputStream);
            int newWidth = 1075;
            int newHeight = 927;
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            resizedImage.getGraphics().drawImage(originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
            ImageIO.write(resizedImage, "TIFF", outputStream);
            System.out.println("TIFF file resized and saved to: " );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
