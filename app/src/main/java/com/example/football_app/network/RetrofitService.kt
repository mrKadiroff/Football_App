package com.example.football_app.network

import com.example.football_app.network.leagueclass.LeagueResult
import com.example.football_app.network.seasons.SeasonsResult
import com.example.football_app.network.standingsclass.Standings
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @GET("leagues/{word}/standings")
    suspend fun getStandings(
        @Path("word") word:String,
        @Query("season") season:String = "2021",
        @Query("sort") sort:String = "asc"
    ): Standings

    @GET("leagues/{word}/seasons")
    suspend fun getSeasons(
        @Path("word") word:String
    ): SeasonsResult



    @GET("leagues/{word}/standings")
    fun getStandings2(
        @Path("word") word:String,
        @Query("season") season:String = "2021",
        @Query("sort") sort:String = "asc"
    ): Call<Standings>


    @GET("leagues")
    suspend fun getLeagues(
    ): LeagueResult


}