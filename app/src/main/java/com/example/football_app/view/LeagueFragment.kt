package com.example.football_app.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.football_app.R
import com.example.football_app.databinding.FragmentLeagueBinding
import com.example.football_app.databinding.FragmentStandingsBinding
import com.example.football_app.network.leagueclass.Data
import com.example.football_app.view.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import java.lang.Exception

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LeagueFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LeagueFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding: FragmentLeagueBinding
    val animalsArray = arrayOf(
        "Matches",
        "Table"
    )
    private val TAG = "LeagueFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLeagueBinding.inflate(layoutInflater, container, false)

        val data = arguments?.getSerializable("liga") as Data

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        Log.d(TAG, "onCreateView: ${data}")
        binding.liga.text = data.name
        try {
            Glide.with(binding.root.context).load(data.logos.dark).into(binding.icond)
        }catch (e: Exception){
            Toast.makeText(binding.root.context, e.message, Toast.LENGTH_SHORT)
                .show()
        }

        val adapter = ViewPagerAdapter(childFragmentManager, lifecycle,data)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = animalsArray[position]
        }.attach()


        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LeagueFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LeagueFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}