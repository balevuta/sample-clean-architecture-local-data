package com.ethan.crypto.domain.usecase

import com.ethan.crypto.domain.model.CurrencyModel

interface CurrencyUseCase {

    suspend fun saveCurrencies(jsonData: String): Result<Boolean>

    suspend fun getCurrencies(): Result<List<CurrencyModel>>
}
