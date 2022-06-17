package com.example.football_app.view.minitabfragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.football_app.R
import com.example.football_app.database.AppDatabase
import com.example.football_app.database.entity.TeamEntity
import com.example.football_app.databinding.FragmentAllBinding
import com.example.football_app.databinding.FragmentLeagueBinding
import com.example.football_app.network.RetrofitClient
import com.example.football_app.network.leagueclass.Data
import com.example.football_app.network.standingsclass.Standing
import com.example.football_app.repositories.UserRepository
import com.example.football_app.utils.NetworkHelper
import com.example.football_app.utils.Status
import com.example.football_app.view.adapters.DetailAdapter
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
 * Use the [AllFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AllFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Data? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as Data

        }
    }

    lateinit var binding: FragmentAllBinding
    lateinit var detailAdapter: DetailAdapter
    lateinit var userViewModel: UserViewModel
    private val TAG = "AllFragment"
    lateinit var appDatabase: AppDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAllBinding.inflate(layoutInflater, container, false)
        appDatabase = AppDatabase.getInstance(binding.root.context)
        val userRepository = UserRepository(RetrofitClient.apiService(), AppDatabase.getInstance(binding.root.context))
        val networkHelper = NetworkHelper(binding.root.context )
        userViewModel = ViewModelProvider(this, ViewModelFactory(userRepository,networkHelper))[UserViewModel::class.java]




        GlobalScope.launch(Dispatchers.Main) {
            userViewModel.getStandings(param1!!.id)
                .observe(viewLifecycleOwner) {


                    when (it.status) {
                        Status.LOADING -> {

                        }

                        Status.ERROR -> {
                            Log.d(TAG, "onCreateView: ${it.message}")
                        }

                        Status.SUCCESS -> {

                            Log.d(TAG, "onCreateView: ${it.data}")
                            detailAdapter = DetailAdapter(it.data!!,object :DetailAdapter.OnItemClickListener{
                                override fun onItemClick(malumotlar: Standing) {
                                    val teamEntity = TeamEntity()
                                    teamEntity.icon = malumotlar.team.logos[0].href
                                    teamEntity.teamname = malumotlar.team.name
                                    teamEntity.d = malumotlar.stats[2].value.toString()
                                    teamEntity.l = malumotlar.stats[1].value.toString()
                                    teamEntity.gd = malumotlar.stats[9].value.toString()
                                    teamEntity.pts = malumotlar.stats[6].value.toString()
                                    teamEntity.p = malumotlar.stats[4].value.toString()
                                    appDatabase.teamDao().insertWord(teamEntity)
                                }

                            })

                            binding.rv2.adapter = detailAdapter


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
         * @return A new instance of fragment AllFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Data) =
            AllFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }
}