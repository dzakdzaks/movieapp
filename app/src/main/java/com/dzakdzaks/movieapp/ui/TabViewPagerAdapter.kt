package com.dzakdzaks.movieapp.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dzakdzaks.movieapp.ui.home.HomeFragment


/**
 * ==================================//==================================
 * ==================================//==================================
 * Created on Thursday, 05 November 2020 at 10:45 AM.
 * Project Name => MovieApp
 * Package Name => com.dzakdzaks.movieapp.ui
 * ==================================//==================================
 * ==================================//==================================
 */

class TabViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val fragmentList: MutableList<Fragment> = ArrayList()

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    fun addFragment(fragment: Fragment) {
        fragmentList.add(fragment)
    }
}