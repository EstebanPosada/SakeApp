package com.estebanposada.sakeapp.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.estebanposada.sakeapp.domain.model.sakes

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeState = HomeState(),
    onItemClick: (Int) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {},
        content = { innerPadding ->
            LazyColumn(modifier = modifier.padding(innerPadding)) {
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
        },
    )
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