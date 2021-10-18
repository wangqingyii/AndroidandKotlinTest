package com.example.androidandkotlintest.retrofit

import com.example.androidandkotlintest.retrofit.bean.App
import retrofit2.http.GET

/**
 * 定义接口文件，一般以功能命名
 *
 * @author WangQingYi
 * @since  2021/10/18
 */
interface RetrofitService {

    @GET("get_data.json")
    fun getAppData(): retrofit2.Call<List<App>>
}