package com.onlinetours.ui.tours

import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import com.onlinetours.base.BaseViewModel
import com.onlinetours.domain.usecases.MyUseCase
import com.onlinetours.base.extensions.set
import com.onlinetours.domain.entities.result.ResultDomain
import com.onlinetours.ui.utills.SearchState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListToursViewModel @Inject
constructor(
    private val router: Router,
    private val useCase: MyUseCase
) : BaseViewModel<SearchState>(SearchState.Loading) {


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

}