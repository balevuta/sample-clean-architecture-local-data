package com.ethan.crypto.data.local

import com.ethan.crypto.domain.model.CurrencyModel

interface LocalStorage {

    fun getCurrencyList(): List<CurrencyModel>

    fun saveCurrencyListIfNeeded(jsonData: String): Boolean
}
