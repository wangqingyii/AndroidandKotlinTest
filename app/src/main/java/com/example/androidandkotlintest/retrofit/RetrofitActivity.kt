package com.example.androidandkotlintest.retrofit

import android.util.Log
import com.example.androidandkotlintest.base.BaseActivity
import com.example.androidandkotlintest.databinding.ActivityRetrofitBinding
import com.example.androidandkotlintest.retrofit.bean.App
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitActivity : BaseActivity<ActivityRetrofitBinding>() {

    override fun ActivityRetrofitBinding.initView() {
        vButton.setOnClickListener {
            initRetrofit()
            Log.d("qweqwe", "initView: ")
        }
    }

    /**
     * 初始化Retrofit
     */
    private fun initRetrofit() {
        // 调用Builder()方法构建Retrofit对象
        val retrofit = Retrofit.Builder()
            // baseUrl()方法指定请求的根路径
            .baseUrl("http://10.2.2/")
            // 指定Retrofit在解析数据时所使用的库
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        // 调用create()方法传入具体的Service接口所对应的Class类型，创建该接口的动态代理对象
        val retrofitService = retrofit.create(RetrofitService::class.java)
        // 调用enqueue进行网络请求，是异步的
        retrofitService.getAppData().enqueue(object : Callback<List<App>> {
            override fun onResponse(call: Call<List<App>>, response: Response<List<App>>) {
                // 得到解析后的对象
                val list = response.body()
                if (list.isNotEmpty()) {
                    for (app in list) {
                        Log.d("qwe", "id = ${app.id}")
                        Log.d("qwe", "name = ${app.name}")
                        Log.d("qwe", "version = ${app.version}")
                    }
                }
            }

            override fun onFailure(call: Call<List<App>>?, t: Throwable?) {
                t?.printStackTrace()
            }

        })
    }
}