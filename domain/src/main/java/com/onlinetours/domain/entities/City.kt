package com.onlinetours.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class City(
    var id: Int = 0,
    var name: String = "",
    var country_name: String = "",
    var pathIds: List<Int>

) : Parcelable


