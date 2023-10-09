package com.onlinetours.data.entities.remoute.search

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.annotations.SerializedName

@JsonIgnoreProperties(ignoreUnknown = true)
data class OfferDto(
    @SerializedName("original_price") val originalPrice: OriginalPriceDto,
    @SerializedName("original_name") val originalName: String,
    @SerializedName("operator_name") val operator_name: String,

    )
