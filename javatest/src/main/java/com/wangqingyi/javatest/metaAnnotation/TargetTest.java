package com.wangqingyi.javatest.metaAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Target注解
 * 作用：只能修饰注解定义，他用于指定被修饰的注解只能用于修饰哪些程序单元。
 * value值：
 * ElementType.ANNOTATION_TYPE：指定该策略的注解只能修饰注解
 * ElementType.CONSTRUCTOR：指定该策略的注解只能修饰构造器
 * ElementType.FIELD：指定该策略的直接只能修饰成员变量
 * ElementType.LOCAL_VARIABLE:指定该策略的注解只能修饰局部变量
 * ElementType.METHOD：指定该策略的直接只能修饰方法定义
 * ElementType.PACKAGE：指定该策略的直接只能修饰包定义
 * ElementType.PARAMETER：指定该策略的直接可以修饰参数
 * ElementType.TYPE：指定该策略的注解可以修饰参数
 *
 * @author WangQingYi
 * @since  2021/10/11
 */
class TargetTest {

    @Target(ElementType.METHOD)
    public @interface ActionListenerFor{}
}
