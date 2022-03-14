package com.ethan.crypto.feature.currency.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ethan.crypto.domain.model.CurrencyModel

class CurrencyAdapter(
) : ListAdapter<CurrencyModel, RecyclerView.ViewHolder>(CurrencyDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CurrencyItemVH.onCreateViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItem(position)) {
            is CurrencyModel -> {
                (holder as CurrencyItemVH).onBind(model = getItem(position) as CurrencyModel)
            }
        }
    }
}

class CurrencyDiffCallback : DiffUtil.ItemCallback<CurrencyModel>() {

    override fun areItemsTheSame(
        oldItem: CurrencyModel,
        newItem: CurrencyModel
    ): Boolean {
        return oldItem.name == newItem.name
            && oldItem.symbol == newItem.symbol
    }

    override fun areContentsTheSame(
        oldItem: CurrencyModel,
        newItem: CurrencyModel
    ): Boolean {
        return oldItem == newItem
    }
}
