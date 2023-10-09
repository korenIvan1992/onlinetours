package com.onlinetours.ui

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.onlinetours.domain.entities.result.Tour
import com.onlinetours.ui.tours.ListTourFragment


object Screens {

    fun openTours(list : List<Tour>,name : String) = FragmentScreen(clearContainer = false) {
        ListTourFragment.newInstance(list,name)
    }
}




