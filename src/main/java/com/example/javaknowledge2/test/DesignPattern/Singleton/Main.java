package com.example.javaknowledge2.test.DesignPattern.Singleton;

public class Main {
    public static void main(String[] args) {
        // 获取单例实例
        Singleton singleton = Singleton.getInstance();

        singleton.setCount(2);
        // 修改属性
        singleton.setValue("新的值");

        // 其他地方读取
        Singleton anotherInstance = Singleton.getInstance();
        System.out.println("属性值: " + anotherInstance.getValue()); // 输出: 属性值: 新的值
        System.out.println("计数值: " + anotherInstance.getCount()); // 输出: 2
    }
}