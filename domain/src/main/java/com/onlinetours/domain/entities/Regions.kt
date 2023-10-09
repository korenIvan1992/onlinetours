package com.onlinetours.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Regions(
    var id: Int = 0,
    var name: String = "",
    var flag: String = "",
    var visaText: String = "",
    var pathIds: List<Int>
) : Parcelable


