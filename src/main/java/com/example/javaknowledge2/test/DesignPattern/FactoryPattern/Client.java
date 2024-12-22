package com.example.javaknowledge2.test.DesignPattern.FactoryPattern;

public class Client {
    public static void main(String[] args) {
        Creator creatorA = new ConcreteCreatorA();
        creatorA.someOperation();  // 输出：使用产品 A

        Creator creatorB = new ConcreteCreatorB();
        creatorB.someOperation();  // 输出：使用产品 B
    }
}


// region factory pattern
// Factory pattern 是一种创建型设计模式，它定义一个用于创建对象的接口，但由子类决定实例化哪一个类。
 interface Product {
    void use();
}
 class ConcreteProductA implements Product {
    @Override
    public void use() {
        System.out.println("使用产品 A");
    }
}

 class ConcreteProductB implements Product {
    @Override
    public void use() {
        System.out.println("使用产品 B");
    }
}
 abstract class Creator {
    public abstract Product createProduct();

    public void someOperation() {
        Product product = createProduct();
        product.use();
    }
}

 class ConcreteCreatorA extends Creator {
    @Override
    public Product createProduct() {
        return new ConcreteProductA();
    }
}

 class ConcreteCreatorB extends Creator {
    @Override
    public Product createProduct() {
        return new ConcreteProductB();
    }
}

//#endregion
