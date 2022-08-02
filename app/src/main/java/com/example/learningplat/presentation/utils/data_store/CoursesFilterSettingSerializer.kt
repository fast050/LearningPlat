package com.example.learningplat.presentation.utils.data_store

import androidx.datastore.core.Serializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream





object CoursesFilterSettingSerializer : Serializer<CoursesFilterSetting> {
    override val defaultValue: CoursesFilterSetting
        get() = CoursesFilterSetting()

    override suspend fun readFrom(input: InputStream): CoursesFilterSetting {
        return try {
            Json.decodeFromString(
                deserializer = CoursesFilterSetting.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: CoursesFilterSetting, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = CoursesFilterSetting.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }
}

