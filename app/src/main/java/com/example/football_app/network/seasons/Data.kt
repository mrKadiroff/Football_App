package com.example.football_app.network.seasons

data class Data(
    val abbreviation: String,
    val desc: String,
    val name: String,
    val seasons: List<Season>
)