package com.example.football_app.network.seasons

data class Type(
    val abbreviation: String,
    val endDate: String,
    val hasStandings: Boolean,
    val id: String,
    val name: String,
    val startDate: String
)