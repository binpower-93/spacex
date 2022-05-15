package me.benbarber.spacex.data.api

import me.benbarber.spacex.data.api.model.Launch
import retrofit2.http.GET

interface SpacexApiService {

    @GET("/v4/launches")
    suspend fun fetchLaunches(): List<Launch>
}