package com.estebanposada.sakeapp.ui.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    state: DetailState
) {
}

@Preview
@Composable
private fun DetailPreview() {
    DetailScreen(state = DetailState())
}