package com.wangqingyi.javatest.constructor;

/**
 * 构造器学习
 * 构造器是一个特殊的方法，这个特殊的方法用于创建实例时执行初始化。构造器是创建对象的重要途径(即使使用工厂模式、
 * 反射等方式创建对象，其实本质依然是依赖于构造器)
 * 构造器最大的作用就是在创建对象时执行初始化。
 * <p>
 * 构造器重载：
 * 同一个类里具有多个构造器，多个构造器的形参列表不同，即被称为构造器重载。
 * 构造器重载要求构造器名称相同，参数列表必须不同。
 * <p>
 * 注意：如果程序员没有为Java类提供任何构造器，则系统会为这个类提供一个无参数的构造器。
 *
 * @author WangQingYi
 * @since 2021/10/25
 */
class ConstructorTest {
    public String name;
    public int age;

    /**
     * 定义无参构造器
     */
    public ConstructorTest() {
    }

    /**
     * 定义带两个参数的构造器
     * 对该构造器返回的对象执行初始化
     */
    public ConstructorTest(String name, int age) {
        // 构造器里的this代表它进行初始化的对象
        // 下面两行代码传入的两个参数赋给this代表对象的name和count实例变量
        this.name = name;
        this.age = age;
    }

}

class Apple {
    public String name;
    public String color;
    public double weight;

    /**
     * 两个参数的构造器
     */
    public Apple(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public Apple(String name, String color, double weight) {

        /**
         * 通过this调用另一个重载的构造器只能在构造器中使用，而且必须作为构造器执行体的第一条语句。
         * 使用this调用重载的构造器时，系统会根据this后括号里的实参来调用形参列表与之对应的构造器。
         */
        // 通过this调用另一个重载的构造器的初始化代码
        this(name, color);
        // 下面this应用该构造器正在初始化的Java对象
        this.weight = weight;
    }
}

