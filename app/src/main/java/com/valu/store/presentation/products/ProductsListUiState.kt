package com.valu.store.presentation.products

import com.valu.store.data.model.ProductsResponce
import com.valu.store.domain.entity.Rating
import kotlinx.coroutines.flow.Flow

data class ProductsListUiState (
    val products: Flow<List<ProductsResponce>>
)