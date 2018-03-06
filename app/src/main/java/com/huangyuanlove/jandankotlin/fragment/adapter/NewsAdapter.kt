package com.huangyuanlove.jandankotlin.fragment.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
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
class NewsAdapter(var context: Activity, var data: List<News>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    val inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var news  = data[position]
        holder.commentCount.text = "${news.comment_count}评论"
        holder.time.text = news.date
        holder.author.text = news.author.name
        holder.title.text = news.title
        Glide.with(holder.view).load(news.custom_fields.thumb_c[0]).into(holder.img)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = inflater.inflate(R.layout.item_news,parent,false)
            return ViewHolder(view)

    }





    class ViewHolder(var view: View) :RecyclerView.ViewHolder(view){
        var title = view.findViewById<TextView>(R.id.news_title)
        var author = view.findViewById<TextView>(R.id.news_auth)
        var commentCount = view.findViewById<TextView>(R.id.news_comment_count)
        var time = view.findViewById<TextView>(R.id.news_time)
        var img = view.findViewById<ImageView>(R.id.news_img)
    }
}