package com.ethan.crypto.feature.currency.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ethan.crypto.databinding.LayoutCurrencyItemBinding
import com.ethan.crypto.domain.model.CurrencyModel

class CurrencyItemVH constructor(
    private val binding: LayoutCurrencyItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(model: CurrencyModel) {
        binding.tvName.text = model.name
        binding.tvSymbol.text = model.symbol
    }

    companion object {

        fun onCreateViewHolder(
            parent: ViewGroup,
        ): RecyclerView.ViewHolder {
            val binding = LayoutCurrencyItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return CurrencyItemVH(binding = binding)
        }
    }
}