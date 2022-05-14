package me.benbarber.spacex.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun HomeScreen(viewModel: IHomeViewModel) {
    val launches by viewModel.launches.collectAsState(initial = Unit)
}