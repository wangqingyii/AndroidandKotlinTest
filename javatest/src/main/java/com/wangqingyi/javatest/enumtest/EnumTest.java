package com.wangqingyi.javatest.enumtest;

/**
 * 枚举类复习
 * enum与class、interface关键字的地位相同，可以拥有自己的成员变量、方法，可以实现一个或者多个接口，也可以定义自己的构造器
 * 一个java源文件最多只能定义一个public访问权限的枚举类，且该java源文件也必须和该枚举类的类名相同
 * <p>
 * 枚举类与普通类的区别：
 * 枚举类可以实现一个或者多个接口，使用enum定义的枚举类默认继承了java.lang.Enum类，而不是默认继承Object类，
 * 因此枚举类不能显示继承其他父类。
 * <p>
 * 枚举类的成员变量、方法和构造器：
 * 枚举类也是一种类，只是它是一种比较特殊的类，因此它一样可以定义成员变量、方法和构造器。
 * 枚举类的实例只能是枚举值，而不是随意的通过new来创建枚举类对象
 * <p>
 * 枚举类通常应该设计成不可变类，也就是说，它的成员变量值不应该允许改变，这样会更安全，而且代码更加简洁。因此建议
 * 将枚举类的成员变量都使用private final修饰，
 * <p>
 * 如果将所有的成员变量都使用了final修饰符来修饰，所以必须在构造器里为这些成员变量指定初始值(或者在定义成员变量时指定默认值，
 * 但这个两种情况并不常见)，因此应该为枚举类显式定义带参数的构造器。
 * 以单位枚举类显式定义了带参数的构造器，列出枚举值时就必须对应的传入参数。
 * <p>
 * 实现接口的枚举类:
 * 与普通类实现一个或多个接口完全一样:使用implement实现接口，并实现接口里包含的抽象方法
 * 如果由枚举类来实现接口里的方法，则每个枚举值在调用该方法时都有相同的行为方式(因为方法体完全一样)。如果需要每个枚举
 * 值在调用该方法时呈现出不同的行为方式，则可以让每个枚举值分别来实现该方法，每个枚举值提供不同的实现方式，从而让不同的
 * 枚举值调用该方法时具有不同的行为方式。
 *
 * 包含抽象方法的枚举类
 * 枚举类里定义抽象方法时不能使用abstract关键字将枚举类定义成抽象类(因为系统自动会为它添加abstract关键字)，但因为
 * 枚举类需要显式创建枚举值，而不是作为父类，所以定义每个枚举值时必须为抽象方法提供实现，否则将出现编译错误。
 * @author WangQingYi
 * @since 2021/11/21
 */
class EnumTest {
    public void judge(SeasonEnum s) {
        switch (s) {
            case SPRING:
                System.out.println("春");
                break;
            case SUMMER:
                System.out.println("夏");
                break;
            case FALL:
                System.out.println("秋");
                break;
            case WINTER:
                System.out.println("冬");
                break;
        }
    }

    public static void main(String[] args) {
        // 枚举类默认有一个values()方法，返回该枚举类的所有实例
        for (SeasonEnum s : SeasonEnum.values()) {
            System.out.println(s);
        }
        // 使用枚举实例时，可通过EnumClass.variable形式来访问
        new EnumTest().judge(SeasonEnum.SPRING);
    }
}

/**
 * 枚举类的成员变量、方法和构造器
 */
enum Gender {
    // 这样的写法设计上有问题
//    MALE, FEMALE;
//    // 定义一个public修饰的实例变量
//    public String name;
//
//    public void setName(String name) {
//        switch (this) {
//            case MALE:
//                if (name.equals("男")) {
//                    this.name = name;
//                } else {
//                    System.out.println("参数错误");
//                    return;
//                }
//                break;
//            case FEMALE:
//                if (name.equals("女")) {
//                    this.name = name;
//                } else {
//                    System.out.println("参数错误");
//                    return;
//                }
//                break;
//        }
//
//    public String getName() {
//        return this.name;
//    }
    // 此处的枚举值必须调用对应的构造器来创建，无须显式调用构造器也无须使用new关键字
    MALE("男"), FEMALE("女");
    private final String name;

    // 枚举类的构造器只能使用private修饰
    private Gender(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}

class GenderTest {
    public static void main(String[] args) {
/**
 * 不应该允许直接访问Gender类的name成员变量，应该通过方法来控制对name的访问
 */
        // 通过Enum的valueOf()方法来获取指定枚举类的枚举值
//        Gender g = Enum.valueOf(Gender.class, "FEMALE");
//        g.name = "女";
//        System.out.println(g + "代表" + g.name);
//        // 此时设置name值会提示参数错误
//        g.setName("男");
//        System.out.println(g + "代表" + g.name);
    }
}


/**
 * 实现接口的枚举类
 */
interface GenderDesc {
    void info();
}

enum Gender1 implements GenderDesc {
    /**
     * 创建MALE，FEMALE枚举值时，并不是直接创建Gender1枚举类的实例，而是相当于创建Gender1的匿名子类的实例。
     */
    MALE("男")
            // 花括号部分实际上是一个类体部分
            {
                @Override
                public void info() {
                    System.out.println("男");
                }
            },
    FEMALE("女") {
        @Override
        public void info() {
            System.out.println("女");
        }
    };
    private final String name;

    // 枚举类的构造器只能使用private修饰
    private Gender1(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}

/**
 * 包含抽象方法的枚举类
 */
enum Operation {
    PLUS {
        @Override
        public double eval(double x, double y) {
            return x + y;
        }
    },
    MINUS {
        @Override
        public double eval(double x, double y) {
            return x - y;
        }
    },
    TIMES {
        @Override
        public double eval(double x, double y) {
            return x * y;
        }
    };

    // 为枚举类定义一个抽象方法
    // 这个抽象方法有不同的枚举值提供不同的实现
    public abstract double eval(double x, double y);

    public static void main(String[] args) {
        System.out.println(Operation.PLUS.eval(3,4));
        System.out.println(Operation.MINUS.eval(3,4));
        System.out.println(Operation.TIMES.eval(3,4));
    }
}