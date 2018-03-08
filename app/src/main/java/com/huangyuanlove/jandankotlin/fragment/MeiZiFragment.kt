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
import com.huangyuanlove.jandankotlin.domain.MeiZi
import com.huangyuanlove.jandankotlin.domain.RequestResultBean
import com.huangyuanlove.jandankotlin.fragment.adapter.MeiZiAdapter
import com.huangyuanlove.jandankotlin.httpservice.GirlPicsInterface
import com.huangyuanlove.jandankotlin.ui.PhotoViewActivity
import com.huangyuanlove.jandankotlin.ui.RecyclerViewItemClickListener
import kotlinx.android.synthetic.main.fragment_meizi.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.intentFor
import retrofit2.Call
import retrofit2.Response

/**
 * @Describe
 * Created by huangyuan on 2018/3/6.
 */
class MeiZiFragment : Fragment() {
    private var page: Int = 0
    private var meiZiService = JanDanApplication.retrofit.create(GirlPicsInterface::class.java)
    private var meiZis: List<MeiZi> = mutableListOf()
    private lateinit var adapter: MeiZiAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_meizi, container, false)
        return view!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadData(false)
    }

    private fun initView() {
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(activity as Context, R.color.material_light_green_a700))
        swipeRefreshLayout.setOnRefreshListener { loadData(false) }
        val linearLayoutManager = LinearLayoutManager(context)
        recycler_view.layoutManager = linearLayoutManager

        adapter = MeiZiAdapter(activity as Activity, meiZis)
        adapter.onItemClickListener = object : RecyclerViewItemClickListener<MeiZi> {
            override fun onItemClick(t: MeiZi) {
                (activity as Activity).startActivity<PhotoViewActivity>("urls" to t.pics)
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
        val call: retrofit2.Call<RequestResultBean<MeiZi>> = meiZiService.getGirlPics(page)
        call.enqueue(object : retrofit2.Callback<RequestResultBean<MeiZi>> {
            override fun onFailure(call: Call<RequestResultBean<MeiZi>>?, t: Throwable?) {
            }

            override fun onResponse(call: retrofit2.Call<RequestResultBean<MeiZi>>?, response: Response<RequestResultBean<MeiZi>>?) {
                swipeRefreshLayout.setRefreshing(false)
                if (response?.body() != null) {
                    val result = response.body()
                    if (result?.status == "ok") {
                        if (isLoadMore) {
                            meiZis += result.comments
                        } else {
                            meiZis = result.comments
                        }
                        adapter.data = meiZis
                        adapter.notifyDataSetChanged()

                    }
                } else {

                }
            }


        })
    }
}