package com.onlinetours.data.entities.remoute.search

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.annotations.SerializedName

@JsonIgnoreProperties(ignoreUnknown = true)
data class OriginalPriceDto (
    @SerializedName("price") val price: Double,
    @SerializedName("currency") val currency: String,
)
