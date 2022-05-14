package me.benbarber.spacex.ui.home.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.benbarber.spacex.ui.theme.SpacexTheme

@Composable
fun LaunchDetails(
    modifier: Modifier = Modifier,
    nameOfMission: String,
    launchDate: String,
    wasSuccessful: Boolean,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(nameOfMission)
        Text("Launch Date: $launchDate")

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Mission Success: ")
            if(wasSuccessful) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Successful",
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Unsuccessful",
                )
            }
        }
    }
}

@Preview(
    name = "Successful Launch Details",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
)
@Preview(
    name = "Successful Launch Details",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,)
@Composable
fun SuccessfulLaunchDetailsPreview() {
    SpacexTheme {
        LaunchDetails(
            modifier = Modifier.fillMaxWidth(),
            nameOfMission = "Falcon 9 Test Flight",
            launchDate = "04-06-2010",
            wasSuccessful = true,
        )
    }
}

@Preview(
    name = "Unsuccessful Launch Details",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
)
@Preview(
    name = "Unsuccessful Launch Details",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
)
@Composable
fun UnsuccessfulLaunchDetailsPreview() {
    SpacexTheme {
        LaunchDetails(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
            nameOfMission = "Falcon 9 Test Flight",
            launchDate = "04-06-2010",
            wasSuccessful = false,
        )
    }
}