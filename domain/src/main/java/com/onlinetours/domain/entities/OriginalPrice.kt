package com.onlinetours.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OriginalPrice(
    val price: Double,
    val currency: String,
): Parcelable
