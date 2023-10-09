package com.onlinetours.data.api

import com.onlinetours.data.entities.remoute.search.SearchCreate
import com.onlinetours.data.entities.remoute.city.CityDto
import com.onlinetours.data.entities.remoute.country.RegionsDto
import com.onlinetours.data.entities.remoute.search.ResponceCreateSearchDto
import com.onlinetours.data.entities.remoute.search.SearchData
import com.onlinetours.data.entities.remoute.search.SearchResultDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {

    /**
     * Так как нет регистрации токен здесь вставил.
     * Так как не регистрации и мы его нигде не получаем
     */
    @GET("depart_cities")
    suspend fun getCities(
        @Header("Authorization") token: String = "Token token=66e060af6f5ed7d4217dc7d05ad909da"
    ): Response<List<CityDto>>

    // Cначала сделал для странн
    // - потом не увидел в АPI(для запроса посика) куда странну можно отправить
    // ,а обязательное поле регион
    // поэтому просто быстро переделал на получение регионов
    // ( для тестого задания подойдет... быстро делал...)
//    @GET("countries")
    @GET("regions")
    suspend fun getCountries(
        @Header("Authorization") token: String = "Token token=66e060af6f5ed7d4217dc7d05ad909da"
    ): Response<List<RegionsDto>>


    @POST("searches")
    suspend fun createSearches(
        @Header("Authorization") token: String = "Token token=66e060af6f5ed7d4217dc7d05ad909da",
        @Body body: SearchData
    ): Response<ResponceCreateSearchDto>

    @GET("searches/{key}/results")
    suspend fun searchTours(
        @Header("Authorization") token: String = "Token token=66e060af6f5ed7d4217dc7d05ad909da",
        @Path("key") key: String,
    ): Response<SearchResultDto>




}