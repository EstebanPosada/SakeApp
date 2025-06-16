package com.estebanposada.sakeapp.ui.detail

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.rememberAsyncImagePainter
import com.estebanposada.sakeapp.domain.model.Sake
import com.estebanposada.sakeapp.ui.Constants
import com.estebanposada.sakeapp.ui.detail.components.RatingStars

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    state: DetailState
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = state.sake?.picture,
                onError = { error ->
                    Log.e("Coil", "Error loading image: ${error.result.throwable.message}")
                }),
            contentDescription = "Sake image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape)
                .align(Alignment.CenterHorizontally)
        )
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
                .clickable { /* Handle address click */ }
                .padding(16.dp)
        )
        Spacer(modifier = modifier.height(16.dp))
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, state.sake?.website?.toUri())
                context.startActivity(intent)
            },
            modifier = modifier.fillMaxWidth()
        ) {
            Text(text = "Visit Website")
        }
    }
}

@Preview
@Composable
private fun DetailPreview() {
    DetailScreen(
        state = DetailState(
            Sake(
                name = "Sake name", description = "This is a description for sake",
                id = 1,
                picture = "picture",
                rating = 3.5f,
                address = "123 Sake St, Tokyo, Japan",
                coordinates = listOf(),
                google_maps_link = "maps link",
                website = "website",
            )
        )
    )
}