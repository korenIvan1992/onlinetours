package com.onlinetours.domain.usecases

import com.onlinetours.domain.entities.City
import com.onlinetours.domain.entities.Regions
import com.onlinetours.domain.entities.result.ResultDomain
import com.onlinetours.domain.entities.result.Tour
import com.onlinetours.domain.repositories.IMyRepository
import kotlinx.coroutines.flow.Flow


class MyUseCase(val myRepository: IMyRepository) {

    fun getDataCities(): Flow<ResultDomain<List<City>>> =
        myRepository.getCities()

    fun getDataCountries(): Flow<ResultDomain<List<Regions>>> =
        myRepository.getCountries()

    fun searchTours(departCityId:Long,regionIds : List<Int>): Flow<ResultDomain<List<Tour>>> =
        myRepository.searchTours(departCityId,regionIds)
}