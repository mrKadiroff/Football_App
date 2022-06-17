package com.example.football_app.network.standingsclass




data class Standing(
    val note: Note,
    val stats: List<Stat>,
    val team: Team
)