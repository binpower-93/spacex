package me.benbarber.spacex.data.source

import kotlinx.coroutines.flow.Flow
import me.benbarber.spacex.data.api.model.Launch
import me.benbarber.spacex.data.common.ApiState

interface IRemoteDataSource {

    fun fetchLaunches(): Flow<ApiState<List<Launch>>>
}