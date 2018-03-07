package com.huangyuanlove.jandankotlin.ui

import android.app.Activity
import android.os.Bundle
import android.text.Html
import com.bumptech.glide.Glide
import com.huangyuanlove.jandankotlin.JanDanApplication
import com.huangyuanlove.jandankotlin.R
import com.huangyuanlove.jandankotlin.domain.News
import com.huangyuanlove.jandankotlin.domain.NewsDetail
import com.huangyuanlove.jandankotlin.httpservice.NewsInterface
import kotlinx.android.synthetic.main.activity_news_detail.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

/**
 * @Describe
 * Created by huangyuan on 2018/3/7.
 */
class NewsDetailActivity : Activity() {
    var news: News? = null
    var context = this
    private var newsService = JanDanApplication.retrofit.create(NewsInterface::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        news = intent.getParcelableExtra("news")
        initView()
        if (news == null) {
            alert("网络出错，请重新获取") {
                yesButton { finish() }
                noButton { finish() }
            }.show()
        } else {
            loadData()
        }
    }

    private fun initView(){
        Glide.with(context).load(news!!.custom_fields.thumb_c[0]).into(image)
        news_title.text = news?.title
        author.text  = news?.author?.name
        val currentCalendar = Calendar.getInstance()
        if (currentCalendar.get(Calendar.YEAR) == news?.date?.get(Calendar.YEAR)) {
            if (currentCalendar.get(Calendar.DAY_OF_YEAR) == news?.date?.get(Calendar.DAY_OF_YEAR)) {
                time.text = SimpleDateFormat("HH:mm").format(news?.date?.timeInMillis)
            } else {
                time.text = SimpleDateFormat("MM-dd HH:mm").format(news?.date?.timeInMillis)
            }
        } else {
            time.text = SimpleDateFormat("yyyy-MM-dd HH:mm").format(news?.date?.timeInMillis)
        }
        excerpt.text = news?.excerpt

        back.onClick{finish()}
        more.onClick { toast("更多") }
        comment.onClick { toast("comment") }
    }


    private fun loadData() {
        val call = newsService.getNewsDetail(news!!.id)
        call.enqueue(object : Callback<NewsDetail> {
            override fun onFailure(call: Call<NewsDetail>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<NewsDetail>?, response: Response<NewsDetail>?) {
                if (response?.body() != null) {
                    val result = response.body()
                    if (result?.status == "ok") {
                        news_content.loadData(result.post.content,"text/html; charset=UTF-8",null)
                    } else {

                    }
                } else {

                }


            }

        })
    }

}