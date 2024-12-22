package com.example.javaknowledge2.test.FileTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CsFIle {
    public static void main(String[] args) throws IOException {
         Path path = Paths.get("E:\\Amireux\\Downloads\\ss");
//        FileUtils.deleteFolderContents(path);
        File file = new File("E:\\Amireux\\Downloads\\sss\\123456.txt");
          String fileName = file.getName();
         if (fileName == null) {
            throw new IllegalArgumentException("文件名不能为空");
        }

        // 创建目标路径
         Path targetPath = Paths.get(String.valueOf(path), fileName);        // 确保目标目录存在，否则创建
         if (targetPath.toFile().exists()) {
            throw new IllegalArgumentException("目标文件已存在");
        }
        if (!Files.exists(targetPath.getParent())) {
             Files.createDirectories(targetPath.getParent()); // 创建父目录
        }


        // 复制文件到目标路径
        try {
            Files.copy(file.toPath(), targetPath); // 文件复制
            System.out.println("文件保存成功: " + targetPath.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
