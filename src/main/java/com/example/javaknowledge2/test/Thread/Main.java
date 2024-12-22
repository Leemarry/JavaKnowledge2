package com.example.javaknowledge2.test.Thread;

import static com.example.javaknowledge2.test.Thread.Cs.read;

class MyThread extends Thread {
    @Override
    public void run() {
        try {
            Thread.currentThread().sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("MyThread is running.");
    }
}

public class Main {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start(); // 启动线程

        Thread helloThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello, world!");
            }
        });
        helloThread.start(); // 启动线程

        new Thread( () -> read()).start();

        // 主线程休眠2秒
        try {
            Thread.sleep(2000);
            System.out.println("Main thread is running.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
