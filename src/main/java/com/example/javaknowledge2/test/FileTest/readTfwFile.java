package com.example.javaknowledge2.test.FileTest;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class readTfwFile {
    public static void main(String[] args) {
        // 如果数据为 MultipartFile file

        readFile("F:\\document\\杆塔测试数据\\正射\\map\\gsddsm.tfw");
    }

    public static void readFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine())!= null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}