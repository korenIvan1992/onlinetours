package com.onlinetours.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Offer(
    val originalPrice: OriginalPrice,
    val originalName: String,
    val operatorName : String
): Parcelable
