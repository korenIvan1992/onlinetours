package com.onlinetours.data.entities.local.city

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city" )
data class CityDb(
        @PrimaryKey(autoGenerate = true)
        val id: Int= 0,
        var name: String,
        var path_ids: List<Int>,

)




