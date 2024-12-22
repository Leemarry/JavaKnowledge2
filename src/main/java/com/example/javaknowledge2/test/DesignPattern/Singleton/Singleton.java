package com.example.javaknowledge2.test.DesignPattern.Singleton;

// 单例类
public class Singleton {
    private static Singleton instance;

    // 类的属性
    private String value;

    private int count = 0;

    // 私有构造器
    private Singleton() {
        value = "初始值";
    }

    // 获取实例的方法
    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    // Getter 和 Setter
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
