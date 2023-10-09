package com.onlinetours.data.datasource.local

import com.onlinetours.data.db.dao.CityDao
import com.onlinetours.data.db.dao.CountryDao
import com.onlinetours.data.entities.local.city.CityDb
import com.onlinetours.data.entities.local.country.RegionsDb

class LocalDataSource(
    private val cityDao: CityDao,
    private val countryDao: CountryDao
) {

    // GET
    suspend fun getData(): List<CityDb> = cityDao.getListCity()

    suspend fun getDataCountry(): List<RegionsDb> = countryDao.getListCountry()

//    fun getId(id :Long ): PeopleDb = peopleDao.getId(id)

    //INSERT
    suspend fun setData(list: List<CityDb>) = cityDao.insertList(list)

    suspend fun setDataCountry(list: List<RegionsDb>) = countryDao.insertCountryList(list)

}