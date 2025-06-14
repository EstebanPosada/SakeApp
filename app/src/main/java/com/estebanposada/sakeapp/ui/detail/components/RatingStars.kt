package com.estebanposada.sakeapp.ui.detail.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.estebanposada.sakeapp.R

@Composable
fun RatingStars(modifier: Modifier = Modifier, rating: Float) {
    val fullStars = rating.toInt()
    val hasHalfStar = rating % 1 != 0f
    Row {
        repeat(5) { index ->
            val icon = ImageVector.vectorResource(
                when {
                    index < fullStars -> R.drawable.ic_star_filled
                    index == fullStars && hasHalfStar -> R.drawable.ic_star_half
                    else -> R.drawable.ic_star_border
                }
            )
            Icon(
                imageVector = icon,
                contentDescription = "Star Rating",
                modifier = modifier.size(24.dp),
                tint = Color.DarkGray
            )
        }
    }
}

@Preview
@Composable
private fun RatingStarsPreview() {
    RatingStars(rating = 2.5f)
}