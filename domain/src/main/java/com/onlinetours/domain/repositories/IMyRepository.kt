package com.onlinetours.domain.repositories

import com.onlinetours.domain.entities.City
import com.onlinetours.domain.entities.Regions
import com.onlinetours.domain.entities.result.ResultDomain
import com.onlinetours.domain.entities.result.Tour
import kotlinx.coroutines.flow.Flow


interface IMyRepository {

    fun getCities(): Flow<ResultDomain<List<City>>>
    fun getCountries(): Flow<ResultDomain<List<Regions>>>
    fun searchTours(departCityId: Long, regionIds: List<Int>):  Flow<ResultDomain<List<Tour>>>
}