package com.example.javaknowledge2.test.Cshi;

class MathConstants {
    // 圆周率，用final关键字定义为常量，值不能被修改
    private final int num;


    MathConstants(int num) {
        this.num = num;
    }


    public static void main(String[] args) {
        // 下面这行代码会报错，因为PI是final的，不能重新赋值
        MathConstants mathConstants = new MathConstants(7);

        MathConstants mathConstants1 = new MathConstants(314);

        System.out.println("圆周率的值: " + mathConstants.num);
    }
}
