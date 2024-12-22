package com.example.javaknowledge2.test.DesignPattern.FactoryMethodPattern.example;


interface Product {
    void use();
}

class ConcreteProductA implements Product {
    public void use() {
        System.out.println("使用产品A");
    }
}

class ConcreteProductB implements Product {
    public void use() {
        System.out.println("使用产品B");
    }
}

abstract class Creator {
    public abstract Product factoryMethod();
}

class CreatorA extends Creator {
    public Product factoryMethod() {
        return new ConcreteProductA();
    }
}

class CreatorB extends Creator {
    public Product factoryMethod() {
        return new ConcreteProductB();
    }
}

public class FactoryMethodExample {
    public static void main(String[] args) {
        Creator creatorA = new CreatorA();
        Product productA = creatorA.factoryMethod();
        productA.use();

        Creator creatorB = new CreatorB();
        Product productB = creatorB.factoryMethod();
        productB.use();
    }
}
