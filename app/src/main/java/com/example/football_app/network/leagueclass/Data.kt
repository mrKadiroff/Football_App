package com.example.football_app.network.leagueclass

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.io.Serializable

@Entity(tableName = "users")
data class Data(
    @PrimaryKey
    val abbr: String,
    val id: String,
    @Embedded
    val logos: Logos,
    val name: String,
    val slug: String


):Serializable