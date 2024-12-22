package com.example.javaknowledge2.test.DesignPattern.PrototypePattern.example;

import java.io.IOException;

public class PrototypeUsageExample {
    public static void main(String[] args) {
        try {
            // 创建一个 Prototype 实例
            Prototype original = new Prototype();
            original.setString("Original String");
            SerializableObject serializableObject = new SerializableObject();
            original.setObj(serializableObject);

            // 浅克隆操作及验证
            Prototype shallowClone = (Prototype) original.clone();
            System.out.println("浅克隆后，对象引用是否相同（obj）: " + (original.getObj() == shallowClone.getObj()));
            shallowClone.setString("Shallow Cloned String");
            System.out.println("原对象字符串: " + original.getString() + ", 浅克隆对象字符串: " + shallowClone.getString());

            // 深克隆操作及验证
            Prototype deepClone = (Prototype) original.deepClone();
            System.out.println("深克隆后，对象引用是否相同（obj）: " + (original.getObj() == deepClone.getObj()));
            deepClone.setString("Deep Cloned String");
            deepClone.getObj(); // 这里只是演示获取，实际可进行修改等操作对比
            System.out.println("原对象字符串: " + original.getString() + ", 深克隆对象字符串: " + deepClone.getString());
        } catch (CloneNotSupportedException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}