package com.ethan.crypto.feature.currency.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ethan.crypto.databinding.FragmentCurrencyListBinding
import com.ethan.crypto.feature.currency.adapter.CurrencyAdapter
import com.ethan.crypto.feature.currency.model.CurrencyViewState
import com.techiness.progressdialoglibrary.ProgressDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyListFragment : Fragment() {

    private val viewModel by viewModels<CurrencyListViewModel>()

    private val progressDialog: ProgressDialog by lazy {
        ProgressDialog(requireContext())
    }

    private val currenciesAdapter: CurrencyAdapter by lazy {
        CurrencyAdapter()
    }

    private var _viewBinding: FragmentCurrencyListBinding? = null
    private val viewBinding get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentCurrencyListBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setupObservers()
    }

    fun loadData() {
        viewModel.getCurrencies()
    }

    fun sortData() {
        viewModel.sortCurrencies()
    }

    private fun setupObservers() {
        viewModel.detailViewState.observe(viewLifecycleOwner) {
            when (it) {
                is CurrencyViewState.Error -> {
                    progressDialog.dismiss()
                    Toast.makeText(requireContext(), it.throwable?.message, Toast.LENGTH_SHORT)
                        .show()
                }
                is CurrencyViewState.GetCurrencySucceed -> {
                    progressDialog.dismiss()
                    currenciesAdapter.submitList(it.currencies)
                }
                is CurrencyViewState.SortCurrencySucceed -> {
                    progressDialog.dismiss()
                    currenciesAdapter.submitList(it.currenciesSorted)
                }
                CurrencyViewState.Loading -> {
                    progressDialog.show()
                }
            }
        }
    }

    private fun initViews() {
        viewBinding.rvCurrencies.adapter = currenciesAdapter
    }

    companion object {
        fun newInstance() = CurrencyListFragment()
    }
}