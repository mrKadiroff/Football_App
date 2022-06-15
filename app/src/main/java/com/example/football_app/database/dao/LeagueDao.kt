package com.example.football_app.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.football_app.network.leagueclass.Data

@Dao
interface LeagueDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLeagues(leagues: List<Data>)


    @Query("select * from users")
    suspend fun getAllLeagues(): List<Data>

}