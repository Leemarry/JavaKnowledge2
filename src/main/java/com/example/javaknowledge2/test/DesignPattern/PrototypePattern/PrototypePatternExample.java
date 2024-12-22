package com.example.javaknowledge2.test.DesignPattern.PrototypePattern;

import java.util.HashMap;
import java.util.Map;

abstract class Shape implements Cloneable {
    private String id;
    protected String type;

    public abstract void draw();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    @Override
    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}

class Circle extends Shape {
    public Circle() {
        type = "Circle";
    }

    @Override
    public void draw() {
        System.out.println("Drawing a circle");
    }
}

class Rectangle extends Shape {
    public Rectangle() {
        type = "Rectangle";
    }

    @Override
    public void draw() {
        System.out.println("Drawing a rectangle");
    }
}


class ShapeCache {
    private static Map<String, Shape> shapeMap = new HashMap<>();

    public static void loadCache() {
        Circle circle = new Circle();
        circle.setId("1");
        shapeMap.put(circle.getId(), circle);

        Rectangle rectangle = new Rectangle();
        rectangle.setId("2");
        shapeMap.put(rectangle.getId(), rectangle);
    }

    public static Shape getShape(String shapeId) {
        Shape cachedShape = shapeMap.get(shapeId);
        return (Shape) cachedShape.clone();
    }
}


public class PrototypePatternExample {
    public static void main(String[] args) {
        ShapeCache.loadCache();

        Shape clonedCircle = ShapeCache.getShape("1");
        System.out.println("Cloned circle type: " + clonedCircle.getType());
        clonedCircle.draw();

        Shape clonedRectangle = ShapeCache.getShape("2");
        System.out.println("Cloned rectangle type: " + clonedRectangle.getType());
        clonedRectangle.draw();
    }
}
