package com.example.javaknowledge2.test.Stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class streamDemo {
    public static void main(String[] args) {
        String str = "HelloWorld";
        //转列表
       List<String> collect =  Stream.of(str.split("")).collect(Collectors.toList());
        System.out.println(collect.toString());
       List<String> list = Arrays.asList(str.split(""));
        System.out.println(list.toString());
        //去重
        List<String> distinctList = list.stream().distinct().collect(Collectors.toList());
        System.out.println(distinctList.toString());
    }
}
