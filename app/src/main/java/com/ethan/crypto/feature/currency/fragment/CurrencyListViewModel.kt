package com.ethan.crypto.feature.currency.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ethan.crypto.domain.model.CurrencyModel
import com.ethan.crypto.domain.usecase.CurrencyUseCase
import com.ethan.crypto.feature.currency.model.CurrencyViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class CurrencyListViewModel @Inject constructor(
    private val currencyUseCase: CurrencyUseCase,
) : ViewModel() {

    val detailViewState = MutableLiveData<CurrencyViewState>()

    private var listLoadedItems = listOf<CurrencyModel>()

    private var sortedByDescending = false

    fun getCurrencies() {
        detailViewState.postValue(CurrencyViewState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            val result = currencyUseCase.getCurrencies()
            when {
                result.isFailure -> {
                    detailViewState.postValue(CurrencyViewState.Error(throwable = result.exceptionOrNull()))
                }
                result.isSuccess -> {
                    result.getOrNull()?.let {
                        listLoadedItems = it
                        detailViewState.postValue(
                            CurrencyViewState.GetCurrencySucceed(
                                currencies = it
                            )
                        )
                    }
                }
            }
        }
    }

    fun sortCurrencies() {
        viewModelScope.launch(Dispatchers.IO) {
            val itemsSorted = if (sortedByDescending) {
                listLoadedItems.sortedByDescending { it.name }
            } else {
                listLoadedItems.sortedBy { it.name }
            }
            detailViewState.postValue(
                CurrencyViewState.SortCurrencySucceed(
                    currenciesSorted = itemsSorted
                )
            )
            sortedByDescending = !sortedByDescending
        }
    }
}