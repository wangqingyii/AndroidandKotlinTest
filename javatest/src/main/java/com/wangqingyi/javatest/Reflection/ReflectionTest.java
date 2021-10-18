package com.wangqingyi.javatest.Reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 反射练习
 * Java反射机制是程序在运行时，对于任何一个类，都能够知道这个类的所有属性和方法；
 * 对于任意一个对象，都能调用它的任意一个方法和属性。这种动态的获取信息以及动态调用对象的方法的能能成为java反射机制
 *
 * @author WangQingYi
 * @since 2021/10/13
 */
class ReflectionTest {

    public static void main(String[] args) {
        // 获取类的名称
        Class mClass = SonClass.class;
        System.out.println("类的名称：" + mClass.getName());
        // 获取所有public访问权限的变量
        // 包括本类声明和从父类继承的
        Field[] fields = mClass.getFields();
        // 获取所有本类声明的变量(不问访问权限)
        Field[] declaredFields = mClass.getDeclaredFields();
        // 遍历变量并输出变量信息
        for (Field field:fields){
            // 获取访问权限并输出变量信息
            int modifiers = field.getModifiers();
            System.out.println("访问权限:"+Modifier.toString(modifiers));
            // 输出变量的类型及变量名
            System.out.println("变量类型:"+field.getType().getName()+"\n"+"变量名:"+field.getName());
        }
    }
}