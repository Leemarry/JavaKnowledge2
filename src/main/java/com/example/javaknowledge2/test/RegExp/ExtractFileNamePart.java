package com.example.javaknowledge2.test.RegExp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractFileNamePart {
    public static void main(String[] args) {
        String fileName = "DJI_2024060616214_0012_D.jpg";

        // 正则表达式解释：
        // ^ 表示字符串开始
        // DJI_ 匹配文本 "DJI_"
        // (.*?) 匹配并捕获任意字符（非贪婪模式），直到遇到下一个指定的模式
        // _D\.jpg$ 匹配文本 "_D.jpg" 并确保字符串在这里结束
        Pattern pattern = Pattern.compile("^DJI_(.*?)_D\\.jpg$");

        Matcher matcher = pattern.matcher(fileName);

        if (matcher.find()) {
            // 捕获组1就是我们需要的部分
            String result = matcher.group(1);
            System.out.println(result); // 输出：2024060616214_0012
        } else {
            System.out.println("没有找到匹配项");
        }
    }
}