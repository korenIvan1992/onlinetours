package com.onlinetours.data.entities.remoute.city

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.annotations.SerializedName

@JsonIgnoreProperties(ignoreUnknown = true)
data class CityDto(
    @SerializedName(value = "id")
    var id: Int,
    @SerializedName(value = "name")
    var name: String,
    @SerializedName(value = "country_id")
    var country_id: String,
    @SerializedName(value = "country_name")
    var country_name : String,
    @SerializedName(value = "path_ids")
    var path_ids : List<Int>,

)

