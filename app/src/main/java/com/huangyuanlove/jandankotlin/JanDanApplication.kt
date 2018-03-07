package com.huangyuanlove.jandankotlin

import android.app.Application
import android.text.TextUtils
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*

/**
 * @Describe
 * Created by huangyuan on 2018/3/6.
 */
class JanDanApplication : Application() {
    companion object {
        var gson = GsonBuilder()
                .registerTypeAdapter(Calendar::class.java, object : JsonDeserializer<Calendar> {
                    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Calendar {
                        var calendar = Calendar.getInstance()
                        var date = Date()
                        if (!TextUtils.isEmpty(json?.asString?.trim())) {
                            date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(json?.asString?.trim())
                        }
                        calendar.time = date
                        return calendar
                    }

                })
                .create()
        var retrofit = Retrofit.Builder().baseUrl("http://i.jandan.net/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    override fun onCreate() {
        super.onCreate()

    }
}