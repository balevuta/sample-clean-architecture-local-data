package com.ethan.crypto.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CurrencyProviderModule {

    private const val CRYPTO_PREFS = "CryptoPrefs"

    @Provides
    fun providesSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        return context.getSharedPreferences(CRYPTO_PREFS, Context.MODE_PRIVATE)
    }
}
