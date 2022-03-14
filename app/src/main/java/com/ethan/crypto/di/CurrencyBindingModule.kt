package com.ethan.crypto.di

import com.ethan.crypto.data.CurrencyRepositoryImpl
import com.ethan.crypto.data.local.LocalStorage
import com.ethan.crypto.data.local.LocalStorageImpl
import com.ethan.crypto.domain.repository.CurrencyRepository
import com.ethan.crypto.domain.usecase.CurrencyUseCase
import com.ethan.crypto.domain.usecase.CurrencyUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class CurrencyBindingModule {

    @Binds
    abstract fun bindCurrencyUseCase(useCaseImpl: CurrencyUseCaseImpl): CurrencyUseCase

    @Binds
    abstract fun bindCurrencyRepository(
        currencyRepositoryImpl: CurrencyRepositoryImpl
    ): CurrencyRepository

    @Binds
    abstract fun bindLocalStorage(storageImpl: LocalStorageImpl): LocalStorage
}
