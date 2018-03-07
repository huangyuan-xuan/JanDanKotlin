package com.huangyuanlove.jandankotlin.fragment

import android.app.Activity
import android.content.Context
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
import com.huangyuanlove.jandankotlin.domain.Joke
import com.huangyuanlove.jandankotlin.domain.RequestResultBean
import com.huangyuanlove.jandankotlin.fragment.adapter.JokeAdapter
import com.huangyuanlove.jandankotlin.httpservice.JokeInterface
import kotlinx.android.synthetic.main.fragment_joke.*
import retrofit2.Response

/**
 * @Describe
 * Created by huangyuan on 2018/3/6.
 */
class JokeFragment :Fragment(){
    private var page: Int = 0
    private var jokeService = JanDanApplication.retrofit.create(JokeInterface::class.java)
    private var jokes: List<Joke> = mutableListOf()
    private lateinit var adapter: JokeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_joke, container, false)
        return view!!
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadData(false)
    }
    private fun initView(){
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(activity as Context,R.color.material_light_green_a700))
        swipeRefreshLayout.setOnRefreshListener { loadData(false) }

        val linearLayoutManager = LinearLayoutManager(context)
        recycler_view.layoutManager = linearLayoutManager
        recycler_view.itemAnimator = DefaultItemAnimator()
        adapter = JokeAdapter(activity as Activity, jokes)
        recycler_view.adapter = adapter
        recycler_view.addOnScrollListener(object : RecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore() {
                loadData(true)
            }

        })
    }
    private fun loadData(isLoadMore:Boolean){
        if (isLoadMore){
            page += 1
        }else{
            page = 1
        }

        val call : retrofit2.Call<RequestResultBean<Joke>> = jokeService.getJokes(page)
        call.enqueue(object : retrofit2.Callback<RequestResultBean<Joke>>{
            override fun onResponse(call: retrofit2.Call<RequestResultBean<Joke>>?, response: Response<RequestResultBean<Joke>>?) {
                swipeRefreshLayout.setRefreshing (false)
                if (response?.body() != null ) {
                    val result = response.body()
                    if (result?.status == "ok") {
                        if (isLoadMore) {
                            jokes += result.comments
                        } else {
                            jokes = result.comments
                        }
                        adapter.data = jokes
                        adapter.notifyDataSetChanged()

                    }
                } else {

                }
            }

            override fun onFailure(call: retrofit2.Call<RequestResultBean<Joke>>?, t: Throwable?) {
            }
        })

    }
}