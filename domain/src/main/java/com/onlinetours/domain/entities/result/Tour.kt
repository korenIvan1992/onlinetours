package com.onlinetours.domain.entities.result

import android.os.Parcelable
import com.onlinetours.domain.entities.Offer
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tour(
    val offer: Offer,
    val count: Int = 0,
): Parcelable
