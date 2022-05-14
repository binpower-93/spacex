package me.benbarber.spacex.ui.home.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.benbarber.spacex.ui.theme.SpacexTheme

@Composable
fun LaunchRow(
    modifier: Modifier = Modifier,
    nameOfMission: String,
    launchDate: String,
    wasSuccessful: Boolean,
    badgeUrl: String,
    badgeContentDescription: String?,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        LaunchBadge(
            modifier = Modifier.size(96.dp),
            imageUrl = badgeUrl,
            contentDescription = badgeContentDescription,
        )

        LaunchDetails(
            modifier = Modifier.weight(1f),
            nameOfMission = nameOfMission,
            launchDate = launchDate,
            wasSuccessful = wasSuccessful
        )
    }
}



@Preview(
    name = "Successful Launch Row",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
)
@Preview(
    name = "Successful Launch Row",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,)
@Composable
fun SuccessfulLaunchRowPreview() {
    SpacexTheme {
        LaunchRow(
            modifier = Modifier.fillMaxWidth(),
            nameOfMission = "Falcon 9 Test Flight",
            launchDate = "04-06-2010",
            wasSuccessful = true,
            badgeUrl = "https://images2.imgbox.com/ab/79/Wyc9K7fv_o.png",
            badgeContentDescription = null,
        )
    }
}

@Preview(
    name = "Unsuccessful Launch Row",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
)
@Preview(
    name = "Unsuccessful Launch Row",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
)
@Composable
fun UnsuccessfulLaunchRowPreview() {
    SpacexTheme {
        LaunchRow(
            modifier = Modifier.fillMaxWidth(),
            nameOfMission = "Falcon 9 Test Flight",
            launchDate = "04-06-2010",
            wasSuccessful = false,
            badgeUrl = "https://images2.imgbox.com/ab/79/Wyc9K7fv_o.png",
            badgeContentDescription = null,
        )
    }
}