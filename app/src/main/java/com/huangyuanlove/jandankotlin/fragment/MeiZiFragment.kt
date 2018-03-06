package com.huangyuanlove.jandankotlin.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.huangyuanlove.jandankotlin.R
import kotlinx.android.synthetic.main.fragment_news.*

/**
 * @Describe
 * Created by huangyuan on 2018/3/6.
 */
class MeiZiFragment : Fragment(){
    var page :Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_meizi, container, false)
        initView()
        return view
    }

    private fun initView(){
//        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(activity as Context,R.color.material_light_green_a700))
//        swipeRefreshLayout.setOnRefreshListener {  }
    }
    private fun loadData(isLoadMore:Boolean){
        if (isLoadMore){
            page += 1
        }else{
            page = 1
        }
    }
}