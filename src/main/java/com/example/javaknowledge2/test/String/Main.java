package com.example.javaknowledge2.test.String;

public class Main {
    public static void main(String[] args) {
        String path = "G:\\瓦片地图\\googlemap\\tiles\\p\\6\\52";
        int index = 0;
        for (int i = 0; i < path.length(); i++) {
            char c = path.charAt(i);
            if (Character.isDigit(c)) {
                break;
            }
            index = i;
        }
        String result = path.substring(0, index + 1);
        System.out.println(result);
    }
}