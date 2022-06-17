package com.example.football_app.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.football_app.databinding.StandingsItemBinding
import com.example.football_app.network.RetrofitClient
import com.example.football_app.network.leagueclass.Data
import com.example.football_app.network.standingsclass.Standings
import com.example.football_app.viewmodels.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserAdapter(var userViewModel: UserViewModel,var lifecycleOwner: LifecycleOwner) : ListAdapter<Data, UserAdapter.Vh>(MyDiffUtill()) {

    inner class Vh(var itemUserBinding: StandingsItemBinding) :
        RecyclerView.ViewHolder(itemUserBinding.root) {

        fun onBind(user: Data) {
            itemUserBinding.apply {
                itemUserBinding.leagueName.text = user.name
                itemUserBinding.country.text = user.id
                Glide.with( itemUserBinding.root.context).load(user.logos.dark).into( itemUserBinding.flag);

                val input: String = itemUserBinding.country.getText().toString()

                            RetrofitClient.apiService().getStandings2(input).enqueue(object:
                                Callback<Standings> {
                override fun onResponse(call: Call<Standings>, response: Response<Standings>) {
                    if (response.isSuccessful){
                        val body = response.body()
                        itemUserBinding.club1.text = body!!.data.standings[0].team.name

                        //sdsdsdsdsd

                    }
                }

                override fun onFailure(call: Call<Standings>, t: Throwable) {

                }

            })




            }
        }
    }

    class MyDiffUtill : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(StandingsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(getItem(position))
    }
}