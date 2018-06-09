package com.example.kotlintest.kotlintest.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.kotlintest.kotlintest.ui.fragment.*

class TabFragmentAda(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    //    var fragments: Array<Fragment> = arrayOf(TabMovieFragment(), TabBookFragment(), TabMusicFragment(), TabBrodFragment(), TabPhotoFragment())
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return TabMovieFragment()
            1 -> return TabBookFragment()
            2 -> return TabMusicFragment()
            3 -> return TabBrodFragment()
            4 -> return TabPhotoFragment()
        }
        return Fragment()
    }

    override fun getCount(): Int {
        return 5
    }

}