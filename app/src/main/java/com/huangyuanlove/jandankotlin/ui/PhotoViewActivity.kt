package com.huangyuanlove.jandankotlin.ui

import android.app.Activity
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView
import com.huangyuanlove.jandankotlin.R
import kotlinx.android.synthetic.main.activity_photo_view.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.yesButton

/**
 * @Describe
 * Created by huangyuan on 2018/3/8.
 */
class PhotoViewActivity : Activity() {
    lateinit var photosUrl: List<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_view)
        photosUrl = intent.getStringArrayListExtra("urls")
        if ( photosUrl.isEmpty()) {
            alert("网络出错，请重新获取") {
                yesButton { finish() }
                noButton { finish() }
            }.show()
        } else {
            initView()
        }
    }

    private fun initView() {
        var photoViews = arrayListOf<PhotoView>()
        for (url in photosUrl) {
            var photoView = PhotoView(this)
            photoView.scaleType = ImageView.ScaleType.FIT_CENTER
            photoView.maximumScale = 10f
            Glide.with(this).load(url).into(photoView)
            photoViews.add(photoView)
            photoView.onClick { finish() }
        }
        viewPager.adapter = PhotoViewAdapter(photoViews)
    }


    class PhotoViewAdapter(var photoViews: List<PhotoView>) : PagerAdapter() {
        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }

        override fun getCount(): Int {
            return photoViews.size
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(photoViews[position])
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            container.addView(photoViews[position])
            return photoViews[position]
        }
    }

}