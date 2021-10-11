package com.wangqingyi.javatest.metaAnnotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Documented注解
 * 作用：指定被该元注解修饰的注解类将被javadoc工具提取成文档，如果定义注解类时使用该注解修饰，
 * 则所有使用该注解修饰程序元素的API文档中间会包含该注解说明
 *
 * @author WangQingYi
 * @since 2021/10/11
 */
class DocumentedTest {
    // 编译器把注解记录在class文件中
    @Retention(RetentionPolicy.RUNTIME)
    // 修饰方法定义
    @Target(ElementType.METHOD)
    // 定义Testable注解将被javadoc工具提取
    @Documented
    public @interface Testable {
    }
}
