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
import com.huangyuanlove.jandankotlin.domain.BoredPic
import com.huangyuanlove.jandankotlin.domain.RequestResultBean
import com.huangyuanlove.jandankotlin.fragment.adapter.BoredAdapter
import com.huangyuanlove.jandankotlin.httpservice.PicsInterface
import com.huangyuanlove.jandankotlin.ui.PhotoViewActivity
import com.huangyuanlove.jandankotlin.ui.RecyclerViewItemClickListener
import kotlinx.android.synthetic.main.fragment_bored_pics.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.intentFor
import retrofit2.Response

/**
 * @Describe
 * Created by huangyuan on 2018/3/6.
 */
class BoredPicsFragment : Fragment() {
    private var page: Int = 0
    private var boredPicService = JanDanApplication.retrofit.create(PicsInterface::class.java)
    private var boredPics: List<BoredPic> = mutableListOf()
    private lateinit var adapter: BoredAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_bored_pics, container, false)

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
        adapter = BoredAdapter(activity as Activity, boredPics)
        adapter.onItemClickListener = object : RecyclerViewItemClickListener<BoredPic> {
            override fun onItemClick(t: BoredPic) {
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
    private fun loadData(isLoadMore:Boolean){
        if (isLoadMore){
            page += 1
        }else{
            page = 1
        }

        val call : retrofit2.Call<RequestResultBean<BoredPic>> = boredPicService.getPics(page)
        call.enqueue(object : retrofit2.Callback<RequestResultBean<BoredPic>>{
            override fun onResponse(call: retrofit2.Call<RequestResultBean<BoredPic>>?, response: Response<RequestResultBean<BoredPic>>?) {
                swipeRefreshLayout.setRefreshing (false)
                if (response?.body() != null ) {
                    val result = response.body()
                    if (result?.status == "ok") {
                        if (isLoadMore) {
                            boredPics += result.comments
                        } else {
                            boredPics = result.comments
                        }
                        adapter.data = boredPics
                        adapter.notifyDataSetChanged()

                    }
                } else {

                }
            }

            override fun onFailure(call: retrofit2.Call<RequestResultBean<BoredPic>>?, t: Throwable?) {
            }
        })

    }
}