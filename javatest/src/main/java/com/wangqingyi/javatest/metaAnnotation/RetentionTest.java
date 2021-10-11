package com.wangqingyi.javatest.metaAnnotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Retention注解
 * 作用：@Retention注解只能用于修饰注解定义，用于指定被修饰的注解可以保留多长时间，
 * 其注解包含一个RetentionPolicy类型的value变量，所以使用@Retention时必须为该value成员变量指定值
 * <p>
 * RetentionPolicy.CLASS：编译器将把注解记录在class文件中。当运行程序时，JVM不可获取注解信息，这是默认值。
 * <p>
 * RetentionPolicy.RUNTIME：编译器把注解记录在class文件中。当程序运行时，JVM也可获取注解信息，
 * 程序可以通过反射获取该注解信息。
 * <p>
 * RetentionPolicy.SOURCE：注解只保留在源代码中，编译器直接丢弃这种注解。
 *
 * @author WangQingYi
 * @since 2021/10/11
 */
class RetentionTest {
    // 定义下面的@Testable注解保留到运行时
    @Retention(value = RetentionPolicy.RUNTIME)
    public @interface Testable { }

    /**
     * 也可以采用此方式为value指定值
     */
    // 定义下面的@Testable注解保留到运行时
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Testable1 { }
}
