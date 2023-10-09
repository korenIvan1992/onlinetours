package com.onlinetours.data.entities.remoute.search

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.annotations.SerializedName

@JsonIgnoreProperties(ignoreUnknown = true)
data class SearchResultDto(
    @SerializedName("results") val result: List<ResultSearhDto>
    )