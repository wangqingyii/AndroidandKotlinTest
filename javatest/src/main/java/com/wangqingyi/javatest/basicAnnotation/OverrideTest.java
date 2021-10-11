package com.wangqingyi.javatest.basicAnnotation;

import android.util.Log;

/**
 * Override注解
 * 作用：指定方法覆载，个人理解主要防止程序员犯低级错误，如：把要重写的方法的方法名写错
 * 注意：@Override方法只能修饰方法，不能修饰其他程序元素
 *
 * @author WangQingYi
 * @since  2021/10/11
 */
class OverrideTest {

    public class Fruit{
        public void info(){
            Log.d("info", "水果的info方法");
        }
    }

    class Apple extends Fruit{
        @java.lang.Override
        public void info() {
            super.info();
            Log.d("info", "苹果重写水果的info方法");
        }
    }
}
