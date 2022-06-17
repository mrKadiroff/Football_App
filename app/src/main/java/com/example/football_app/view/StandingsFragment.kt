package com.example.football_app.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.football_app.R
import com.example.football_app.database.AppDatabase
import com.example.football_app.databinding.FragmentStandingsBinding
import com.example.football_app.network.RetrofitClient
import com.example.football_app.network.leagueclass.Data
import com.example.football_app.repositories.UserRepository
import com.example.football_app.utils.NetworkHelper
import com.example.football_app.utils.Status
import com.example.football_app.view.adapters.StandingsAdapter
import com.example.football_app.view.adapters.UserAdapter
import com.example.football_app.viewmodels.UserViewModel
import com.example.football_app.viewmodels.ViewModelFactory
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


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
    private lateinit var userAdapter: UserAdapter
    lateinit var appDatabase: AppDatabase
    lateinit var userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStandingsBinding.inflate(layoutInflater, container, false)
        appDatabase = AppDatabase.getInstance(binding.root.context)
        val userRepository = UserRepository(RetrofitClient.apiService(),AppDatabase.getInstance(binding.root.context))
        val networkHelper = NetworkHelper(binding.root.context )
        userViewModel = ViewModelProvider(this, ViewModelFactory(userRepository,networkHelper))[UserViewModel::class.java]


//        userAdapter = UserAdapter(userViewModel,viewLifecycleOwner)
//        binding.rv.adapter = userAdapter

        MobileAds.initialize(binding.root.context) {}

        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)





        GlobalScope.launch(Dispatchers.Main) {
            userViewModel.getWord()
                .observe(viewLifecycleOwner) {


                    when (it.status) {
                        Status.LOADING -> {

                        }

                        Status.ERROR -> {
                            Log.d(TAG, "onCreateView: ${it.message}")
                        }

                        Status.SUCCESS -> {

//                            Log.d(TAG, "onCreateView: ${it.data}")
//                           userAdapter.submitList(it.data)
                            adapter = StandingsAdapter(it.data!!,networkHelper,appDatabase,object :StandingsAdapter.OnItemClickListener{
                                override fun onItemClick(malumotlar: Data) {
                                    var bundle = Bundle()
                                    bundle.putSerializable("liga",malumotlar)
                                    findNavController().navigate(R.id.leagueFragment,bundle)
                                }

                            })
                            binding.rv.adapter = adapter

                        }
                    }
                }}



//        GlobalScope.launch(Dispatchers.Main) {
//            userViewModel.getStandings("arg.1")
//                .observe(viewLifecycleOwner) {
//
//
//                    when (it.status) {
//                        Status.LOADING -> {
//
//                        }
//
//                        Status.ERROR -> {
//                            Log.d(TAG, "onCreateView: ${it.message}")
//                        }
//
//                        Status.SUCCESS -> {
//
//                            Log.d(TAG, "onCreateView: ${it.data}")
//
//
//                        }
//                    }
//                }}








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