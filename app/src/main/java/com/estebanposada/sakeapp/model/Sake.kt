package com.estebanposada.sakeapp.model

data class Sake(

    val name: String,
    val description: String,
    val pictureUrl: String,
    val rating: Float,
    val address: String,
    val coordinates: List<Double>,
    val mapLink: String,
    val website: String,
)
