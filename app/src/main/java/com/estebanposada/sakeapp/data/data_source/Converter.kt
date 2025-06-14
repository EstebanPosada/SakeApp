package com.estebanposada.sakeapp.data.data_source

import androidx.room.TypeConverter

class Converter {
    @TypeConverter
    fun fromCoordinates(coordinates: List<Double>): String =
        coordinates.joinToString(separator = ",")

    @TypeConverter
    fun toCoordinates(coordinates: String): List<Double> =
        coordinates.split(",").map { it.toDouble() }

}