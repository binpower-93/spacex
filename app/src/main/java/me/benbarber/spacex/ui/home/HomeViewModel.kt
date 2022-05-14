package me.benbarber.spacex.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import me.benbarber.spacex.ui.home.models.LaunchData

class HomeViewModel(): ViewModel(), IHomeViewModel {

    private val _launches = MutableStateFlow((0 until 10).map {
        LaunchData(
            id = "$it",
            nameOfMission = "Mission $it",
            launchDate = "2009-07-13",
            wasSuccessful = it % 2 == 0,
            badgeUrl = "https://images2.imgbox.com/8d/fc/0qdZMWWx_o.png",
            badgeContentDescription = "Mission Badge"
        )
    })
    override val launches: Flow<List<LaunchData>> = _launches

    private val _loading = MutableStateFlow(false)
    override val loading: Flow<Boolean> = _loading

    private val _failed = MutableStateFlow(false)
    override val failed: Flow<Boolean> = _failed
}