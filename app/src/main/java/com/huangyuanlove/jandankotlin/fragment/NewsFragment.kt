package com.huangyuanlove.jandankotlin.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.huangyuanlove.jandankotlin.JanDanApplication
import com.huangyuanlove.jandankotlin.R
import com.huangyuanlove.jandankotlin.RecyclerViewScrollListener
import com.huangyuanlove.jandankotlin.domain.News
import com.huangyuanlove.jandankotlin.domain.RequestResultBean
import com.huangyuanlove.jandankotlin.fragment.adapter.NewsAdapter
import com.huangyuanlove.jandankotlin.fragment.adapter.NewsAdapter.OnItemClickListener
import com.huangyuanlove.jandankotlin.httpservice.NewsInterface
import com.huangyuanlove.jandankotlin.ui.NewsDetailActivity
import kotlinx.android.synthetic.main.fragment_news.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @Describe
 * Created by huangyuan on 2018/3/6.
 */
class NewsFragment : Fragment() {
    private var page: Int = 0
    private var newsService = JanDanApplication.retrofit.create(NewsInterface::class.java)
    private var newsz: List<News> = mutableListOf()
    private lateinit var adapter: NewsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)
        return view!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadData(false)
    }


    private fun initView() {
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(activity as Context, R.color.material_light_green_a700))
        swipeRefreshLayout.setOnRefreshListener { loadData(false)}
        val linearLayoutManager = LinearLayoutManager(context)
        recycler_view.layoutManager = linearLayoutManager
        recycler_view.itemAnimator = DefaultItemAnimator()
        recycler_view.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
        adapter = NewsAdapter(activity as Activity, newsz)
        adapter.onItemClickListener = object : OnItemClickListener{
            override fun onItemClick(news:News) {
                val intent  = Intent(context,NewsDetailActivity::class.java)
                intent.putExtra("news",news)
                startActivity(intent)
            }

        }
        recycler_view.adapter = adapter

        recycler_view.addOnScrollListener(object : RecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore() {
                loadData(true)
            }
        })



    }

    private fun loadData(isLoadMore: Boolean) {
        if (isLoadMore) {
            page += 1
        } else {
            page = 1
        }

        val call: Call<RequestResultBean<News>> = newsService.getNews(page)
        call.enqueue(object : Callback<RequestResultBean<News>> {

            override fun onResponse(call: Call<RequestResultBean<News>>?, response: Response<RequestResultBean<News>>?) {
                swipeRefreshLayout.setRefreshing (false)
                if (response?.body() != null ) {
                    val result = response.body()
                    if (result?.status == "ok") {
                        if (isLoadMore) {
                            newsz += result.posts
                        } else {
                            newsz = result.posts
                        }
                        adapter.data = newsz
                        adapter.notifyDataSetChanged()

                    }
                } else {

                }
            }

            override fun onFailure(call: Call<RequestResultBean<News>>?, t: Throwable?) {
                swipeRefreshLayout.setRefreshing (false)
            }
        })
    }
}