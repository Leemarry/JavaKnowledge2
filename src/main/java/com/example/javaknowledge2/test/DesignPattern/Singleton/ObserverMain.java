package com.example.javaknowledge2.test.DesignPattern.Singleton;


import java.util.ArrayList;
import java.util.List;

// 被观察者
class Observable {
    private List<Observer> observers = new ArrayList<>();
    private String value;

    private Integer count = 0;

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(value);
        }
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
        notifyObservers();
    }

    public void setValue(String value) {
        this.value = value;
        notifyObservers();
    }

    public String getValue() {
        return value;
    }
}

// 观察者接口
interface Observer {
    void update(String value);
}

// 具体观察者
class ConcreteObserver implements Observer {
    private String name;

    public ConcreteObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String value) {
        System.out.println(name + " 收到了更新: " + value);
    }
}

// 使用示例
public class ObserverMain {
    public static void main(String[] args) {
        Observable observable = new Observable();
        ConcreteObserver observer1 = new ConcreteObserver("观察者1");
        ConcreteObserver observer2 = new ConcreteObserver("观察者2");

        observable.addObserver(observer1);
        observable.addObserver(observer2);

        // 修改属性值
        observable.setValue("新的值"); // 所有观察者都会收到更新
        observable.setCount(100); // 所有观察者都会收到更新
    }
}