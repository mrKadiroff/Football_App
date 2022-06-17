package com.example.football_app.view.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.football_app.network.leagueclass.Data
import com.example.football_app.view.tabfragments.MatchesFragment
import com.example.football_app.view.tabfragments.TableFragment


private const val NUM_TABS = 2

class ViewPagerAdapter (fragmentManager: FragmentManager, lifecycle: Lifecycle,val data: Data) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return MatchesFragment.newInstance(data)
            1 -> return TableFragment.newInstance(data)
        }
        return Fragment()
    }
}