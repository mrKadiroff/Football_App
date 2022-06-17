package com.example.football_app.view.minitabfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.football_app.R
import com.example.football_app.database.AppDatabase
import com.example.football_app.databinding.FragmentAllBinding
import com.example.football_app.databinding.FragmentHomeBinding
import java.lang.Exception

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
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
    lateinit var binding: FragmentHomeBinding
    lateinit var appDatabase: AppDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        appDatabase = AppDatabase.getInstance(binding.root.context)





        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val allTeams = appDatabase.teamDao().getAllTeams()

        allTeams.forEach {
            try {
                Glide.with(binding.root.context).load(it.icon).into(binding.logos)
            }catch (e: Exception){
                Toast.makeText(binding.root.context, e.message, Toast.LENGTH_SHORT)
                    .show()
            }

            binding.clubnae.text = it.teamname
            binding.d.text = "D: ${it.d}"
            binding.gd.text = "GD: ${it.gd}"
            binding.l.text = "L: ${it.l}"
            binding.p.text = "P: ${it.p}"
            binding.pts.text = "PTS: ${it.pts}"
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}