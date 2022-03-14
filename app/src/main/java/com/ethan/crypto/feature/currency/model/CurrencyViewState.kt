package com.ethan.crypto.feature.currency.model

import com.ethan.crypto.domain.model.CurrencyModel

sealed class CurrencyViewState {
    object Loading : CurrencyViewState()

    object SaveCurrencySucceed : CurrencyViewState()

    class GetCurrencySucceed(val currencies: List<CurrencyModel>) :
        CurrencyViewState()

    class SortCurrencySucceed(val currenciesSorted: List<CurrencyModel>) :
        CurrencyViewState()

    class Error(val throwable: Throwable?) : CurrencyViewState()
}