package com.estebanposada.sakeapp.domain.util

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParseException
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ResourceTypeAdapter<T> : JsonDeserializer<Resource<T>>, JsonSerializer<Resource<T>> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Resource<T> {
        val jsonObject = json.asJsonObject
        val statusElement = jsonObject.get("status")
        if (statusElement == null || !statusElement.isJsonPrimitive) {
            throw JsonParseException("Missing or invalid 'status' field")
        }
        return when (jsonObject.get("status").asString) {
            "success" ->
                Resource.Success(
                    context.deserialize<T>(
                        jsonObject.get("data"),
                        (typeOfT as ParameterizedType).actualTypeArguments[0]
                    )
                )

            "error" -> Resource.Error(jsonObject.get("message").asString)
            else -> throw JsonParseException("Unknown status: ${jsonObject.get("status").asString}")
        }
    }

    override fun serialize(
        src: Resource<T>,
        typeOfSrc: Type,
        context: JsonSerializationContext
    ): JsonElement {
        val jsonObject = JsonObject()
        when (src) {
            is Resource.Success -> {
                jsonObject.addProperty("status", "success")
                jsonObject.add("data", context.serialize(src.data))
            }

            is Resource.Error -> {
                jsonObject.addProperty("status", "error")
                jsonObject.addProperty("message", src.message)
            }
        }
        return jsonObject
    }

}