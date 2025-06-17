package com.estebanposada.sakeapp.ui.detail

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.estebanposada.sakeapp.R
import com.estebanposada.sakeapp.ui.Constants
import com.estebanposada.sakeapp.ui.detail.components.RatingStars

@Composable
fun DetailScreen(
    state: DetailState,
    modifier: Modifier = Modifier,
    onEvent: (DetailEvent) -> Unit = { }
) {

    val snackBarHostState = remember { SnackbarHostState() }
    val errorMessage = when (state.error) {
        is DetailError.Custom -> stringResource(id = state.error.messageResId)
        is DetailError.CustomMessage -> state.error.message
        DetailError.AddressUnavailable -> stringResource(R.string.coordinates_or_address_not_available)
        DetailError.WebsiteUnavailable -> stringResource(R.string.website_is_not_available)
        else -> Constants.EMPTY
    }
    val loadingDescription = stringResource(R.string.loading)

    LaunchedEffect(state.error) {
        if (state.error != null && errorMessage.isNotBlank()) snackBarHostState.showSnackbar(message = errorMessage)
    }
    when {
        state.isLoading ->
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(modifier = modifier.semantics {
                    contentDescription = loadingDescription
                })
            }

        else -> Box(modifier = modifier.fillMaxSize()) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                if (!state.sake?.imageUrl.isNullOrBlank()) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = state.sake?.imageUrl,
                            onError = { error ->
                                onEvent(
                                    DetailEvent.ShowError((DetailError.Custom(R.string.error_loading_image)))
                                )
                                Log.e(
                                    "Coil",
                                    "Error loading image: ${error.result.throwable.message}"
                                )
                            }),
                        contentDescription = stringResource(R.string.sake_image_description),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.Gray, CircleShape)
                            .align(Alignment.CenterHorizontally)
                    )
                }
                Spacer(modifier = modifier.height(16.dp))
                Text(
                    text = state.sake?.name ?: Constants.EMPTY,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = modifier.height(8.dp))
                Text(
                    text = state.sake?.description ?: Constants.EMPTY,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = modifier.height(16.dp))
                RatingStars(rating = state.sake?.rating ?: 0f)
                Spacer(modifier = modifier.height(16.dp))
                Text(
                    text = state.sake?.address ?: Constants.EMPTY,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Blue,
                    modifier = modifier
                        .clickable {
                            val coordinates = state.sake?.coordinates
                            val address = state.sake?.address
                            if (coordinates != null && !address.isNullOrBlank()) {
                                onEvent(
                                    DetailEvent.OpenMap(
                                        coordinates.first,
                                        coordinates.second,
                                        address
                                    )
                                )
                            } else {
                                onEvent(DetailEvent.ShowError(DetailError.AddressUnavailable))
                            }
                        }
                        .padding(16.dp)
                )
                Spacer(modifier = modifier.height(16.dp))
                Button(
                    onClick = {
                        state.sake?.website?.let {
                            onEvent(DetailEvent.OpenWebsite(it))
                        } ?: run {
                            onEvent(DetailEvent.ShowError(DetailError.WebsiteUnavailable))
                        }
                    },
                    modifier = modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(R.string.visit_website))
                }
            }
            SnackbarHost(
                hostState = snackBarHostState,
                modifier = modifier.align(Alignment.BottomStart)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailPreview() {
    DetailScreen(
        state = DetailState(
            SakeUiModel(
                name = "Sake name",
                description = "This is a description for sake",
                id = 1,
                imageUrl = "picture",
                rating = 3.5f,
                address = "123 Sake St, Tokyo, Japan",
                coordinates = Pair(1.0, 1.0),
                website = "website",
            ),
        )
    )
}