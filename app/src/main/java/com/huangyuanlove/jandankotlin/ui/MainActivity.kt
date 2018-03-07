package com.huangyuanlove.jandankotlin.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.huangyuanlove.jandankotlin.R
import com.huangyuanlove.jandankotlin.fragment.BoredPicsFragment
import com.huangyuanlove.jandankotlin.fragment.JokeFragment
import com.huangyuanlove.jandankotlin.fragment.MeiZiFragment
import com.huangyuanlove.jandankotlin.fragment.NewsFragment
import com.huangyuanlove.jandankotlin.ui.adapter.ContentPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*


/**
 * @Describe
 * Created by huangyuan on 2018/3/6.
 */
class MainActivity : AppCompatActivity() , Toolbar.OnMenuItemClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initView()
    }

    private fun initView() {
        val fragments = ArrayList<Fragment>(5)
        fragments.add(NewsFragment())
        fragments.add(BoredPicsFragment())
        fragments.add(MeiZiFragment())
        fragments.add(JokeFragment())
        val nameList = arrayListOf(R.string.news, R.string.bored_pics, R.string.meizi, R.string.joke).map { Int -> getString(Int) }
        viewPager.adapter = ContentPagerAdapter(fragments, nameList, supportFragmentManager)
        viewPager.offscreenPageLimit = 2
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.add->{

            }
            R.id.top ->{

            }
            R.id.action_setting->{

            }
            else ->{

            }
        }
        return false
    }

}