package com.estebanposada.sakeapp.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.estebanposada.sakeapp.R
import com.estebanposada.sakeapp.domain.model.sakes

@Composable
fun HomeScreen(
    state: MainState,
    modifier: Modifier = Modifier,
    onItemClick: (Int) -> Unit
) {
    val loadingDescription = stringResource(R.string.loading)

    when (state) {
        is MainState.Loading ->
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(modifier = Modifier.size(100.dp))
            }

        is MainState.LoadedSakes ->
            LazyColumn(modifier = modifier) {
                items(sakes) { item ->
                    Card(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                item.id?.let { onItemClick(it) }
                            }, elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(modifier = modifier.padding(16.dp)) {
                            Text(text = item.name, style = MaterialTheme.typography.titleMedium)
                            Text(
                                text = item.description,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }

        is MainState.Error -> Text(
            modifier = modifier.padding(16.dp),
            text = stringResource(R.string.no_items_found)
        )

        MainState.Idle -> TODO()
    }
}

@Preview
@Composable
private fun HomePreview() {
    HomeScreen(
        state = MainState.Loading,
        onItemClick = {}
    )
}

@Preview
@Composable
private fun HomePreviewEmpty() {
    HomeScreen(
        state = MainState.LoadedSakes(sakes),
        onItemClick = {}
    )
}

@Preview
@Composable
private fun HomePreviewError() {
    HomeScreen(
        state = MainState.Error("Error"),
        onItemClick = {}
    )
}