package com.example.javaknowledge2.test.DesignPattern.FactoryMethodPattern.example;


abstract class Button {
    public abstract void display();
}
class WindowsButton extends Button {
    public void display() {
        System.out.println("This is a Windows button.");
    }
}
class MacButton extends Button {
    public void display() {
        System.out.println("This is a Mac button.");
    }
}
class LinuxButton extends Button {
    public void display() {
        System.out.println("This is a Linux button.");
    }
}


//interface Button {
//    void display();
//}

//class WindowsButton implements Button {
//    public void display() {
//        System.out.println("This is a Windows button.");
//    }
//}
//
//class MacButton implements Button {
//    public void display() {
//        System.out.println("This is a Mac button.");
//    }
//}
//class LinuxButton implements Button {
//    public void display() {
//        System.out.println("This is a Linux button.");
//    }
//}



abstract class ButtonFactory {
    public abstract Button createButton();
}

class WindowsButtonFactory extends ButtonFactory {
    public Button createButton() {
        return new WindowsButton();
    }
}

class MacButtonFactory extends ButtonFactory {
    public Button createButton() {
        return new MacButton();
    }
}

class LinuxButtonFactory extends ButtonFactory {
    public Button createButton() {
        return new LinuxButton();
    }
}

//
//interface ButtonFactory {
//    Button createButton();
//}
//
//class WindowsButtonFactory implements ButtonFactory {
//    public Button createButton() {
//        return new WindowsButton();
//    }
//}
//
//class MacButtonFactory implements ButtonFactory {
//    public Button createButton() {
//        return new MacButton();
//    }
//}
//
//class LinuxButtonFactory implements ButtonFactory {
//    public Button createButton() {
//        return new LinuxButton();
//    }
//}


public class ButtonClient {
    public static void main(String[] args) {

        ButtonFactory windowsFactory = new WindowsButtonFactory();
        ButtonFactory macFactory = new MacButtonFactory();
        ButtonFactory linuxFactory = new LinuxButtonFactory();

        Button windowsButton = windowsFactory.createButton();
        windowsButton.display();

//        Button macButton = macFactory.createButton();
//        macButton.display();
//
//        Button linuxButton = linuxFactory.createButton();
//        linuxButton.display();

    }
}
