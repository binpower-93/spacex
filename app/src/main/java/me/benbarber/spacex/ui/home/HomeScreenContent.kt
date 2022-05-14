package me.benbarber.spacex.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.benbarber.spacex.ui.common.SpacexScaffold
import me.benbarber.spacex.ui.home.components.LaunchRow
import me.benbarber.spacex.ui.home.models.LaunchData
import me.benbarber.spacex.ui.theme.SpacexTheme

@Composable
fun HomeScreenContent(
    launches: List<LaunchData>,
    loading: Boolean,
    failed: Boolean,
) {
    SpacexScaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
        when {
            loading -> {
                Box(
                    modifier = Modifier.fillMaxSize().padding(paddingValues),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(128.dp)
                    )
                }
            }
            failed -> {
                Box(
                    modifier = Modifier.fillMaxSize().padding(paddingValues),
                    contentAlignment = Alignment.Center,
                ) {
                    Text("Failed to load content")
                }
            }
            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = paddingValues,
                ) {
                    items(items = launches, key = { it.id }) { launch ->
                        LaunchRow(
                            nameOfMission = launch.nameOfMission,
                            launchDate = launch.launchDate,
                            wasSuccessful = launch.wasSuccessful,
                            badgeUrl = launch.badgeUrl,
                            badgeContentDescription = null,
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    name = "Loaded 10 Launches",
    group = "HomeScreenContent",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    showSystemUi = true,
)
@Preview(
    name = "Loaded 10 Launches",
    group = "HomeScreenContent",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    showSystemUi = true,
)
@Composable
fun LoadedLaunchesHomeScreenPreview() {
    SpacexTheme {
        HomeScreenContent(
            launches = (0 until 10).map {
                LaunchData(
                    id = "$it",
                    nameOfMission = "Mission $it",
                    launchDate = "2009-07-13",
                    wasSuccessful = it % 2 == 0,
                    badgeUrl = "https://images2.imgbox.com/8d/fc/0qdZMWWx_o.png",
                    badgeContentDescription = "Mission Badge"
                )
            },
            loading = false,
            failed = false,
        )
    }
}

@Preview(
    name = "Loading Launches",
    group = "HomeScreenContent",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    showSystemUi = true,
)
@Preview(
    name = "Loading Launches",
    group = "HomeScreenContent",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    showSystemUi = true,
)
@Composable
fun LoadingLaunchesHomeScreenPreview() {
    SpacexTheme {
        HomeScreenContent(
            launches = emptyList(),
            loading = true,
            failed = false,
        )
    }
}

@Preview(
    name = "Failed to Load Launches",
    group = "HomeScreenContent",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    showSystemUi = true,
)
@Preview(
    name = "Failed to Load Launches",
    group = "HomeScreenContent",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    showSystemUi = true,
)
@Composable
fun FailureLaunchesHomeScreenPreview() {
    SpacexTheme {
        HomeScreenContent(
            launches = emptyList(),
            loading = false,
            failed = true,
        )
    }
}