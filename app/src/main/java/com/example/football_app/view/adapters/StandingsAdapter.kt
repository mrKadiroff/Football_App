package com.example.football_app.view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.football_app.databinding.StandingsItemBinding
import com.example.football_app.network.RetrofitClient
import com.example.football_app.network.standingsclass.Data
import com.example.football_app.network.standingsclass.Standings
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class StandingsAdapter (var list: List<com.example.football_app.network.leagueclass.Data>) : RecyclerView.Adapter<StandingsAdapter.Vh>() {

    inner class Vh(var malumotItemBinding: StandingsItemBinding) :
        RecyclerView.ViewHolder(malumotItemBinding.root) {

        fun onBind(malumotlar: com.example.football_app.network.leagueclass.Data, position: Int) {
           malumotItemBinding.leagueName.text = malumotlar.name
            malumotItemBinding.country.text = malumotlar.abbr
            Glide.with(malumotItemBinding.root.context).load(malumotlar.logos.dark).into(malumotItemBinding.flag);



            RetrofitClient.apiService().getStandings(malumotlar.id).enqueue(object:
                Callback<Standings> {
                override fun onResponse(call: Call<Standings>, response: Response<Standings>) {
                    if (response.isSuccessful){
                        val body = response.body()
                        malumotItemBinding.club1.text = body!!.data.standings[0].team.name
                        malumotItemBinding.draw1.text = body!!.data.standings[0].stats[2].value.toString()
                        malumotItemBinding.lose1.text = body!!.data.standings[0].stats[1].value.toString()

                        try {
                            Glide.with(malumotItemBinding.root.context).load(body.data.standings[0].team.logos[0].href).into(malumotItemBinding.logo1)
                        }catch (e:Exception){
                            Toast.makeText(malumotItemBinding.root.context, e.message, Toast.LENGTH_SHORT)
                                .show()
                        }



                        malumotItemBinding.club2.text = body!!.data.standings[1].team.name
                        malumotItemBinding.draw2.text = body!!.data.standings[1].stats[2].value.toString()
                        malumotItemBinding.lose2.text = body!!.data.standings[1].stats[1].value.toString()

                        try {
                            Glide.with(malumotItemBinding.root.context).load(body.data.standings[1].team.logos[0].href).into(malumotItemBinding.logo2)
                        }catch (e:Exception){
                            Toast.makeText(malumotItemBinding.root.context, e.message, Toast.LENGTH_SHORT)
                                .show()
                        }

                        //sdsdsdsdsd

                    }
                }

                override fun onFailure(call: Call<Standings>, t: Throwable) {

                }

            })




        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(StandingsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size


}