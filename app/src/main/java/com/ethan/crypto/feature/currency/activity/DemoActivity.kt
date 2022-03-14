package com.ethan.crypto.feature.currency.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ethan.crypto.R
import com.ethan.crypto.databinding.ActivityDemoBinding
import com.ethan.crypto.feature.currency.fragment.CurrencyListFragment
import com.ethan.crypto.feature.currency.model.CurrencyViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DemoActivity : AppCompatActivity() {

    private val viewModel by viewModels<CurrencyDemoViewModel>()

    private lateinit var viewBinding: ActivityDemoBinding

    private val currencyListFragment: CurrencyListFragment by lazy {
        CurrencyListFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityDemoBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewModel.saveCurrencies()

        initViews()
        setupObservers()

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currencyListFragment)
                .commitAllowingStateLoss()
        }
    }

    private fun setupObservers() {
        viewModel.detailViewState.observe(this) {
            when (it) {
                is CurrencyViewState.Error -> {
                    Toast.makeText(this, it.throwable?.message, Toast.LENGTH_SHORT)
                        .show()
                }
                is CurrencyViewState.SaveCurrencySucceed -> {
                    viewBinding.btnDisplayData.isEnabled = true
                    viewBinding.btnSortData.isEnabled = true
                }
            }
        }
    }

    private fun initViews() {
        viewBinding.btnDisplayData.setOnClickListener {
            currencyListFragment.loadData()
        }
        viewBinding.btnSortData.setOnClickListener {
            currencyListFragment.sortData()
        }
    }
}