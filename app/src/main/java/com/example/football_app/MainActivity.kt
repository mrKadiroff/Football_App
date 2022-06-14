package com.example.football_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.football_app.network.RetrofitClient
import com.example.football_app.network.standingsclass.Standings
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        RetrofitClient.apiService().getStandings().enqueue(object:
//        Callback<Standings> {
//            override fun onResponse(call: Call<Standings>, response: Response<Standings>) {
//                if (response.isSuccessful){
//                    val body = response.body()
//                    Log.d(TAG, "onResponse: ${body!!.data.name}")
//                }
//            }
//
//            override fun onFailure(call: Call<Standings>, t: Throwable) {
//                Log.d(TAG, "onFailure: ${t.message}")
//            }
//
//        })

    }
}