package me.benbarber.spacex.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import me.benbarber.spacex.data.api.model.Launch
import me.benbarber.spacex.data.common.ApiState
import me.benbarber.spacex.data.repository.ILaunchesRepository
import me.benbarber.spacex.ui.home.models.LaunchData
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: ILaunchesRepository,
): ViewModel(), IHomeViewModel {

    init {
        repository.getLaunches()
            .onEach {
                when (it) {
                    is ApiState.Success -> {
                        update(it.data, loading = false, error = false)
                    }
                    is ApiState.Loading -> {
                        update(it.data.orEmpty(), loading = true, error = false)
                    }
                    is ApiState.Failure -> {
                        Log.e("HomeViewModel", "launchesFlow hit the failure with", it.error)
                        update(it.data.orEmpty(), loading = false, error = true)
                    }
                }
            }
            .catch {
                Log.e("HomeViewModel", "launchesFlow hit the catch with", it)
                _failed.value = true
            }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }

    private val _launches = MutableStateFlow<List<LaunchData>>(emptyList())
    override val launches: Flow<List<LaunchData>> = _launches

    private val _loading = MutableStateFlow(true)
    override val loading: Flow<Boolean> = _loading

    private val _failed = MutableStateFlow(false)
    override val failed: Flow<Boolean> = _failed

    private fun update(
        launches: List<Launch>,
        loading: Boolean,
        error: Boolean,
    ) {
        _launches.value = launches.filter { it.upcoming != true }.map {
            LaunchData(
                id = it.id.orEmpty(),
                nameOfMission = it.name.orEmpty(),
                wasSuccessful = it.success == true,
                launchDate = it.dateLocal.orEmpty().split('T').firstOrNull().orEmpty(),
                badgeContentDescription = "Mission Badge",
                badgeUrl = it.links?.patch?.large ?: it.links?.patch?.small.orEmpty()
            )
        }

        _loading.value = loading
        _failed.value = error
    }
}