package com.example.javaknowledge2.test.Cshi.F;

public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Rectangle is drawn");
    }

    @Override
    public void erase() {
        System.out.println("Rectangle is erased");
    }

    public void resize() {
        System.out.println("Rectangle is resized");
    }
}
