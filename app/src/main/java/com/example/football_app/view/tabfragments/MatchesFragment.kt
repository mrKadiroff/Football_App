package com.example.football_app.view.tabfragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.football_app.database.AppDatabase
import com.example.football_app.databinding.FragmentMatchesBinding
import com.example.football_app.network.RetrofitClient
import com.example.football_app.network.leagueclass.Data
import com.example.football_app.repositories.UserRepository
import com.example.football_app.utils.NetworkHelper
import com.example.football_app.utils.Status
import com.example.football_app.view.adapters.SeasonsAdapter
import com.example.football_app.viewmodels.UserViewModel
import com.example.football_app.viewmodels.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"


/**
 * A simple [Fragment] subclass.
 * Use the [MatchesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */


class MatchesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Data? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as Data
        }
    }
    lateinit var binding: FragmentMatchesBinding
    lateinit var userViewModel: UserViewModel
    lateinit var seasonsAdapter: SeasonsAdapter
    private val TAG = "MatchesFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMatchesBinding.inflate(layoutInflater, container, false)
        val userRepository = UserRepository(RetrofitClient.apiService(), AppDatabase.getInstance(binding.root.context))
        val networkHelper = NetworkHelper(binding.root.context )
        userViewModel = ViewModelProvider(this, ViewModelFactory(userRepository,networkHelper))[UserViewModel::class.java]

        GlobalScope.launch(Dispatchers.Main) {
            userViewModel.getSeasons(param1!!.id)
                .observe(viewLifecycleOwner) {


                    when (it.status) {
                        Status.LOADING -> {

                        }

                        Status.ERROR -> {
                            Log.d(TAG, "onCreateView: ${it.message}")
                        }

                        Status.SUCCESS -> {

                            Log.d(TAG, "onCreateView: ${it.data}")
                            seasonsAdapter = SeasonsAdapter(it.data!!.seasons)
                            binding.rv2.adapter = seasonsAdapter

                        }
                    }
                }}


        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MatchesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Data) =
            MatchesFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }
}