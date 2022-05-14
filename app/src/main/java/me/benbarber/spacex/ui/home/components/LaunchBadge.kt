package me.benbarber.spacex.ui.home.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import me.benbarber.spacex.ui.theme.SpacexTheme

@Composable
fun LaunchBadge(
    modifier: Modifier = Modifier,
    imageUrl: String,
    contentDescription: String?,
) {
    SubcomposeAsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription,
        loading = {
            CircularProgressIndicator()
        }
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun LaunchBadgePreview() {
    SpacexTheme {
        LaunchBadge(
            modifier = Modifier.size(128.dp),
            imageUrl = "https://images2.imgbox.com/ab/79/Wyc9K7fv_o.png",
            contentDescription = null,
        )
    }
}
