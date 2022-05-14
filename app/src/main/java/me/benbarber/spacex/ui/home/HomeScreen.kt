package me.benbarber.spacex.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun HomeScreen(viewModel: IHomeViewModel) {
    val launches by viewModel.launches.collectAsState(initial = emptyList())
    val loading by viewModel.loading.collectAsState(initial = true)
    val failed by viewModel.failed.collectAsState(initial = false)

    HomeScreenContent(
        launches = launches,
        loading = loading,
        failed = failed,
    )
}