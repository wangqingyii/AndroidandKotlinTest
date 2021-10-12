package com.wangqingyi.javatest.annotation.basicAnnotation;

import android.util.Log;

/**
 * Deprecated注解
 * 作用：用于表示某个程序元素(类，方法)，已过时，当其他程序使用已过时的类、方法时，编译器将会给出警告
 * 注意：@Deprecated的作用与文档中的@deprecated标记的作用基本相同，但它们的用法不同，前者是JDK15才支持的注解，
 * 无需放在文档注释语法(/*.../部分)中，而是直接用于修饰程序中的程序元素，如方法、类、接口等。
 *
 * @author WangQingYi
 * @since 2021/10/11
 */
class DeprecatedTest {


    public static void main(String[] args) {
        // 编译器提示该方法即将被弃用
        new Apple().info();
    }
}

class Apple {
    @Deprecated
    public void info() {
        Log.d("info", "苹果重写水果的info方法");
    }
}