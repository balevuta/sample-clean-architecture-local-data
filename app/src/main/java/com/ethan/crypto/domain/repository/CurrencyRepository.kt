package com.ethan.crypto.domain.repository

import com.ethan.crypto.domain.model.CurrencyModel

interface CurrencyRepository {

    suspend fun saveCurrencies(jsonData: String): Result<Boolean>

    suspend fun getCurrencies(): Result<List<CurrencyModel>>
}
