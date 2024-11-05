package com.example.javaknowledge2.test.Oss;

import java.net.MalformedURLException;
import java.net.URL;

public class os {
    public static void main(String[] args) {
        String str1 = "/4444_7031_B001_B003/ssssssss/ssssss";
        String st=   substring1(str1);
        System.out.println(st);
    }
    public static String substring1(String folder) {
        int firstSlashIndex = folder.indexOf("/");
        if (firstSlashIndex == -1) {
            System.out.println("未找到第一个 '/'");
            return folder;
        }
        // 找到第二个 '/' 的位置，从第一个 '/' 之后的位置开始搜索
        int secondSlashIndex = folder.indexOf("/", firstSlashIndex + 1);
        if (secondSlashIndex == -1) {
//            System.out.println("未找到第二个 '/'");
            secondSlashIndex = folder.length();
        }
        String newFolder = folder.substring(firstSlashIndex + 1, secondSlashIndex); // 截取文件夹名
        String[] folderArr = newFolder.split("_");
        String endmarkName = ""; // 末尾标记名
        if (folderArr.length > 0) {
            endmarkName = folderArr[folderArr.length - 1];
        }
        return endmarkName;
    }
}
