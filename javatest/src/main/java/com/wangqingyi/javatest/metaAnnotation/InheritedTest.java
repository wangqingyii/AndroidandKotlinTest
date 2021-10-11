package com.wangqingyi.javatest.metaAnnotation;

import android.util.Log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Inherited注解
 * 作用：指定被修饰的注解将具有继承性，如果某个类使用了@Xxx注解(定义该注解时使用了@Inherited修饰)修饰，
 * 则其子类将自动被@Xxx修饰
 *
 * @author WangQingYi
 * @since 2021/10/11
 */
class InheritedTest extends Base {
    // 指定该策略的注解可以修饰参数
    @Target(ElementType.TYPE)
    // 编译器把注解记录在class文件中,当程序运行时，JVM也可获取注解信息，程序可以通过反射获取该注解信息。
    @Retention(RetentionPolicy.RUNTIME)
    @InInherited
    public @interface InInherited {
    }

    public static void main(String[] args) {
        // 打印InheritedTest1类是否有@InInherited修饰
        Log.d("InheritedTest", "" + InheritedTest.class.isAnnotationPresent(InInherited.class));
    }
}

/**
 * 定义一个Base基类，该基类使用了@Inherited注解，则Base类的子类将会默认使用@InInherited修饰
 */
@InheritedTest.InInherited
class Base {
}