package com.wangqingyi.javatest.basicAnnotation;

import android.util.Log;

/**
 * FunctionalInterface注解
 * 作用：Java8规定：如果接口中只有一个抽象方法(可以包含多个默认方法或者多个static方法)，该接口就是函数式接口
 * 注意：@FunctionalInterface注解只能修饰接口，不能修饰其他程序元素
 *
 * @author WangQingYi
 * @since 2021/10/11
 */
class FunctionalInterfaceTest {

    /**
     * 添加@FunctionalInterface注解后此接口中只能出现一个抽象方法，若出现多个将引起出错
     */
    @FunctionalInterface
    public interface FunInterface {
        static void foo() {
            Log.d("FunInterface", "foo类方法");
        }

        default void bar() {
            Log.d("FunInterface", "bar类方法");
        }

        // 只定义一个抽象方法
        void test();
    }
}
