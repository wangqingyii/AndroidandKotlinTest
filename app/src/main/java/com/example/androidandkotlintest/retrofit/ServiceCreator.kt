package com.example.androidandkotlintest.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Retrofit构建器封装
 *
 * @author WangQingYi
 * @since  2021/10/19
 */
object ServiceCreator {

    private const val BASE_URL = "http://10.0.2.2/"

    // 调用Builder()方法构建Retrofit对象
    private val retrofit = Retrofit.Builder()
        // baseUrl()方法指定请求的根路径
        .baseUrl("http://10.2.2/")
        // 指定Retrofit在解析数据时所使用的库
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    inline fun <reified T> create(): T = create(T::class.java)
}