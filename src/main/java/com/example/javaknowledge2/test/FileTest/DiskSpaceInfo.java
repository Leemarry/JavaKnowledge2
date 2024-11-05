package com.example.javaknowledge2.test.FileTest;

import java.io.File;

public class DiskSpaceInfo {
    public static void main(String[] args) {
        // 获取根目录，这里以C盘为例，根据需要修改
        File disk = new File("C:/");

        // 获取总容量（字节）
        long totalSpace = disk.getTotalSpace();
        // 获取可用空间（字节）
        long freeSpace = disk.getFreeSpace();
        long usedSpace2 = disk.getUsableSpace();
        // 获取已用空间（字节）
        long usedSpace = totalSpace - freeSpace;

        // 打印信息
        System.out.println("总容量: " + formatSize(totalSpace));
        System.out.println("剩余容量: " + formatSize(freeSpace));
        System.out.println("已用容量: " + formatSize(usedSpace));
        System.out.println("已用容量: " + formatSize(usedSpace2));
    }

    // 格式化字节为可读大小
    private static String formatSize(long size) {
        if (size <= 0) return "0 B";
        String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int idx = (int) (Math.log(size) / Math.log(1024));
        double formattedSize = size / Math.pow(1024, idx);
        return String.format("%.2f %s", formattedSize, units[idx]);
    }
}