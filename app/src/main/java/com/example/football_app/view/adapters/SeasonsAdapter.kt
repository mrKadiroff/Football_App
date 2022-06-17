package com.example.football_app.view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.football_app.database.AppDatabase
import com.example.football_app.databinding.SeasonsLayoutBinding
import com.example.football_app.databinding.StandingsItemBinding
import com.example.football_app.network.RetrofitClient
import com.example.football_app.network.seasons.Season
import com.example.football_app.network.standingsclass.Data
import com.example.football_app.network.standingsclass.Standings
import com.example.football_app.utils.NetworkHelper
import com.example.football_app.utils.Status
import com.example.football_app.viewmodels.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class SeasonsAdapter(
    var list: List<Season>,
) : RecyclerView.Adapter<SeasonsAdapter.Vh>() {

    inner class Vh(var malumotItemBinding: SeasonsLayoutBinding) :
        RecyclerView.ViewHolder(malumotItemBinding.root) {

        fun onBind(malumotlar: Season, position: Int) {
            malumotItemBinding.clubnae.text = malumotlar.year.toString()
            malumotItemBinding.logos.text = malumotlar.displayName
            malumotItemBinding.p.text = "Start Date: ${malumotlar.startDate}"
            malumotItemBinding.d.text = "End Date: ${malumotlar.endDate}"

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(SeasonsLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size



}