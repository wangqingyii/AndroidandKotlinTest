package com.wangqingyi.javatest.basicAnnotation;

import java.util.ArrayList;
import java.util.List;

/**
 * SuppressWarnings注解
 * 作用：指示被该注解修饰的程序元素(以及该程序元素中的所有子元素)取消显示指定的编译器警告
 *
 * @author WangQingYi
 * @since 2021/10/11
 */
@SuppressWarnings(value = "unchecked")
class SuppressWarningsTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList();
    }
}
