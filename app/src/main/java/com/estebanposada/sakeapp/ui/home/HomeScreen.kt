package com.estebanposada.sakeapp.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.estebanposada.sakeapp.R
import com.estebanposada.sakeapp.domain.model.sakes

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeState = HomeState(),
    onItemClick: (Int) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(state.items) { item ->
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable {
                        item.id?.let { onItemClick(it) }
                    },
                text = item.name,
            )
            HorizontalDivider()
        }
    }
    if (state.items.isEmpty()) {
        Text(
            modifier = modifier.padding(16.dp),
            text = stringResource(R.string.no_items_found)
        )
    }
}

@Preview
@Composable
private fun HomePreview() {
    HomeScreen(
        state = HomeState(
            items = sakes
        ),
        onItemClick = {}
    )
}

@Preview
@Composable
private fun HomePreviewEmpty() {
    HomeScreen(
        state = HomeState(),
        onItemClick = {}
    )
}