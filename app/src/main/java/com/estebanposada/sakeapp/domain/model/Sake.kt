package com.estebanposada.sakeapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Sake(
    @PrimaryKey val id: Int? = null,
    val name: String,
    val description: String,
    val pictureUrl: String,
    val rating: Float,
    val address: String,
    val coordinates: List<Double>,
    val mapLink: String,
    val website: String,
)

val sakes = listOf(
    Sake(
        id = 1,
        name = "Sake 1",
        description = "Description of Sake 1",
        pictureUrl = "https://example.com/sake1.jpg",
        rating = 4.5f,
        address = "123 Sake St, Tokyo, Japan",
        coordinates = listOf(35.6895, 139.6917),
        mapLink = "https://maps.example.com/sake1",
        website = "https://sake1.example.com"
    ),
    Sake(
        id = 2,
        name = "Sake 2",
        description = "Description of Sake 2",
        pictureUrl = "https://example.com/sake2.jpg",
        rating = 4.0f,
        address = "456 Sake Ave, Kyoto, Japan",
        coordinates = listOf(35.0116, 135.7681),
        mapLink = "https://maps.example.com/sake2",
        website = "https://sake2.example.com"
    )
)

class InvalidItemException(message: String): Exception(message)
