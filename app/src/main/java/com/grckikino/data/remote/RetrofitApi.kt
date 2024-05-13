package com.grckikino.data.remote

import com.grckikino.data.models.round.Round
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitApi {

    @GET("{gameId}/{drawId}")
    suspend fun getRoundById(@Path("gameId") gameId: Int, @Path("drawId") drawId: Int): Response<Round>

    @GET("{gameId}/upcoming/20")
    suspend fun getRounds(@Path("gameId") gameId: Int): Response<ArrayList<Round>>
}