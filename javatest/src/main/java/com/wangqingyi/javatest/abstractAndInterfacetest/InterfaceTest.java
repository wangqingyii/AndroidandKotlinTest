package com.wangqingyi.javatest.interfacetest;

/**
 * 接口复习
 *
 * 接口是从多个相似类中抽象出来的规范，接口不提供任何实现。接口体现的是规范和实现分离的设计模式
 * <p>
 * 接口定义的是一种规范，因此接口里不能包含构造器和初始化块定义。接口里可以包含成员变量(只能是静态常量)、方法
 * (只能是抽象实例方法、类方法、默认方法或私有方法)、内部类(包括内部接口、枚举)定义。
 * 接口里定义的方法只能是抽象方法、类方法、默认方法或私有方法，因此如果不是定义默认方法、类方法或私有方法，系统将自动为
 * 普通方法增加abstract修饰符；定义接口里的普通方法时不管是否使用public abstract来修饰。接口里的普通方法不能有方法实现(方法体)
 * 但类方法、默认方法、私有方法都必须有方法实现(方法体)
 *
 * 使用接口
 * 接口不能用于创建实例，但接口可以用于声明引用类型变量。当使用接口来声明引用类型变量时
 * 
 * @author WangQingYi
 * @since 2021/11/14
 */
interface InterfaceTest {

    // 接口里定义的成员变量只能是常量
    int MAX_CACHE_LINE = 50;

    // 接口里定义的普通方法只能是public的抽象方法
    void out();

    // 在接口中定义默认方法，需要使用default修饰
    default void print(String msg){
        System.out.println("默认方法");
    }
    // 接口中定义类方法，需要使用static修饰
    static String staticTest(){
        return "类方法";
    }
}

/**
 * 接口的继承
 * 接口完全支持多继承，即一个接口可以有多个直接父接口
 */
interface InterfaceA{
    int PROP_A = 5;
    void TestA();
}
interface InterfaceB{
    int PROP_B = 6;
    void TestB();
}
interface InterfaceC extends InterfaceA,InterfaceB{
    int PROP_C = 6;
    void TestC();
}

/**
 */