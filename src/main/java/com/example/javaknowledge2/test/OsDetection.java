package com.example.javaknowledge2.test;

import java.util.Locale;

public class OsDetection {

    public static boolean isWindows() {
        String os = System.getProperty("os.name").toLowerCase(Locale.ROOT);
        return os.contains("win");
    }

    public static void main(String[] args) {
        if (isWindows()) {
            System.out.println("当前是 Windows 操作系统");
        } else {
            System.out.println("当前不是 Windows 操作系统");
        }
    }
}
