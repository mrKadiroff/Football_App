package com.example.football_app.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class TeamEntity {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    @ColumnInfo(name = "icon")
    var icon: String? = null

    @ColumnInfo(name = "name")
    var teamname: String? = null

    @ColumnInfo(name = "p")
    var p: String? = null

    @ColumnInfo(name = "d")
    var d: String? = null

    @ColumnInfo(name = "l")
    var l: String? = null

    @ColumnInfo(name = "gd")
    var gd: String? = null

    @ColumnInfo(name = "pts")
    var pts: String? = null

}