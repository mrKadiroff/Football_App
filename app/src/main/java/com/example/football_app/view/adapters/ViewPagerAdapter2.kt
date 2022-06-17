package com.example.football_app.view.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.football_app.network.leagueclass.Data
import com.example.football_app.view.minitabfragments.AllFragment
import com.example.football_app.view.minitabfragments.AwayFragment
import com.example.football_app.view.minitabfragments.HomeFragment
import com.example.football_app.view.tabfragments.MatchesFragment
import com.example.football_app.view.tabfragments.TableFragment

class ViewPagerAdapter2(
    var list: Array<String>,
    fragmentManager: FragmentManager, lifecycle: Lifecycle,val param1: Data?
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return AllFragment.newInstance(param1!!)
            1 -> return HomeFragment()
            2 -> return AwayFragment()
        }
        return Fragment()
    }
}