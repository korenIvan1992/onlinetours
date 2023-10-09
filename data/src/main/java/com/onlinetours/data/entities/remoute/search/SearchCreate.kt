package com.onlinetours.data.entities.remoute.search

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.annotations.SerializedName

data class SearchCreate(
    @SerializedName("depart_city_id") val cityFrom: Long,
    @SerializedName("region_ids") val regionTo: List<Int>,
    @SerializedName("meal_type_ids") val meal_type_ids: List<Int> = listOf(740),
    @SerializedName("start_from") val startFrom: String = "2023-10-06",
    @SerializedName("start_to") val startTo: String = "2024-10-06",
    @SerializedName("duration_from") val duration_from: Int = 13,
    @SerializedName("duration_to") val durationTo: Int = 17,
    @SerializedName("adults") val adults: Int = 1,
    @SerializedName("kids") val kids: Int = 0,
    @SerializedName("kids_ages") val kidsAges: List<Int> = listOf(),

    )

