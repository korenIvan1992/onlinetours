package com.onlinetours.data.entities.remoute.country

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.annotations.SerializedName

@JsonIgnoreProperties(ignoreUnknown = true)
data class RegionsDto(
    @SerializedName(value = "id")
    var id: Int,
    @SerializedName(value = "name")
    var name: String?,
    @SerializedName(value = "flag")
    var flag: String?,
    @SerializedName(value = "visa_text")
    var visa_text: String?,
    @SerializedName(value = "path_ids")
    var pathIds: List<Int>,

    )