package com.example.javaknowledge2.test.DesignPattern.Weather;

import java.util.ArrayList;
import java.util.List;

// 被观察者
class WeatherData {
    private List<Observer> observers = new ArrayList<>();
    private float temperature;
    private float humidity;

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature, humidity);
        }
    }

    public void setWeatherData(float temperature, float humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        notifyObservers(); // 通知所有观察者更新数据
    }
}

// 观察者接口
interface Observer {
    void update(float temperature, float humidity);
}

// 显示设备
class CurrentConditionsDisplay implements Observer {
    private float temperature;
    private float humidity;

    @Override
    public void update(float temperature, float humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }

    public void display() {
        System.out.println("当前天气条件: 温度 " + temperature + "°C, 湿度 " + humidity + "%");
    }
}

// 主程序
public class WeatherStation {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay();
        weatherData.addObserver(currentDisplay);

        // 模拟天气数据更新
        weatherData.setWeatherData(30.0f, 65.0f);
        weatherData.setWeatherData(25.0f, 70.0f);
    }
}
