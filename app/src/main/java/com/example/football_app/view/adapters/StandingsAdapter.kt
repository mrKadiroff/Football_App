package com.example.football_app.view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.football_app.database.AppDatabase
import com.example.football_app.databinding.StandingsItemBinding
import com.example.football_app.network.RetrofitClient
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

class StandingsAdapter(
    var list: List<com.example.football_app.network.leagueclass.Data>,
    val networkHelper: NetworkHelper,
    val appDatabase: AppDatabase,
    var onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<StandingsAdapter.Vh>() {

    inner class Vh(var malumotItemBinding: StandingsItemBinding) :
        RecyclerView.ViewHolder(malumotItemBinding.root) {

        fun onBind(malumotlar: com.example.football_app.network.leagueclass.Data, position: Int) {
           malumotItemBinding.leagueName.text = malumotlar.name
            malumotItemBinding.country.text = malumotlar.abbr
            Glide.with(malumotItemBinding.root.context).load(malumotlar.logos.dark).into(malumotItemBinding.flag);



            malumotItemBinding.leagueName.setOnClickListener {
                onItemClickListener.onItemClick(malumotlar)
            }
            malumotItemBinding.goo.setOnClickListener {
                onItemClickListener.onItemClick(malumotlar)
            }





            if (networkHelper.isNetworkConnected()){
                RetrofitClient.apiService().getStandings2(malumotlar.id).enqueue(object:
                    Callback<Standings> {
                    override fun onResponse(call: Call<Standings>, response: Response<Standings>) {
                        if (response.isSuccessful){
                            val body = response.body()
//                            appDatabase.standingsDao().addStandings(body!!.data.standings)
                            malumotItemBinding.club1.text = body!!.data.standings[0].team.name
                            malumotItemBinding.draw1.text = body!!.data.standings[0].stats[2].value.toString()
                            malumotItemBinding.lose1.text = body!!.data.standings[0].stats[1].value.toString()
                            malumotItemBinding.ga1.text = body!!.data.standings[0].stats[5].value.toString()
                            malumotItemBinding.gd1.text = body!!.data.standings[0].stats[9].value.toString()
                            malumotItemBinding.pts1.text = body!!.data.standings[0].stats[6].value.toString()

                            try {
                                Glide.with(malumotItemBinding.root.context).load(body.data.standings[0].team.logos[0].href).into(malumotItemBinding.logo1)
                            }catch (e:Exception){
                                Toast.makeText(malumotItemBinding.root.context, e.message, Toast.LENGTH_SHORT)
                                    .show()
                            }



                            malumotItemBinding.club2.text = body!!.data.standings[1].team.name
                            malumotItemBinding.draw2.text = body!!.data.standings[1].stats[2].value.toString()
                            malumotItemBinding.lose2.text = body!!.data.standings[1].stats[1].value.toString()
                            malumotItemBinding.ga2.text = body!!.data.standings[1].stats[5].value.toString()
                            malumotItemBinding.gd2.text = body!!.data.standings[1].stats[9].value.toString()
                            malumotItemBinding.pts2.text = body!!.data.standings[1].stats[6].value.toString()


                            try {
                                Glide.with(malumotItemBinding.root.context).load(body.data.standings[1].team.logos[0].href).into(malumotItemBinding.logo2)
                            }catch (e:Exception){
                                Toast.makeText(malumotItemBinding.root.context, e.message, Toast.LENGTH_SHORT)
                                    .show()
                            }



                            malumotItemBinding.club3.text = body!!.data.standings[2].team.name
                            malumotItemBinding.draw3.text = body!!.data.standings[2].stats[2].value.toString()
                            malumotItemBinding.lose3.text = body!!.data.standings[2].stats[1].value.toString()
                            malumotItemBinding.ga3.text = body!!.data.standings[2].stats[5].value.toString()
                            malumotItemBinding.gd3.text = body!!.data.standings[2].stats[9].value.toString()
                            malumotItemBinding.pts3.text = body!!.data.standings[2].stats[6].value.toString()


                            try {
                                Glide.with(malumotItemBinding.root.context).load(body.data.standings[2].team.logos[0].href).into(malumotItemBinding.logo3)
                            }catch (e:Exception){
                                Toast.makeText(malumotItemBinding.root.context, e.message, Toast.LENGTH_SHORT)
                                    .show()
                            }



                            malumotItemBinding.club4.text = body!!.data.standings[3].team.name
                            malumotItemBinding.draw4.text = body!!.data.standings[3].stats[2].value.toString()
                            malumotItemBinding.lose4.text = body!!.data.standings[3].stats[1].value.toString()
                            malumotItemBinding.ga4.text = body!!.data.standings[3].stats[5].value.toString()
                            malumotItemBinding.gd4.text = body!!.data.standings[3].stats[9].value.toString()
                            malumotItemBinding.pts4.text = body!!.data.standings[3].stats[6].value.toString()


                            try {
                                Glide.with(malumotItemBinding.root.context).load(body.data.standings[3].team.logos[0].href).into(malumotItemBinding.logo4)
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
            }else{
                Toast.makeText(
                    malumotItemBinding.root.context,
                    "Internet is off",
                    Toast.LENGTH_SHORT
                ).show()
            }













        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(StandingsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickListener{
        fun onItemClick(malumotlar: com.example.football_app.network.leagueclass.Data)
    }

}