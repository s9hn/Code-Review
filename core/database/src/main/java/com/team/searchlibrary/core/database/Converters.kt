package com.team.searchlibrary.core.database

import androidx.room.TypeConverter

internal class Converters {
    @TypeConverter
    fun fromString(value: String): List<String> =
        if (value.isEmpty()) emptyList() else value.split(",")

    @TypeConverter
    fun listToString(list: List<String>): String = list.joinToString(",")
}
