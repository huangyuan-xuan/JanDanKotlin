package com.huangyuanlove.jandankotlin.httpservice

import com.huangyuanlove.jandankotlin.domain.News
import com.huangyuanlove.jandankotlin.domain.NewsDetail
import com.huangyuanlove.jandankotlin.domain.RequestResultBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @Describe
 * Created by huangyuan on 2018/3/6.
 */
interface NewsInterface {
    @GET("?oxwlxojflwblxbsapi=get_recent_posts&include=url,date,tags,author,title,excerpt,comment_count,comment_status,custom_fields&custom_fields=thumb_c,views&dev=1")
    fun getNews(@Query("page") pageNumber: Int): Call<RequestResultBean<News>>

    @GET("?oxwlxojflwblxbsapi=get_post&include=content,date,modified")
    fun getNewsDetail(@Query("id") id: Int):Call<NewsDetail>
}