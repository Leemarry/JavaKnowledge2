package com.example.javaknowledge2.utils.SnowflakeIdGenerator;

import com.example.javaknowledge2.utils.SnowflakeIdGenerator.SnowflakeIdGenerator;

public class Main {
    public static void main(String[] args) {
        // 创建一个雪花 ID 生成器实例，传入节点 ID
        SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(30);
        // 生成 ID
        long id = idGenerator.generateId();
        long id2 = idGenerator.generateId();
        long id3 = idGenerator.generateId();
        // 输出 ID
        System.out.println(id);
        System.out.println(id2);
        System.out.println(id3);
    }
}