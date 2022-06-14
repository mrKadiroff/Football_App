package com.example.football_app.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.football_app.R
import com.example.football_app.databinding.FragmentStandingsBinding
import com.example.football_app.network.RetrofitClient
import com.example.football_app.network.leagueclass.LeagueResult
import com.example.football_app.network.standingsclass.Standings
import com.example.football_app.view.adapters.StandingsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StandingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StandingsFragment : Fragment() {
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

    lateinit var binding: FragmentStandingsBinding
    lateinit var adapter: StandingsAdapter
    private val TAG = "StandingsFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStandingsBinding.inflate(layoutInflater, container, false)

                RetrofitClient.apiService().getLeagues().enqueue(object:
                    Callback<LeagueResult> {
            override fun onResponse(call: Call<LeagueResult>, response: Response<LeagueResult>) {
                if (response.isSuccessful){
                    val body = response.body()
                    Log.d(TAG, "onResponse: ${body!!.data}")
                    adapter = StandingsAdapter(body.data)
                    binding.rv.adapter = adapter
                }
            }

            override fun onFailure(call: Call<LeagueResult>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })




//        RetrofitClient.apiService().getStandings("arg.1").enqueue(object:
//            Callback<Standings> {
//            override fun onResponse(call: Call<Standings>, response: Response<Standings>) {
//                if (response.isSuccessful){
//                    val body = response.body()
//                    Log.d(TAG, "onResponse: ${body!!.data}")
//
//                }
//            }
//
//            override fun onFailure(call: Call<Standings>, t: Throwable) {
//                Log.d(TAG, "onFailure: ${t.message}")
//            }
//
//        })


        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StandingsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StandingsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}