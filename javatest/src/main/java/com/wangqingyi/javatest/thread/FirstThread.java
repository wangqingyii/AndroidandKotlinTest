package com.wangqingyi.javatest.thread;

/**
 * 示范通过继承Thread类来创建线程类
 *
 * @author WangQingYi
 * @since 2022/2/9
 */
public class FirstThread extends Thread {

    private int i;

    /**
     * 重写run方法，该方法的方法体就是线程执行体
     */
    @Override
    public void run() {
        for (; i < 100; i++) {
            // 当线程类继承Thread类时，直接使用this即可获取当前线程
            // Thread对象的getName()方法返回当前线程的名字
            System.out.println(this.getName() + "  " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            // 调用Thread类的currentThread()方法获取当前线程
            System.out.println(Thread.currentThread().getName() + "  " + i);
            if (i == 20) {
                // 创建并启动第一个线程
                new FirstThread().start();
                // 创建并启动第二个线程
                new FirstThread().start();
            }
        }
    }

//  Thread-1  17
//  Thread-1  18
//  Thread-1  19
//  Thread-1  20  ——> 第二个线程正在执行
//  Thread-1  21
//  Thread-0  7   ——> 两个线程的循环变量的不连续表明他们没有共享数据
//  Thread-0  8   ——> 第一个线程正在执行
//  Thread-0  9
//  Thread-0  10
//  main  72      ——> 主线程正在执行
//  main  73
//  main  74
//  main  75
}
