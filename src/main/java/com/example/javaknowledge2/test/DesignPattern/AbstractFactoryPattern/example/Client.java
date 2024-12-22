package com.example.javaknowledge2.test.DesignPattern.AbstractFactoryPattern.example;

// 抽象产品：披萨
 interface Pizza {
    void prepare();
}

// 抽象产品：饮料
 interface Beverage {
    void serve();
}

// 具体产品：意大利披萨
 class ItalianPizza implements Pizza {
    @Override
    public void prepare() {
        System.out.println("Preparing Italian Pizza");
    }
}

// 具体产品：意大利咖啡
 class ItalianCoffee implements Beverage {
    @Override
    public void serve() {
        System.out.println("Serving Italian Coffee");
    }
}

// 具体产品：美式披萨
 class AmericanPizza implements Pizza {
    @Override
    public void prepare() {
        System.out.println("Preparing American Pizza");
    }
}

// 具体产品：美式可乐
 class AmericanCoke implements Beverage {
    @Override
    public void serve() {
        System.out.println("Serving American Coke");
    }
}
// 抽象工厂：美食工厂
 interface FoodFactory {
    Pizza createPizza();
    Beverage createBeverage();
}

// 具体工厂：意大利美食工厂
 class ItalianFoodFactory implements FoodFactory {
    @Override
    public Pizza createPizza() {
        return new ItalianPizza();
    }

    @Override
    public Beverage createBeverage() {
        return new ItalianCoffee();
    }
}

// 具体工厂：美式美食工厂
 class AmericanFoodFactory implements FoodFactory {
    @Override
    public Pizza createPizza() {
        return new AmericanPizza();
    }

    @Override
    public Beverage createBeverage() {
        return new AmericanCoke();
    }
}
public class Client {
    public static void main(String[] args) {
        // 使用意大利美食工厂
        FoodFactory italianFactory = new ItalianFoodFactory();
        Pizza italianPizza = italianFactory.createPizza();
        Beverage italianCoffee = italianFactory.createBeverage();

        italianPizza.prepare();
        italianCoffee.serve();

        // 使用美式美食工厂
        FoodFactory americanFactory = new AmericanFoodFactory();
        Pizza americanPizza = americanFactory.createPizza();
        Beverage americanCoke = americanFactory.createBeverage();

        americanPizza.prepare();
        americanCoke.serve();
    }
}
