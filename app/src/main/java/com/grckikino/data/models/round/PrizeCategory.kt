package com.grckikino.data.models.round

data class PrizeCategory(
    val categoryType: Int,
    val distributed: Double,
    val divident: Double,
    val fixed: Double,
    val gameType: String,
    val id: Int,
    val jackpot: Double,
    val winners: Int
)