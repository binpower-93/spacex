package me.benbarber.spacex.ui.common

import android.content.res.Configuration
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import me.benbarber.spacex.ui.theme.SpacexTheme

@Composable
fun SpacexTopAppBar(
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text("Falcon 9 Launches")
        }
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun SpacexTopAppBarPreview() {
    SpacexTheme {
        SpacexTopAppBar()
    }
}
