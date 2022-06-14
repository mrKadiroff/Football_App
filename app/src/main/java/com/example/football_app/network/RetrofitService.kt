package com.example.football_app.network

import com.example.football_app.network.leagueclass.LeagueResult
import com.example.football_app.network.standingsclass.Standings
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @GET("leagues/{word}/standings")
    fun getStandings(
        @Path("word") word:String,
        @Query("season") season:String = "2021",
        @Query("sort") sort:String = "asc"
    ): Call<Standings>


    @GET("leagues")
    fun getLeagues(
    ): Call<LeagueResult>


}