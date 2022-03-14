package com.ethan.crypto.feature.currency.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ethan.crypto.domain.usecase.CurrencyUseCase
import com.ethan.crypto.feature.currency.model.CurrencyViewState
import com.ethan.crypto.utils.SAMPLE_DATE
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class CurrencyDemoViewModel @Inject constructor(
    private val currencyUseCase: CurrencyUseCase,
) : ViewModel() {

    val detailViewState = MutableLiveData<CurrencyViewState>()

    fun saveCurrencies() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = currencyUseCase.saveCurrencies(SAMPLE_DATE)
            when {
                result.isFailure -> {
                    detailViewState.postValue(CurrencyViewState.Error(throwable = result.exceptionOrNull()))
                }
                result.isSuccess -> {
                    result.getOrNull()?.let {
                        detailViewState.postValue(CurrencyViewState.SaveCurrencySucceed)
                    }
                }
            }
        }
    }
}