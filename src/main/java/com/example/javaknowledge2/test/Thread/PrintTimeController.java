package com.example.javaknowledge2.test.Thread;

import java.util.Scanner;

public class PrintTimeController {
    private static volatile boolean running = true; // 共享变量，控制线程的运行状态

    public static void main(String[] args) {
        // 创建打印时间的线程
        Thread printTimeThread = new Thread(new Runnable(){
            @Override
            public void run() {
                System.out.println("Current time: " + System.currentTimeMillis());
            }
        });
        printTimeThread.start();

        // 创建监听控制台输入的线程
        Thread inputThread = new Thread(new InputListener());
        inputThread.start();
    }

    // 打印时间的任务
    static class PrintTimeTask implements Runnable {
        @Override
        public void run() {
            while (running) { // 当running为true时，持续打印时间
                System.out.println("Current time: " + System.currentTimeMillis());
                // 为了避免过于频繁地打印，这里使用sleep方法暂停2秒
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // 如果线程在sleep期间被中断，则退出循环（但在这个例子中我们不会主动中断它）
                    System.out.println("PrintTimeThread was interrupted. Exiting.");
                    break;
                }
            }
            System.out.println("PrintTimeThread has stopped.");
        }
    }

    // 监听控制台输入的线程
    static class InputListener implements Runnable {
        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter 'stop' to terminate the time printing thread:");
            while (true) {
                String input = scanner.nextLine().trim().toLowerCase();
                if ("stop".equals(input)) {
                    running = false; // 检测到停止命令，更新共享变量
                    System.out.println("Stop command received. PrintTimeThread will stop.");
                    break;
                } else {
                    System.out.println("Unknown command. Enter 'stop' to terminate.");
                }
            }
            scanner.close();
        }
    }
}