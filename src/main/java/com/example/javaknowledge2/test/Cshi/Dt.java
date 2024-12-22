package com.example.javaknowledge2.test.Cshi;


 interface Pet {
    void eat();
}

 class Dog implements Pet {
    @Override
    public void eat() {
        System.out.println("狗粮");
    }
}

 class Penguin implements Pet {
    @Override
    public void eat() {
        System.out.println("鱼");
    }
    public void swim() {
        System.out.println("游泳");
    }

}

 class Master {
    public void feed(Pet pet) {
        System.out.println("喂食...");
        pet.eat();
    }
}


public class Dt {
    public static void main(String[] args) {
//        Master master = new Master();
//        Dog dog = new Dog();
//        Penguin penguin = new Penguin();
//        master.feed(dog);
//        master.feed(penguin);

        // 使用
        Master master = new Master();
        master.feed(new Dog());  // 输出：喂食... 狗粮
        master.feed(new Penguin());  // 输出：喂食... 鱼
    }


}
