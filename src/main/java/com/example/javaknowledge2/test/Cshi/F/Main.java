package com.example.javaknowledge2.test.Cshi.F;


public class Main {
    public static void main(String[] args) {
        Animal animal1 = new Dog();
        Animal animal2 = new Cat();
        animal1.makeSound();
        animal2.makeSound();
        animal1.eat();

        if(animal1 instanceof Dog){
            System.out.println("animal1 is a Dog");
        }
    }
}


class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Dog barks");
    }
}
class Cat extends Animal {

    @Override
    public void makeSound() {
        super.makeSound();
    }
}

class Animal {
    public void makeSound() {
        System.out.println("Animal makes a sound");
    }
    public void eat() {
        System.out.println("Animal is eating");
    }
}