package com.example.football_app.repositories

import com.example.football_app.database.AppDatabase
import com.example.football_app.network.RetrofitService
import com.example.football_app.network.leagueclass.Data

class UserRepository(private val apiService: RetrofitService,
                     private val appDatabase: AppDatabase
                    ) {

    suspend fun getRemoteUsers() = apiService.getLeagues()

    suspend fun getLocalUsers() = appDatabase.leagueDao().getAllLeagues()

    suspend fun addUsers(list: List<Data>) = appDatabase.leagueDao().addLeagues(list)


}