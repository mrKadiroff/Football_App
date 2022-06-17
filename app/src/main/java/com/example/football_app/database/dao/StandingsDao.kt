package com.example.football_app.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.football_app.network.leagueclass.Data
import com.example.football_app.network.standingsclass.Standing
import com.example.football_app.network.standingsclass.Standings

@Dao
interface StandingsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addStandings(stangings: List<Standing>)


    @Query("select * from users")
    suspend fun getAllLeagues(): List<Data>

}