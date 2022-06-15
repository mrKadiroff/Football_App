package com.example.football_app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.football_app.database.dao.LeagueDao
import com.example.football_app.network.leagueclass.Data

@Database(entities = [Data::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun leagueDao(): LeagueDao


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