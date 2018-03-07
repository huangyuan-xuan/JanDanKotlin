package com.huangyuanlove.jandankotlin.ui

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.huangyuanlove.jandankotlin.R
import com.huangyuanlove.jandankotlin.ui.adapter.ContentPagerAdapter
import com.huangyuanlove.jandankotlin.fragment.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


/**
 * @Describe
 * Created by huangyuan on 2018/3/6.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        initView()
    }

    private fun initView() {
        val fragments = ArrayList<Fragment>(5)
        fragments.add(NewsFragment())
        fragments.add(PopularFragment())
        fragments.add(BoredPicsFragment())
        fragments.add(MeiZiFragment())
        fragments.add(JokeFragment())
        val nameList = arrayListOf(R.string.news, R.string.popular, R.string.bored_pics, R.string.meizi, R.string.joke).map { Int -> getString(Int) }
        viewPager.adapter = ContentPagerAdapter(fragments, nameList, supportFragmentManager)
        viewPager.offscreenPageLimit = 2
        tabLayout.setupWithViewPager(viewPager)

    }
}