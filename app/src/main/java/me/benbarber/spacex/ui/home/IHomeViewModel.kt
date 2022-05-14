package me.benbarber.spacex.ui.home

import kotlinx.coroutines.flow.Flow

interface IHomeViewModel {

    val launches: Flow<Any>
}