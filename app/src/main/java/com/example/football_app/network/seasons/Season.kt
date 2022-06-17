package com.example.football_app.network.seasons

data class Season(
    val displayName: String,
    val endDate: String,
    val startDate: String,
    val types: List<Type>,
    val year: Int
)