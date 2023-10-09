package com.onlinetours.data.repository

import android.content.Context
import com.ringtone2appwallpapers.data.entities.remoute.ResultRemoute
import com.onlinetours.data.datasource.local.LocalDataSource
import com.onlinetours.data.datasource.remoute.RemoteDataSource
import com.onlinetours.data.entities.local.city.CityDb
import com.onlinetours.data.mapper.local.ModelMapperLocal
import com.onlinetours.data.mapper.service.ModelMapperService
import com.onlinetours.domain.entities.City
import com.onlinetours.domain.entities.result.ResultDomain
import com.onlinetours.domain.repositories.IMyRepository
import com.onlinetours.data.R
import com.onlinetours.data.entities.local.country.RegionsDb
import com.onlinetours.data.entities.remoute.country.RegionsDto
import com.onlinetours.domain.entities.Regions
import com.onlinetours.domain.entities.result.Tour
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch


class MyRepositoryImpl(
    private val context: Context,
    private val mapperService: ModelMapperService,
    private val mapperLocal: ModelMapperLocal,
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : IMyRepository {


    override fun getCities(): Flow<ResultDomain<List<City>>> =
        flow {
            localDataSource.getData().let {
                if (it.isEmpty()) {
                    // Если база пустая - делаем загрузку с бэка
                    when (val result = remoteDataSource.getCities()) {
                        is ResultRemoute.Error ->
                            emit(ResultDomain.Error(result.message))

                        is ResultRemoute.Success -> {
                            result.data.let {
                                if (it.isEmpty())
                                    emit(ResultDomain.Error(context.resources.getString(R.string.list_empty)))
                                else {
                                    val listDb: List<CityDb> =
                                        mapperService.convertListCityDb(result.data)
                                    val list = mapperLocal.convertListCity(listDb)

                                    CoroutineScope(Dispatchers.IO).launch {
                                        localDataSource.setData(listDb)
                                    }
                                    emit(ResultDomain.Success(list))
                                }
                            }
                        }
                    }
                } else {
                    // Иначе берем с базы
                    val list = mapperLocal.convertListCity(it)
                    emit(ResultDomain.Success(list))
                }
            }

        }

    override fun getCountries(): Flow<ResultDomain<List<Regions>>> =
        flow {
            localDataSource.getDataCountry().let {
                if (it.isEmpty()) {
                    // Если база пустая - делаем загрузку с бэка
                    when (val result = remoteDataSource.getCountries()) {
                        is ResultRemoute.Error ->
                            emit(ResultDomain.Error(result.message))

                        is ResultRemoute.Success -> {
                            result.data.let { itList ->
                                if (itList.isEmpty())
                                    emit(ResultDomain.Error(context.resources.getString(R.string.list_empty)))
                                else {
                                    val listRegion = mutableListOf<RegionsDto>()
                                    // Покажем 100 штук - странно сразу грузить 10 000 регионов
                                    // торопился
                                    // Для тестового сойдет
                                    // чтобы не уточнять
                                    itList.forEachIndexed { index, itR ->
                                        if (index <= 100)
                                            listRegion.add(itR)
                                    }
                                    val listDb: List<RegionsDb> =
                                        mapperService.convertListCountryDb(listRegion)
                                    val list = mapperLocal.convertListCountry(listDb)

                                    CoroutineScope(Dispatchers.IO).launch {
                                        localDataSource.setDataCountry(listDb)
                                    }
                                    emit(ResultDomain.Success(list))
                                }
                            }
                        }
                    }
                } else {
                    // Иначе берем с базы
                    val list = mapperLocal.convertListCountry(it)
                    emit(ResultDomain.Success(list))
                }
            }

        }

    override fun searchTours(
        departCityId: Long,
        regionIds: List<Int>
    ): Flow<ResultDomain<List<Tour>>> =
        flow {
            when (val result = remoteDataSource.search(departCityId, regionIds)) {
                is ResultRemoute.Error ->
                    emit(ResultDomain.Error(result.message))

                is ResultRemoute.Success -> {
                    result.data.let { itList ->
                        if (itList.isEmpty())
                            emit(ResultDomain.Error(context.resources.getString(R.string.no_tour)))
                        else {
                            val list: List<Tour> =
                                mapperService.convertListOffer(result.data)
                            emit(ResultDomain.Success(list))
                        }
                    }
                }
            }
        }
}