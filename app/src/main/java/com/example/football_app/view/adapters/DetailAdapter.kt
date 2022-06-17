package com.example.football_app.view.adapters

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.football_app.R
import com.example.football_app.database.AppDatabase
import com.example.football_app.databinding.StandingsItem2Binding
import com.example.football_app.databinding.StandingsItemBinding
import com.example.football_app.network.RetrofitClient
import com.example.football_app.network.standingsclass.Data
import com.example.football_app.network.standingsclass.Standing
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

class DetailAdapter(
    var list: List<Standing>,
    var onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<DetailAdapter.Vh>() {
    private var selectedItems = IntArray(list.size)


    inner class Vh(var malumotItemBinding: StandingsItem2Binding) :
        RecyclerView.ViewHolder(malumotItemBinding.root) {

        fun onBind(malumotlar: Standing, position: Int) {
            malumotItemBinding.club1.text = malumotlar.team.name
            malumotItemBinding.draw1.text =  malumotlar.stats[2].value.toString()
            malumotItemBinding.lose1.text = malumotlar.stats[1].value.toString()
            malumotItemBinding.ga1.text = malumotlar.stats[5].value.toString()
            malumotItemBinding.gd1.text = malumotlar.stats[9].value.toString()
            malumotItemBinding.pts1.text = malumotlar.stats[6].value.toString()


            try {
                Glide.with(malumotItemBinding.root.context).load(malumotlar.team.logos[0].href).into(malumotItemBinding.logo1)
            }catch (e:Exception){
                Toast.makeText(malumotItemBinding.root.context, e.message, Toast.LENGTH_SHORT)
                    .show()
            }

//            malumotItemBinding.root.setOnClickListener {
//                onItemClickListener.onItemClick(malumotlar)
//            }

            if(selectedItems[position] == 1) malumotItemBinding.gangster.setBackgroundResource(R.drawable.item_shape);
            else malumotItemBinding.gangster.setBackgroundResource(R.drawable.item_shape2);


            malumotItemBinding.root.setOnClickListener {

                if (position != RecyclerView.NO_POSITION) {
                    setSelectedItem(position);
                    notifyDataSetChanged();
                }

                onItemClickListener.onItemClick(malumotlar)
            }





        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(StandingsItem2Binding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickListener{
        fun onItemClick(malumotlar: Standing)
    }

    private fun initializeSeledtedItems() {
        for (item in selectedItems) item == 1
    }
    private fun setSelectedItem(position: Int) {
        for (i in 0 until selectedItems.size) {
            if (i == position) selectedItems[i] = 1 else selectedItems[i] = 0
        }
    }


}