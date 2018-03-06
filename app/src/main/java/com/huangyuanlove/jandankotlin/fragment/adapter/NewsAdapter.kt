package com.huangyuanlove.jandankotlin.fragment.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.huangyuanlove.jandankotlin.R
import com.huangyuanlove.jandankotlin.domain.News
import kotlinx.android.synthetic.main.item_news.view.*

/**
 * @Describe
 * Created by huangyuan on 2018/3/6.
 */
class NewsAdapter(var context: Activity, var data: List<News> ,var inflater: LayoutInflater) : BaseAdapter() {


    init {
        inflater = LayoutInflater.from(context)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var newsBean = data.get(position)
        var viewHolder: ViewHolder?
        var view:View
        if (convertView == null) {
            view = inflater.inflate(R.layout.item_news,parent,false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder

        } else {
            view = convertView
            viewHolder = convertView.tag as ViewHolder
        }

        viewHolder.title.text = newsBean.title
        viewHolder.author.text = newsBean.author.name
        viewHolder.time.text = newsBean.date
        viewHolder.commentCount.text = "${newsBean.comment_count}评论"
        Glide.with(view).load(newsBean.custom_fields.thumb_c?.get(0)).into(viewHolder.img)



        return view

    }

    override fun getItem(position: Int): Any {
        return data?.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.size
    }

    class ViewHolder(var view: View) {
        var title = view.findViewById<TextView>(R.id.news_title)
        var author = view.findViewById<TextView>(R.id.news_auth)
        var commentCount = view.findViewById<TextView>(R.id.news_comment_count)
        var time = view.findViewById<TextView>(R.id.news_time)
        var img = view.findViewById<ImageView>(R.id.news_img)
    }
}