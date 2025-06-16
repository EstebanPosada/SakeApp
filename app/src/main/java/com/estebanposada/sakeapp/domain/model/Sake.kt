package com.estebanposada.sakeapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Sake(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val name: String,
    val description: String,
    @SerialName("picture") val picture: String? = null,
    val rating: Float,
    val address: String,
    val coordinates: List<Double>,
    @SerialName("google_maps_link") val google_maps_link: String,
    val website: String,
)

val sakes = listOf(
    Sake(
        id = 1,
        name = "Sake 1",
        description = "Description of Sake 1",
        picture = "https://example.com/sake1.jpg",
        rating = 4.5f,
        address = "123 Sake St, Tokyo, Japan",
        coordinates = listOf(35.6895, 139.6917),
        google_maps_link = "https://maps.google.com/sake1",
        website = "https://sake1.example.com"
    ),
    Sake(
        id = 2,
        name = "Sake 2",
        description = "Description of Sake 2",
        picture = "https://example.com/sake2.jpg",
        rating = 4.0f,
        address = "456 Sake Ave, Kyoto, Japan",
        coordinates = listOf(35.0116, 135.7681),
        google_maps_link = "https://maps.google.com/sake2",
        website = "https://sake2.example.com"
    )
)