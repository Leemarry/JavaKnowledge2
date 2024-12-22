package com.example.javaknowledge2.test.DesignPattern.Singleton.example;

public class InnerClassSIngeton {
      int num = 0;
      String str = "hello";
    private static class Singleton{
        private static final InnerClassSIngeton instance = new InnerClassSIngeton();
    }
    private InnerClassSIngeton(){
    }
    public static InnerClassSIngeton getInstance(){
        return Singleton.instance;
    }


    //getters and setters

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
