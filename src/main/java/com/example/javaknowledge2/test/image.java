package com.example.javaknowledge2.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
// 没有意义 xxx
public class image {
    public static String extractPointcloud(String input) {
        // 使用正则表达式匹配"pointcloud/"后面的部分
        Pattern pattern = Pattern.compile("pointcloud/([^/]+)");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return "pointcloud/" + matcher.group(1);
        }

        return null; // 如果没有匹配，返回null
    }

    public static void main(String[] args) {
        String string1 = "xxxxxxx/efuav-ortho-img/pointcloud/21d/202408160833_001_B001/{z}/{x}/{y}.png";
        String string2 = "xxxxxxx/efuav-ortho-img/pointcloud/20241d/202408160833_001_B001/{z}/{x}/{y}.png";

        System.out.println(extractPointcloud(string1));  // 输出: pointcloud/202408160833_001_B001eaf67c99859a8e2ccacb38df049f201d
        System.out.println(extractPointcloud(string2));  // 输出: pointcloud/2024081603df049f201d
    }

}
