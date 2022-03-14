package com.ethan.crypto.data

import com.ethan.crypto.data.local.LocalStorage
import com.ethan.crypto.domain.model.CurrencyModel
import com.ethan.crypto.domain.repository.CurrencyRepository
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val currencyLocalStorage: LocalStorage,
) : CurrencyRepository {

    override suspend fun saveCurrencies(jsonData: String): Result<Boolean> {
        return try {
            Result.success(currencyLocalStorage.saveCurrencyListIfNeeded(jsonData))
        } catch (ex: Exception) {
            Result.failure(exception = ex)
        }
    }

    override suspend fun getCurrencies(): Result<List<CurrencyModel>> {
        return try {
            Result.success(currencyLocalStorage.getCurrencyList())
        } catch (ex: Exception) {
            Result.failure(exception = ex)
        }
    }
}
