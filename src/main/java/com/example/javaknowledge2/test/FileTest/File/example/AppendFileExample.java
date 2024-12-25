package com.example.javaknowledge2.test.FileTest.File.example;

import java.io.FileOutputStream;
import java.io.IOException;

public class AppendFileExample {
    public static void main(String[] args) {
        String outputFilePath = "I:\\Administrator\\Pictures\\追加.txt";
        try (FileOutputStream fos = new FileOutputStream(outputFilePath, true)) {
            String data = "This is new data to append.\n";
            fos.write(data.getBytes());
            System.out.println("Data appended successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}