package com.onlinetours.data.datasource.remoute

import android.content.Context
import com.ringtone2appwallpapers.data.entities.remoute.ResultRemoute
import com.onlinetours.data.api.Api
import com.onlinetours.data.entities.remoute.city.CityDto
import com.onlinetours.data.entities.remoute.country.RegionsDto
import com.onlinetours.data.entities.remoute.search.ResultSearhDto
import com.onlinetours.data.entities.remoute.search.SearchCreate
import com.onlinetours.data.entities.remoute.search.SearchData

class RemoteDataSource(
    val context: Context,
    val api: Api
) {

    suspend fun getCities(): ResultRemoute<List<CityDto>> {

        val response = api.getCities()
        return if (response.isSuccessful) {
            val responseBody = response.body()
            try {
                ResultRemoute.Success(responseBody!!)

            } catch (ex: Exception) {
                ResultRemoute.Error(
                    if (ex.localizedMessage != null) ex.localizedMessage
                    else ex.toString()
                )
            }
        } else {
            ResultRemoute.Error("Error request. Response code:${response.code()}")
        }
    }

    suspend fun getCountries(): ResultRemoute<List<RegionsDto>> {
        val response = api.getCountries()
        return if (response.isSuccessful) {
            val responseBody = response.body()
            try {
                ResultRemoute.Success(responseBody!!)

            } catch (ex: Exception) {
                ResultRemoute.Error(
                    if (ex.localizedMessage != null) ex.localizedMessage
                    else ex.toString()
                )
            }
        } else {
            ResultRemoute.Error("Error request. Response code:${response.code()}")
        }
    }

    suspend fun search(
        departCityId: Long,
        regionIds: List<Int>
    ): ResultRemoute<List<ResultSearhDto>> {
        // Кастыль сделал!!! - странно что regionIds может быть пустым
        // присвоил дефолтное
        // так как тестовое задание
        val regId = regionIds.ifEmpty { return@ifEmpty listOf(1,2) }
        val search = SearchCreate(
            //  добавил хардкордно depart_city_id  = 20001
            // для того чтобы протестить приложение
            // так как до этого для других вечно для запроса
            // "searches/{key}/results") ---> возвращал пустой всегда "results": []
            cityFrom = departCityId,  //--> todo потом вернуть
//            cityFrom = 20001,
            regionTo = regId
        )
        val request = SearchData(search)

        // Создание запроса для посика туров по парамметрам
        val response = api.createSearches(body = request)
        if (response.isSuccessful) {
            // Получили key для дальнейшего запроса
            // Теперь можем получить предложенные туры
            val responseBody = response.body()
            return try {
                // Запрашиваем туры по key
                val responseSearch = api.searchTours(key = responseBody!!.key)
                if (responseSearch.isSuccessful) {
                    // Получили туры - показываем клиенту
                    val responseBodySearch = responseSearch.body()
                    ResultRemoute.Success(responseBodySearch!!.result)
                } else
                    ResultRemoute.Error("Error request. Response code:${response.code()}")

            } catch (ex: Exception) {
                ResultRemoute.Error(
                    if (ex.localizedMessage != null) ex.localizedMessage
                    else ex.toString()
                )
            }
        } else {
            // Первый подготовительный запрос не успешный
            return ResultRemoute.Error("Error request. Response code:${response.code()}")
        }
    }

}


