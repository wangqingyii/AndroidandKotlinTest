package com.wangqingyi.javatest.annotation;

/**
 * 自定义注解练习
 * 使用：定义新的注解的类型使用@interface
 * 注意：@interface定义的注解的确非常像定义了一个注解接口，这个注解接口继承了java.long.annotation.Annotation
 * 接口，这一点可以通过反射看到MyTag接口里包含了java.long.annotation.Annotation接口里的方法
 *
 * 知识点：
 * 根据直接是否可以包含成员变量，可以把注解分为如下两类：
 * 标记注解：没有定义成员变量的注解类型被称为标记。这种注解仅利用自身的存在与否来提供信息。如：@Override等
 * 元数据注解：包含成员变量的注解，因为他们可以接受更多的元数据，所以也被称为元数据注解
 *
 * @author WangQingYi
 * @since 2021/10/12
 */
class CustomAnnotation {
    // 定义一个简单的注解类型
    public @interface Test1 {
    }

    // 使用新定义的Test注解修饰类
    @Test1
    public class MyClass {
    }

    /**
     * 注解中的成员变量在注解中以无形参的形式来声明，其方法名和返回值定义了failure成员变量的名字和类型。
     */
    public @interface MyTag {
        // 定义带两个成员变量的注解
        // 注解中的成员变量以方法的形式来定义
        // 一旦在注解里定义了成员变量之后，使用该注解时就应该为它的成员变量指定值
        String name() default "李四";

        // 可以使用default为成员变量指定初始值
        int age() default 20;
    }

    public class Test2 {
        @MyTag(name = "张三", age = 10)
        public void info() {
        }

        /**
         * 如果注解的成员变量指定了初始值，name可以不为它的成员变量指定值
         * 在使用带值注解时为其成员变量指定值，默认值不会起作用
         */
        @MyTag
        public void init() {
        }
    }
}
