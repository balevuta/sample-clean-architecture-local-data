package com.ethan.crypto.data.local

import android.content.SharedPreferences
import com.ethan.crypto.domain.model.CurrencyModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type
import javax.inject.Inject

class LocalStorageImpl @Inject constructor(
    private val preferences: SharedPreferences,
    private val moshi: Moshi
) : LocalStorage {

    override fun getCurrencyList(): List<CurrencyModel> {
        val json: String = preferences.getString(KEY_CURRENCY_LIST_DATA, "").orEmpty()
        if (json.isEmpty()) {
            return listOf()
        }
        val type: Type = Types.newParameterizedType(
            MutableList::class.java,
            CurrencyModel::class.java
        )
        return try {
            val panelListAdapter: JsonAdapter<List<CurrencyModel>> = moshi.adapter(type)
            val result = panelListAdapter.fromJson(json)
            result.orEmpty()
        } catch (ex: Exception) {
            emptyList()
        }
    }

    override fun saveCurrencyListIfNeeded(jsonData: String): Boolean {
        if (preferences.getString(KEY_CURRENCY_LIST_DATA, "").isNullOrEmpty()) {
            edit { putString(KEY_CURRENCY_LIST_DATA, jsonData) }
            return true
        }
        return false
    }

    private inline fun edit(block: SharedPreferences.Editor.() -> Unit) {
        with(preferences.edit()) {
            block()
            commit()
        }
    }


    companion object {
        private const val KEY_CURRENCY_LIST_DATA = "KEY_CURRENCY_LIST_DATA"
    }
}
