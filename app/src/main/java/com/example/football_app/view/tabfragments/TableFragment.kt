package com.example.football_app.view.tabfragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.football_app.R
import com.example.football_app.databinding.FragmentTableBinding
import com.example.football_app.databinding.ItemTabBinding
import com.example.football_app.network.leagueclass.Data
import com.example.football_app.view.adapters.ViewPagerAdapter2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"


/**
 * A simple [Fragment] subclass.
 * Use the [TableFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TableFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Data? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as Data

        }
    }

    lateinit var binding: FragmentTableBinding
    val tabArray = arrayOf(
        "All",
        "Home",
        "Away"
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTableBinding.inflate(layoutInflater, container, false)

        setViewPager()


        return binding.root
    }

    private fun setViewPager() {
        val viewPager = binding.viewPager2
        val tabLayoutgl =  binding.tabLayoutgalvni

        val adapter = ViewPagerAdapter2(tabArray,childFragmentManager, lifecycle,param1)
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayoutgl, viewPager) { tab, position ->
            val itemBinding = ItemTabBinding.inflate(layoutInflater)
            tab.customView = itemBinding.root
            itemBinding.titleTv.text = tabArray!![position]




            if (position == 0) {
                itemBinding.titleTv.setBackgroundResource(R.drawable.tab_item_back_selected)
                itemBinding.titleTv.setTextColor(Color.WHITE)

            } else {
                itemBinding.titleTv.setTextColor(Color.parseColor("#FFFFFF"))
                itemBinding.titleTv.setBackgroundResource(R.drawable.tab_item_back_unselected)
            }

            //Some implementation
        }.attach()



        binding.tabLayoutgalvni.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val itemTabBinding = ItemTabBinding.bind(tab?.customView!!)
                itemTabBinding.titleTv.setBackgroundResource(R.drawable.tab_item_back_selected)

                itemTabBinding.titleTv.setTextColor(Color.WHITE)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val itemTabBinding = ItemTabBinding.bind(tab?.customView!!)
                itemTabBinding.titleTv.setTextColor(Color.parseColor("#FFFFFF"))
                itemTabBinding.titleTv.setBackgroundResource(R.drawable.tab_item_back_unselected)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TableFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Data) =
            TableFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)

                }
            }
    }
}