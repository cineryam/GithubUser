@file:Suppress("DEPRECATION")

package com.example.githubuser.adapter

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.githubuser.R
import com.example.githubuser.fragment.FollowersFragment
import com.example.githubuser.fragment.FollowingFragment

class SectionsPagerAdapter(private val ctx: Context, fm: FragmentManager, data: Bundle) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var fragmentBundle : Bundle

    init {
        fragmentBundle = data
    }

    @StringRes
    private val pages = intArrayOf(
        R.string.tab1,
        R.string.tab2 )

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position) {
            0 -> fragment = FollowersFragment()
            1 -> fragment = FollowingFragment()
        }

        fragment?.arguments = this.fragmentBundle
        return fragment as Fragment
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return ctx.resources.getString(pages[position])
    }
}