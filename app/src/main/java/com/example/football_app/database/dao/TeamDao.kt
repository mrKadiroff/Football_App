package com.example.football_app.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.football_app.database.entity.TeamEntity

@Dao
interface TeamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWord(teamEntity: TeamEntity)

    @Query("select * from teamentity")
    fun getAllTeams(): List<TeamEntity>

}