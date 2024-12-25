package com.example.javaknowledge2.test.Cshi;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class readFile {
    public static void main(String[] args) throws IOException {
        String path = "E:\\Amireux\\Pictures\\waylines.wpml";
        String content = "";
        content = Files.readString(Paths.get(path));
//        try {
//            FileReader fileReader = new FileReader(path);
//            int c;
//            while ((c = fileReader.read())!= -1) {
//                content += (char) c;
//            }
//            fileReader.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        System.out.println(content);

    }
}
