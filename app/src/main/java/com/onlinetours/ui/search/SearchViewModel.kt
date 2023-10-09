package com.onlinetours.ui.search

import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import com.onlinetours.R
import com.onlinetours.base.BaseViewModel
import com.onlinetours.domain.usecases.MyUseCase
import com.onlinetours.base.extensions.set
import com.onlinetours.domain.entities.City
import com.onlinetours.domain.entities.Regions
import com.onlinetours.domain.entities.result.ResultDomain
import com.onlinetours.domain.entities.result.Tour
import com.onlinetours.ui.Screens
import com.onlinetours.ui.utills.SearchState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject
constructor(
    private val router: Router,
    private val useCase: MyUseCase
) : BaseViewModel<SearchState>(SearchState.Loading) {

    var cityFrom: City? = null
    var regionsTo: Regions? = null
    var listCity: List<City>? = null
    var listRegions: List<Regions>? = null

    fun getData() {
        getCity()
        getCountry()
    }

    private fun getCity() {
        state.set(SearchState.Loading)
        viewModelScope.launch {
            useCase.getDataCities().catch {
                it.localizedMessage?.let { error ->
                    state.set(SearchState.Error(error))
                }
            }.collect {
                when (it) {
                    is ResultDomain.Success -> {
                        listCity = it.data
                        state.set(SearchState.Success(it.data))
                    }

                    is ResultDomain.Error -> {
                        state.set(SearchState.Error(it.message))
                    }
                }
            }
        }

    }

    private fun getCountry() {
        state.set(SearchState.Loading)
        viewModelScope.launch {
            useCase.getDataCountries().catch {
                it.localizedMessage?.let { error ->
                    state.set(SearchState.Error(error))
                }
            }.collect {
                when (it) {
                    is ResultDomain.Success -> {
                        listRegions = it.data
                        state.set(SearchState.Success(it.data))
                    }

                    is ResultDomain.Error -> {
                        state.set(SearchState.Error(it.message))
                    }
                }
            }
        }

    }


    override fun backPress() {
        router.exit()
    }

    fun setCityFromData(it: City) {
        cityFrom = it
    }

    fun setCountryFromData(it: Regions) {
        regionsTo = it
    }

    fun searchTours() {
        state.set(SearchState.Loading)
        if (cityFrom != null && regionsTo != null) {
            viewModelScope.launch {
                useCase.searchTours(cityFrom!!.id.toLong(), regionsTo!!.pathIds).catch {
                    it.localizedMessage?.let { error ->
                        state.set(SearchState.Error(error))
                    }
                }.collect {
                    when (it) {
                        is ResultDomain.Success -> {
                            it.data.let { list ->
                                if (list.isEmpty())
                                    state.set(SearchState.ErrorInt(R.string.no_tours))
                                else {
                                    state.set(SearchState.Suspense)
                                    router.navigateTo(Screens.openTours(it.data, regionsTo!!.name))
                                }
                            }
                        }

                        is ResultDomain.Error -> {
                            state.set(SearchState.Error(it.message))
                        }
                    }
                }
            }
        } else
            state.set(SearchState.ErrorInt(R.string.empty_data_))


    }

}