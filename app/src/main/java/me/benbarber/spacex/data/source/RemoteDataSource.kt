package me.benbarber.spacex.data.source

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import me.benbarber.spacex.data.api.SpacexApiService
import me.benbarber.spacex.data.api.model.Launch
import me.benbarber.spacex.data.common.ApiState
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api: SpacexApiService,
): IRemoteDataSource {

    override fun fetchLaunches(): Flow<ApiState<List<Launch>>> {
        return flow {
            emit(ApiState.Success(api.fetchLaunches()) as ApiState<List<Launch>>)
        }
            .onStart {
                emit(ApiState.Loading())
            }
            .catch {
                emit(ApiState.Failure(error = it))
            }
    }
}