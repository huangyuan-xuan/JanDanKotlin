package com.huangyuanlove.jandankotlin

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.AbsListView.OnScrollListener.SCROLL_STATE_IDLE

/**
 * @Describe
 * Created by huangyuan on 2018/3/6.
 */
abstract class RecyclerViewScrollListener(var linearLayoutManager:LinearLayoutManager) : RecyclerView.OnScrollListener() {
    private val previousTotal: Int = 0
    private var isBottom: Boolean = false
    private var firstVisibleItem: Int = 0
    private var visibleItemCount: Int = 0
    private var totalItemCount: Int = 0

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        visibleItemCount = recyclerView?.getChildCount()!!
        totalItemCount = linearLayoutManager.itemCount
        firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition()
        isBottom = firstVisibleItem + visibleItemCount == totalItemCount

    }

    override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
        if (newState == SCROLL_STATE_IDLE && isBottom) {
            onLoadMore()
        }
    }

    abstract fun onLoadMore()
}