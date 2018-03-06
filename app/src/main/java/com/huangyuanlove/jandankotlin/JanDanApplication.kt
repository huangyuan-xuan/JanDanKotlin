package com.huangyuanlove.jandankotlin

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @Describe
 * Created by huangyuan on 2018/3/6.
 */
class JanDanApplication : Application(){
    companion object {
        var retrofit = Retrofit.Builder().baseUrl("http://i.jandan.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    override fun onCreate() {
        super.onCreate()
    }
}