package com.valu.store.presentation.products

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valu.store.data.networking.CoroutineDispatcherProvider
import com.valu.store.domain.usecase.ProductUsecase
import com.valu.store.util.ExceptionParser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ProductsListViewModel  @Inject
constructor(
    private val productUsecase: ProductUsecase,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider
) : ViewModel() {



    private val _uiState = MutableStateFlow<ProductsUiState>(ProductsUiState.Loading)
    val uiState: StateFlow<ProductsUiState> = _uiState


    fun getProfuctsList() {
        _uiState.value = ProductsUiState.Loading

        viewModelScope.launch(coroutineDispatcherProvider.IO()) {
            try {

                val result = productUsecase.execute()

                _uiState.value = ProductsUiState.Loaded(ProductsListUiState(result))
            } catch (error: Exception) {
                _uiState.value = ProductsUiState.Error(ExceptionParser.getMessage(error))
            }
        }
    }

    sealed class ProductsUiState {
        object Loading : ProductsUiState()
        class Loaded(val itemState: ProductsListUiState) : ProductsUiState()
        class Error(@StringRes val message: Int) : ProductsUiState()
    }
}


