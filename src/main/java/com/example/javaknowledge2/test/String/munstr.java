package com.example.javaknowledge2.test.String;

public class munstr {

    public static String extractPathAfterFirstDigit(String path) {
        int index = -1;
        for (int i = 0; i < path.length(); i++) {
            if (Character.isDigit(path.charAt(i))) {
                index = i;
                break;
            }
        }
        if (index!= -1) {
            return path.substring(index);
        }
        return "";
    }

    public static void main(String[] args) {
        String path1 = "G:\\瓦片地图\\googlemap\\tiles\\p";
        String path2 = "G:\\瓦片地图\\googlemap\\tiles\\p\\6\\52\\55\\2";
        System.out.println(extractPathAfterFirstDigit(path1));
        System.out.println(extractPathAfterFirstDigit(path2));
    }
}
