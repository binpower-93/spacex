@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package me.benbarber.spacex.ui.common

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import me.benbarber.spacex.ui.theme.SpacexTheme

@Composable
fun SpacexScaffold(
    modifier: Modifier = Modifier,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            SpacexTopAppBar()
        },
        content = content,
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun SpacexScaffoldPreview() {
    SpacexTheme {
        SpacexScaffold(modifier = Modifier.fillMaxSize()) {
            Text("Content")
        }
    }
}
