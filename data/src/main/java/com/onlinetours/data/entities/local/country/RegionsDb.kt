package com.onlinetours.data.entities.local.country

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "country" )
data class RegionsDb(
        @PrimaryKey(autoGenerate = true)
        val id: Int= 0,
        var name: String,
        var flag: String,
        var visaText: String,
        var pathIds: List<Int>,

)




