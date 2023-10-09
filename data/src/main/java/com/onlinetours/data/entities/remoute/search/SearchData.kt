package com.onlinetours.data.entities.remoute.search

import com.google.gson.annotations.SerializedName

data class SearchData(
    @SerializedName("search") val search: SearchCreate,
)
