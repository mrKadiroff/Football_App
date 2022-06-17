package com.example.football_app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.football_app.database.dao.LeagueDao
import com.example.football_app.database.dao.StandingsDao
import com.example.football_app.database.dao.TeamDao
import com.example.football_app.database.entity.TeamEntity
import com.example.football_app.network.leagueclass.Data
import com.example.football_app.network.standingsclass.Standing

@Database(entities = [Data::class,TeamEntity::class], version = 3)
abstract class AppDatabase : RoomDatabase() {

    abstract fun leagueDao(): LeagueDao
    abstract fun teamDao(): TeamDao



    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (instance == null){
                instance = Room.databaseBuilder(context,AppDatabase::class.java,"pdp.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}