package com.example.javaknowledge2.test.RegExp;

public class RemoveExtraBackslashes {
    public static void main(String[] args) {
        String originalPath = "resource\\kmz\\121\\241121\\\\航线任务2024年11月21日145617.kmz";

        // 使用正则表达式替换多余的反斜杠
        String cleanedPath = originalPath.replaceAll("\\\\\\\\+", "\\\\");

        // 输出结果
        System.out.println("Original Path: " + originalPath);
        System.out.println("Cleaned Path: " + cleanedPath);
    }
}
