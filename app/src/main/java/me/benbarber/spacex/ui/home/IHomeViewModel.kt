package me.benbarber.spacex.ui.home

import kotlinx.coroutines.flow.Flow
import me.benbarber.spacex.ui.home.models.LaunchData

interface IHomeViewModel {

    val launches: Flow<List<LaunchData>>
    val loading: Flow<Boolean>
    val failed: Flow<Boolean>
}