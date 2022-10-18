package com.valu.store.presentation.products

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.valu.store.data.model.ProductsResponce
import com.valu.store.databinding.ActivityProductsBinding
import com.valu.store.presentation.base.BaseActivity
import com.valu.store.presentation.competitions.adapter.ProductsAdapter
import com.valu.store.presentation.productDetails.ProductDetailsActivity
import com.valu.store.util.Constants.PRODUCTS_OBJ
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class ProductsActivity  :
    BaseActivity<ActivityProductsBinding>()
{
        override val bindLayout: (LayoutInflater) -> ActivityProductsBinding
        get() = ActivityProductsBinding::inflate
        private lateinit var viewModel: ProductsListViewModel
    private lateinit var adaptercompet: ProductsAdapter
        private fun initUi() {
        binding.rvProducts.run {
            layoutManager = GridLayoutManager(context, 2);
            setHasFixedSize(true)
            adapter = adaptercompet
        }
    }
    private fun onListItemClick(product:ProductsResponce) {
        val intent = Intent(
            this,
            ProductDetailsActivity::class.java
        )

        intent.putExtra(PRODUCTS_OBJ, product)
        startActivity(intent)
    }
        override fun prepareView() {
        viewModel = ViewModelProvider(this).get(ProductsListViewModel::class.java)
        viewModel.getProfuctsList()
        adaptercompet = ProductsAdapter(this){ code -> onListItemClick(code) }


            initUi()
        fetchCompetitions()
    }
    private fun fetchCompetitions() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is ProductsListViewModel.ProductsUiState.Loaded -> onLoaded(state.itemState)

                        is ProductsListViewModel.ProductsUiState.Error -> showError(state.message)
                        is ProductsListViewModel.ProductsUiState.Loading -> showLoading()
                    }
                }
            }
        }
    }
    private suspend fun onLoaded(productuistate: ProductsListUiState) {
        Timber.d("showerror")

        productuistate.run {
            val flowOfLists: Flow<List<ProductsResponce>> = products
            val flatList: List<ProductsResponce> = flowOfLists.flattenToList()
            adaptercompet.update(flatList)


        }
    }



    suspend fun <T> Flow<List<T>>.flattenToList() =
        flatMapConcat { it.asFlow() }.toList()

    private fun showLoading() {
        Timber.d("showLoading")
    }

    private fun showError(@StringRes stringRes: Int) {

        Toast.makeText(baseContext, stringRes, Toast.LENGTH_SHORT).show()
    }


}