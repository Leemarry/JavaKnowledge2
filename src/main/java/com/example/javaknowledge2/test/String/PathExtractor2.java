package com.example.javaknowledge2.test.String;

import java.util.ArrayList;
import java.util.List;

public class PathExtractor2 {
    public static void main(String[] args) {
        // 示例路径
        String path1 = "/gs_map_B002_B007/map/22/3430828";
        String path2 = "/another/path/with/numbers/6/52/55/2";

        // 提取数字部分并打印结果
        System.out.println(extractNumericPath(path1)); // 输出: /22/3430828
        System.out.println(extractNumericPath(path2)); // 输出: /6/52/55/2
    }

    private static String extractNumericPath(String path) {
        String[] parts = path.split("/"); // 按正斜杠分割路径
        List<String> numericParts = new ArrayList<>();

        // 标志位，指示是否找到数字部分
        boolean foundDigit = false;

        // 从后往前查找数字部分
        for (int i = parts.length - 1; i >= 0; i--) {
            if (isNumeric(parts[i])) {
                numericParts.add(0, parts[i]); // 把数字部分添加到结果列表的前面
                foundDigit = true; // 找到数字
            } else if (foundDigit) {
                // 如果已经找到数字部分，但当前部分不是数字，则停止查找
                break;
            }
        }

        // 构建结果字符串
        return numericParts.isEmpty() ? "" : "/" + String.join("/", numericParts);
    }

    // 辅助函数，判断字符串是否为数字
    private static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char ch : str.toCharArray()) {
            if (!Character.isDigit(ch)) {
                return false;
            }
        }
        return true;
    }
}