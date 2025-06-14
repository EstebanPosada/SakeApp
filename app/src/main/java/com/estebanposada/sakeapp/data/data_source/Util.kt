package com.estebanposada.sakeapp.data.data_source

import android.content.Context
import com.estebanposada.sakeapp.domain.model.Sake
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun loadSakeListFromJson(context: Context): List<Sake> {
    val json = context.assets.open("sake_list.json").bufferedReader().use { it.readText() }
    val type = object : TypeToken<List<Sake>>() {}.type
    val sakeList: List<Sake> = Gson().fromJson(json, type)
    return sakeList.map { sake ->
        if (sake.id == null || sake.id == 0) sake.copy(id = (1..Int.MAX_VALUE).random())
        else sake
    }
}