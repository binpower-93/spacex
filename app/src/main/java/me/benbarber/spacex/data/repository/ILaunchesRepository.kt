package me.benbarber.spacex.data.repository

import kotlinx.coroutines.flow.Flow
import me.benbarber.spacex.data.api.model.Launch
import me.benbarber.spacex.data.common.ApiState

interface ILaunchesRepository {

    fun getLaunches(): Flow<ApiState<List<Launch>>>
}