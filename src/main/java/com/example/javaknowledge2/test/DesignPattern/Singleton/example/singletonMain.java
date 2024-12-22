package com.example.javaknowledge2.test.DesignPattern.Singleton.example;

public class singletonMain {
    public static void main(String[] args) {
        InnerClassSIngeton innerClassSingleton = InnerClassSIngeton.getInstance();
        System.out.println(innerClassSingleton.getNum());
        innerClassSingleton.setNum(100);
        InnerClassSIngeton innerClassSingleton2 = InnerClassSIngeton.getInstance();
        System.out.println(innerClassSingleton2.getNum());
        System.out.println(innerClassSingleton.getNum());
    }
}
