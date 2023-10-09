package com.onlinetours.data.mapper.local

import com.onlinetours.data.entities.local.city.CityDb
import com.onlinetours.data.entities.local.country.RegionsDb
import com.onlinetours.domain.entities.City
import com.onlinetours.domain.entities.Regions

class ModelMapperLocal {

    fun convertListCity(list: List<CityDb>): List<City> {
        val listReturn = mutableListOf<City>()
        list.forEachIndexed { _, it ->
            listReturn.add(
                City(
                    id = it.id,
                    name = it.name,
                    pathIds = it.path_ids
                )
            )
        }
        return listReturn
    }

    fun convertListCountry(list: List<RegionsDb>): List<Regions> {
        val listReturn = mutableListOf<Regions>()
        list.forEachIndexed { _, it ->
            listReturn.add(
                Regions(
                    id = it.id,
                    name = it.name,
                    visaText = it.visaText,
                    flag = it.flag,
                    pathIds = it.pathIds
                )
            )
        }
        return listReturn
    }



}