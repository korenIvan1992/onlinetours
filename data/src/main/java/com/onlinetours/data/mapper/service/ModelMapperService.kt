package com.onlinetours.data.mapper.service

import com.onlinetours.data.entities.local.city.CityDb
import com.onlinetours.data.entities.local.country.RegionsDb
import com.onlinetours.data.entities.remoute.city.CityDto
import com.onlinetours.data.entities.remoute.country.RegionsDto
import com.onlinetours.data.entities.remoute.search.ResultSearhDto
import com.onlinetours.domain.entities.City
import com.onlinetours.domain.entities.Offer
import com.onlinetours.domain.entities.OriginalPrice
import com.onlinetours.domain.entities.result.Tour

class ModelMapperService {

    fun convertListCityDb(list: List<CityDto>): List<CityDb> {
        val listReturn = mutableListOf<CityDb>()
        list.forEachIndexed { _, it ->
            listReturn.add(
                CityDb(
                    id = it.id,
                    name = it.name,
                    path_ids = it.path_ids
                )
            )
        }
        return listReturn
    }

    fun convertListCountryDb(list: List<RegionsDto>): List<RegionsDb> {
        val listReturn = mutableListOf<RegionsDb>()
        list.forEachIndexed { _, it ->
            listReturn.add(
                RegionsDb(
                    id = it.id,
                    name = it.name ?: "name",
                    visaText = it.visa_text ?: "visa_text",
                    flag = it.flag ?: "flag",
                    pathIds = it.pathIds
                )
            )
        }
        return listReturn
    }

    fun convertListOffer(list: List<ResultSearhDto>): List<Tour> {
        val listReturn = mutableListOf<Tour>()
        list.forEachIndexed { _, it ->
            listReturn.add(
                Tour(
                    offer =  Offer(
                        originalPrice = OriginalPrice(
                            price =  it.offer.originalPrice.price,
                            currency = it.offer.originalPrice.currency?:"RUB"
                        ),
                        originalName = it.offer.originalName ?: "name",
                        operatorName =  it.offer.operator_name

                    ),
                    count = 0
                )

            )
        }
        return listReturn
    }

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

}