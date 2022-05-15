package me.benbarber.spacex.data.repository

import me.benbarber.spacex.data.source.RemoteDataSource
import javax.inject.Inject

class LaunchesRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
): ILaunchesRepository {

    override fun getLaunches() = remoteDataSource.fetchLaunches()
}

