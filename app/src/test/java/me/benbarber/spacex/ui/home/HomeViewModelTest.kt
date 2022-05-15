package me.benbarber.spacex.ui.home

import app.cash.turbine.test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import me.benbarber.spacex.data.api.model.Launch
import me.benbarber.spacex.data.common.ApiState
import me.benbarber.spacex.data.repository.ILaunchesRepository
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class HomeViewModelTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI Thread")

    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    //region no events sent
    @Test(timeout = 1000L)
    fun `when a viewModel is created that the viewModels is loading`() = runTest {
        val repository = mock<ILaunchesRepository> {
            on { getLaunches() }.doReturn(emptyFlow())
        }

        val viewModel = HomeViewModel(repository)

        assert(viewModel.loading.first())
    }

    @Test(timeout = 1000L)
    fun `when a viewModel is created that the viewModels is not in failed state`() = runTest {
        val repository = mock<ILaunchesRepository> {
            on { getLaunches() }.doReturn(emptyFlow())
        }

        val viewModel = HomeViewModel(repository)

        assert(!viewModel.failed.first())
    }

    @Test(timeout = 1000L)
    fun `when a viewModel is created that the viewModels launches an empty list`() = runTest {
        val repository = mock<ILaunchesRepository> {
            on { getLaunches() }.doReturn(emptyFlow())
        }

        val viewModel = HomeViewModel(repository)

        assert(viewModel.launches.first().isEmpty())
    }
    //endregion

    //region ApiState.Success
    @Test(timeout = 1000L)
    fun `when a ApiState Success is emitted from getLaunches that the viewModels is loading`() = runTest {
        val repository = mock<ILaunchesRepository> {
            on { getLaunches() }.doReturn(flowOf(ApiState.Success(listOf(Launch(id = "1")))))
        }

        val viewModel = HomeViewModel(repository)

        viewModel.loading.test {
            // Not sure why, but !awaitItem() fails but this works
            val item = awaitItem()

            assert(item == false)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test(timeout = 1000L)
    fun `when a ApiState Success is emitted from getLaunches that the viewModels is not in failed state`() = runTest {
        val repository = mock<ILaunchesRepository> {
            on { getLaunches() }.doReturn(flowOf(ApiState.Success(listOf(Launch(id = "1")))))
        }

        val viewModel = HomeViewModel(repository)

        viewModel.failed.test {
            assert(!awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test(timeout = 1000L)
    fun `when a ApiState Success is emitted from getLaunches that the viewModels launches is the correct list`() = runTest {
        val repository = mock<ILaunchesRepository> {
            on { getLaunches() }.doReturn(flowOf(ApiState.Success(listOf(Launch(id = "1")))))
        }

        val viewModel = HomeViewModel(repository)

        viewModel.launches.test {
            val launches = awaitItem()
            assert(launches.count() == 1)
            assert(launches.first().id == "1")

            cancelAndIgnoreRemainingEvents()
        }
    }
    //endregion

    //region ApiState.Loading
    @Test(timeout = 1000L)
    fun `when a ApiState Loading is emitted from getLaunches that the viewModels is not loading`() = runTest {
        val repository = mock<ILaunchesRepository> {
            on { getLaunches() }.doReturn(flowOf(ApiState.Loading()))
        }

        val viewModel = HomeViewModel(repository)

        viewModel.loading.test {
            assert(awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test(timeout = 1000L)
    fun `when a ApiState Loading is emitted from getLaunches that the viewModels is not in failed state`() = runTest {
        val repository = mock<ILaunchesRepository> {
            on { getLaunches() }.doReturn(flowOf(ApiState.Loading()))
        }

        val viewModel = HomeViewModel(repository)

        viewModel.failed.test {
            assert(!awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test(timeout = 1000L)
    fun `when a ApiState Loading is emitted from getLaunches that the viewModels launches is the correct list`() = runTest {
        val repository = mock<ILaunchesRepository> {
            on { getLaunches() }.doReturn(flowOf(ApiState.Loading()))
        }

        val viewModel = HomeViewModel(repository)

        viewModel.launches.test {
            val launches = awaitItem()
            assert(launches.isEmpty())

            cancelAndIgnoreRemainingEvents()
        }
    }
    //endregion

    //region ApiState.Failure
    @Test(timeout = 1000L)
    fun `when a ApiState Failure is emitted from getLaunches that the viewModels is not loading`() = runTest {
        val repository = mock<ILaunchesRepository> {
            on { getLaunches() }.doReturn(flowOf(ApiState.Failure(Exception("Test"))))
        }

        val viewModel = HomeViewModel(repository)

        viewModel.loading.test {
            assert(awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test(timeout = 1000L)
    fun `when a ApiState Failure is emitted from getLaunches that the viewModels is in failed state`() = runTest {
        val repository = mock<ILaunchesRepository> {
            on { getLaunches() }.doReturn(flowOf(ApiState.Failure(Exception("Test"))))
        }

        val viewModel = HomeViewModel(repository)

        viewModel.failed.test {
            skipItems(1)
            assert(awaitItem())

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test(timeout = 1000L)
    fun `when a ApiState Failure is emitted from getLaunches that the viewModels launches is the correct list`() = runTest {
        val repository = mock<ILaunchesRepository> {
            on { getLaunches() }.doReturn(flowOf(ApiState.Failure(Exception("Test"))))
        }

        val viewModel = HomeViewModel(repository)

        viewModel.launches.test {
            val launches = awaitItem()
            assert(launches.isEmpty())

            cancelAndIgnoreRemainingEvents()
        }
    }
    //endregion
}