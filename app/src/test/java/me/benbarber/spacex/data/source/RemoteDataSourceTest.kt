package me.benbarber.spacex.data.source

import app.cash.turbine.test
import kotlinx.coroutines.runBlocking
import me.benbarber.spacex.data.api.SpacexApiService
import me.benbarber.spacex.data.api.model.Launch
import me.benbarber.spacex.data.common.ApiState
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock

class RemoteDataSourceTest {

    @Test
    fun `when calling spacex api fetchLaunches returns a list that loading then success with the launches is emitted`() = runBlocking {

        val spacexApiService = mock<SpacexApiService> {
            onBlocking { fetchLaunches() }.doReturn(
                listOf(
                    Launch(id = "1"),
                    Launch(id = "2"),
                )
            )
        }

        val remoteDataSource = RemoteDataSource(spacexApiService)

        remoteDataSource.fetchLaunches().test {
            val firstItem = awaitItem()
            assert(firstItem is ApiState.Loading)

            val secondItem = awaitItem()
            assert(secondItem is ApiState.Success)
            assert((secondItem as ApiState.Success).data.first().id == "1")

            awaitComplete()
        }
    }

    @Test
    fun `when calling spacex api fetchLaunches returns a list that loading then Failure is emitted`() = runBlocking {

        val spacexApiService = mock<SpacexApiService> {
            onBlocking { fetchLaunches() }.doThrow(IllegalStateException("Test"))
        }

        val remoteDataSource = RemoteDataSource(spacexApiService)

        remoteDataSource.fetchLaunches().test {
            val firstItem = awaitItem()
            assert(firstItem is ApiState.Loading)

            val secondItem = awaitItem()
            assert(secondItem is ApiState.Failure)

            val failureItem = secondItem as ApiState.Failure
            assert(failureItem.error is IllegalStateException)
            assert(failureItem.error.message == "Test")

            awaitComplete()
        }
    }
}