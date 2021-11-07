package com.wangqingyi.javatest.polymorphism;

/**
 * 多态练习
 * java引用变量有两个类型：一个是编译时类型，一个是运行时类型。编译时类型由声明该变量时使用的类型决定，运行时类型由实际赋给
 * 该变量的对象决定。如果编译时类型和运行时类型不一致，就可能出现所谓的多态。
 *
 * 多态的条件：
 * 继承：必须存在有继承关系的子类和父类
 * 重写：子类对父类的默认方法重新定义，在调用这些方法的时候就会调用子类的方法
 * 向上转型(系统自动完成=-----------------------------------------------------------)：子类其实就是一种特殊的父类，Java允许把一个子类对象直接赋给一个父类引用变量，无需任何类型转换，这被称为向上转型
 *
 * BaseClass polymorphismBc = new SubClass();
 * 例如：polymorphismBc引用变量的编译时类型是BaseClass，而运行时类型是SubClass，当运行时调用该引用变量的方法时，
 * 其方法行为总是表现出子类方法的行为特征，这就可能出现：相同类型的变量、调用同一个方法时呈现出多种不同的行为特征，这就是多态
 *
 * @author WangQingYi
 * @since 2021/11/1
 */
class BaseClass {
    public int book = 6;

    public void base() {
        System.out.println("父类的普通方法");
    }

    public void test() {
        System.out.println("父类的被覆盖方法");
    }
}

class SubClass extends BaseClass {
    // 重新定义一个book实例变90=量隐藏父类的book实例变量
    public String book = "百年孤独";

    @Override
    public void base() {
        super.base();
    }

    @Override
    public void test() {
        System.out.println("子类覆盖父类的方法");
    }

    public void sub() {
        System.out.println("子类的普通方法");
    }

    public static void main(String[] args) {
        BaseClass bc = new BaseClass();
        // 下面编译时类型和运行时类型完全一样，因此不存在多态
        System.out.println(bc.book);
        bc.base();
        bc.test();
        // 下面编译时类型和运行时类型完全一样因此不存在多态
        SubClass sub = new SubClass();
        System.out.println(sub.book);
        sub.base();
        sub.test();
        System.out.println("////////////////////////////////////////////////////////////");

        BaseClass polymorphismBc = new SubClass();
        // 输出6----表明访问的是父类对象的实例变量
        System.out.println(polymorphismBc.book);
        // 执行从父类集成到的base()方法
        polymorphismBc.base();
        // 调用将执行当前类的test方法
        polymorphismBc.test();
    }
}