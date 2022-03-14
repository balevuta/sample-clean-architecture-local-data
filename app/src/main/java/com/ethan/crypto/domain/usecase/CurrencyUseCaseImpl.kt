package com.ethan.crypto.domain.usecase

import com.ethan.crypto.domain.model.CurrencyModel
import com.ethan.crypto.domain.repository.CurrencyRepository
import javax.inject.Inject

class CurrencyUseCaseImpl @Inject constructor(
    private val repository: CurrencyRepository
) : CurrencyUseCase {

    override suspend fun saveCurrencies(jsonData: String): Result<Boolean> {
        return repository.saveCurrencies(jsonData)
    }

    override suspend fun getCurrencies(): Result<List<CurrencyModel>> {
        return repository.getCurrencies()
    }
}
