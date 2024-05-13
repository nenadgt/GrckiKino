package com.grckikino.data.models.round

data class Round(
    val drawBreak: Int = 0,
    val drawId: Int = 0,
    val drawTime: Long = 0L,
    val gameId: Int = 0,
    val pricePoints: PricePoints? = null,
    val prizeCategories: List<PrizeCategory>? = null,
    val status: String = "",
    val visualDraw: Int = 0,
    val wagerStatistics: WagerStatistics? = null
)