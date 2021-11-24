package com.wangqingyi.javatest.abstracttest;

/**
 * 抽象类复习
 * <p>
 * 有抽象方法的类只能被定义成抽象类，抽象类里可以没有抽象方法
 * <p>
 * 抽象方法不能有方法体；
 * 抽象类不能被实例化，无法使用new关键字来调用抽象类的构造器创建抽象类的实例。即使抽象类里不包含抽象方法，这个抽象类也不能创建实例；
 * 抽象类可以包含成员变量、方法(普通方法和抽象方法都可以)、构造器、初始化块、内部类(接口、枚举)5种成分。抽象类的构造器
 * 不能用于创建实例，主要用于被其子类调用；
 * 含有抽象方法的类(包括直接定义了一个抽象方法；或继承了一个抽象父类，但没有完全实现父类包含的抽象方法；或实现了一个接口，
 * 但没有完全实现接口包含的抽象方法三种情况）只能被定义成抽象类
 *
 * 抽象类的作用
 * 从语义的角度来看。抽象类是从多个具体类中抽象出来的父类，它具有更高层次的抽象。从多个具有相同特征的类中抽象出一个抽象类，
 * 以这类作为其子类的模板，从而避免子类设计的随意性。
 * 抽象类体现的就是一种模板模式的设计，抽象类作为多个子类的通用模板，子类在抽象类的基础上进行扩展、改造，
 * 但子类总体上大只保留抽象类的行为方法。
 *
 * @author WangQingYi
 * @since 2021/11/14
 */
class AbstractTest {
    public abstract class Shape {
        private String color;

        // 定义一个计算周长的抽象方法
        public abstract double calPerimeter();

        // 定义一个返回形状的抽象方法
        public abstract String getType();

        // 定义Shape构造器，该构造器并不是用于创建Shape对象
        // 而是用于被子类调用
        public Shape() {
        }

        public Shape(String color) {
            System.out.println("执行Shape的构造器");
            this.color = color;
        }
    }
public  class Triangle extends Shape{

    @Override
    public double calPerimeter() {
        return 0;
    }

    @Override
    public String getType() {
        return null;
    }
}
}
