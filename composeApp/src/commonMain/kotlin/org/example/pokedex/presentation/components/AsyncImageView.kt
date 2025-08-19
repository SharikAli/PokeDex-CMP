package org.example.pokedex.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest

@Composable
fun AsyncImageView(
    modifier: Modifier = Modifier,
    request: ImageRequest,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Fit,
    onLoading: @Composable (() -> Unit)? = null,
    onError: @Composable (() -> Unit)? = null,
    onSuccess: @Composable (() -> Unit)? = null,
) {

    val painter = rememberAsyncImagePainter(model = request)
    val state by painter.state.collectAsState()

    when (state) {
        AsyncImagePainter.State.Empty -> LoadingOverlay(modifier = Modifier.padding(20.dp))
        is AsyncImagePainter.State.Error -> {
            if (onError != null) {
                onError()
            } else {
                LoadingOverlay(modifier = Modifier.padding(20.dp))
            }
        }

        is AsyncImagePainter.State.Loading -> {
            if (onLoading != null) {
                onLoading()
            } else {
                LoadingOverlay(modifier = Modifier.padding(20.dp))
            }
        }

        is AsyncImagePainter.State.Success -> {
            if (onSuccess != null) {
                onSuccess()
            } else {
                Image(
                    painter = painter,
                    contentDescription = contentDescription,
                    contentScale = contentScale,
                    modifier = modifier
                )
            }
        }
    }
}