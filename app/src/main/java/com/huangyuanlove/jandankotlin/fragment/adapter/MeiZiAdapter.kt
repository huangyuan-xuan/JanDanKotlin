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
import com.huangyuanlove.jandankotlin.domain.BoredPic
import com.huangyuanlove.jandankotlin.domain.MeiZi
import com.huangyuanlove.jandankotlin.domain.News
import kotlinx.android.synthetic.main.item_news.view.*
import org.jetbrains.anko.find
import java.text.SimpleDateFormat
import java.util.*

/**
 * @Describe
 * Created by huangyuan on 2018/3/6.
 */
class MeiZiAdapter(var context: Activity, var data: List<MeiZi>) : RecyclerView.Adapter<MeiZiAdapter.ViewHolder>() {
    private val inflater: LayoutInflater =  LayoutInflater.from(context)


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val boredPic = data[position]
        val currentCalendar = Calendar.getInstance()

        if (currentCalendar.get(Calendar.YEAR) == boredPic.comment_date.get(Calendar.YEAR)) {
            if(currentCalendar.get(Calendar.DAY_OF_YEAR) ==boredPic.comment_date.get(Calendar.DAY_OF_YEAR) ){
                holder.time.text = SimpleDateFormat("HH:mm").format(boredPic.comment_date.timeInMillis)
            }else{
                holder.time.text = SimpleDateFormat("MM-dd HH:mm").format(boredPic.comment_date.timeInMillis)
            }
        } else {
            holder.time.text = SimpleDateFormat("yyyy-MM-dd HH:mm").format(boredPic.comment_date.timeInMillis)
        }

        holder.author.text = boredPic.comment_author
        holder.content.text = boredPic.text_content
        holder.praise.text = "OO ${boredPic.vote_positive}"
        holder.oppose.text = "XX ${boredPic.vote_negative}"
        holder.comment.text = "吐槽${boredPic.sub_comment_count}"
        Glide.with(holder.view).load(boredPic.pics[0]).into(holder.img)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.item_bored_pic, parent, false)
        return ViewHolder(view)

    }


    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var author = view.find<TextView>(R.id.author)
        var time = view.find<TextView>(R.id.time)
        var content = view.find<TextView>(R.id.content)
        var img = view.find<ImageView>(R.id.image)
        var praise = view.find<TextView>(R.id.praise)
        var oppose = view.find<TextView>(R.id.oppose)
        var comment = view.find<TextView>(R.id.comment)
        var more = view.find<TextView>(R.id.more)

    }
}