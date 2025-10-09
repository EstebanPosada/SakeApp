package com.estebanposada.sakeapp.data.mappers

import com.estebanposada.sakeapp.data.remote.mock.Sake

fun Sake.toDomainSake() = com.estebanposada.sakeapp.domain.model.Sake(
    name = name,
    description = description,
    picture = picture,
    rating = rating.toFloat(),
    address = address,
    coordinates = coordinates,
    google_maps_link = mapLink,
    website = website
)