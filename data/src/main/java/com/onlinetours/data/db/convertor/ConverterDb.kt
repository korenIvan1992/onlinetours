package com.onlinetours.data.db.convertor

import androidx.room.TypeConverter
import com.fasterxml.jackson.databind.ObjectMapper
import com.onlinetours.data.entities.local.city.CityDb

class ConverterDb {

    @TypeConverter
    fun fromListStarWars(data: List<CityDb>): String {
        return ObjectMapper().writeValueAsString(data)
    }

    @TypeConverter
    fun toListStarWars(data: String): List<CityDb> {
        return ObjectMapper().readValue(data, ObjectMapper().typeFactory.constructCollectionType
            (MutableList::class.java, CityDb::class.java))
    }

    @TypeConverter
    fun fromListInt(data: List<Int>): String {
        return ObjectMapper().writeValueAsString(data)
    }

    @TypeConverter
    fun toListInt(data: String): List<Int> {
        return ObjectMapper().readValue(data, ObjectMapper().typeFactory.constructCollectionType
            (List::class.java, Int::class.java))
    }

}

