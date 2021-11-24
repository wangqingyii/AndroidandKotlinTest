package com.wangqingyi.javatest.abstractAndInterfacetest;

import android.icu.util.Output;

/**
 * 面向接口编程
 * <p>
 * 接口体现的是一种规范和实现分离的设计哲学，充分利用接口可以极好的降低程序各个模块之间的耦合，
 * 从而提高系统的可扩展性和维护性，
 *
 * @author WangQingYi
 * @since 2021/11/21
 */
class InterfaceOriented {

    /**
     * 简单的工厂模式
     */
    public class Computer {
        private Output output;

        public Computer(Output output) {
            this.output = output;
        }

        // 定义一个默认获取字符串输入的方法
        public void keyIn(String msg){
//            output.getData(msg);
        }
    }

}
