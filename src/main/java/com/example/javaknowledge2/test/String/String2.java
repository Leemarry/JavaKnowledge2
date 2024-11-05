package com.example.javaknowledge2.test.String;

import java.util.ArrayList;
import java.util.List;

public class String2 {

    public static void main(String[] args) {
        String folder = "/ooos_B002_B009/user/Documents/Java";
         List<String> parts = verifyFormat(folder);


         if (parts!= null) {
             for (String part : parts) {
                 System.out.println(part);
             }
         }
    }
    // String[]  List<String>

    public static List<String> verifyFormat(String folder){
        // String folder = "/B002_B009/user/Documents/Java";
         List<String> strings= new ArrayList<String>();
        // 尝试分割文件夹路径
        String[] folderArr = folder.split("/");
        // 检查是否有足够的部分
        if (folderArr.length <= 1 || folderArr[1].isEmpty()) {
            System.out.println("路径格式不正确，无法找到有效的文件夹部分");
            return null; // 或抛出异常
        }
        String parentFolder = folderArr[1]; // 假设这是我们要处理的部分，例如 "B002_B009"
        strings.add(parentFolder);

        // 尝试使用下划线分割父文件夹
        String[] parts = parentFolder.split("_");

        // 检查分割后是否至少有两个部分
        if (parts.length < 2) {
            System.out.println("父文件夹部分格式不正确，无法找到足够的下划线分隔部分");
            return strings; // 或抛出异常
        }

        // 获取最后两个部分
        String part1 = parts[parts.length - 2];
        String part2 = parts[parts.length - 1];

        // 检查这两个部分是否都以A或B开头后跟数字
        if (!part1.matches("^[AB]\\d+") || !part2.matches("^[AB]\\d+")) {
            System.out.println("不是所有部分都以A或B开头后跟数字的格式");
            return strings; // 或抛出异常
        }

        // 如果所有检查都通过，则进行后续处理
        char prefix1 = part1.charAt(0);

        char prefix2 = part2.charAt(0);
        // 注意：通常情况下，prefix1 和 prefix2 应该是相同的，但这里仍然分别处理
        System.out.println("第一个部分的开头字母与数字: " + prefix1 + part1.substring(1));
        System.out.println("第二个部分的开头字母与数字: " + prefix2 + part2.substring(1));
        // 返回 prefix1   part1.substring(1)  part2.substring(1)
         strings.add(String.valueOf(prefix1));
         strings.add(String.valueOf(part1.substring(1)));
         strings.add(String.valueOf(part2.substring(1)));
        return strings;



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
        String newFolder = folder.substring(firstSlashIndex + 1, secondSlashIndex);
//        String[] folderArr = newFolder.split("_");
//        String folderName = "";
//        if (folderArr.length > 0) {
//            folderName = folderArr[folderArr.length - 1];
//        }
        return newFolder;
    }


}
