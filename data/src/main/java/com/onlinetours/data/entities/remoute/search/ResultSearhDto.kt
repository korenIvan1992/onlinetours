package com.onlinetours.data.entities.remoute.search

import com.google.gson.annotations.SerializedName

data class ResultSearhDto(
    @SerializedName("offer") val offer: OfferDto,
    @SerializedName("count") val count: Int
)