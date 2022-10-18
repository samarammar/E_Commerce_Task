package com.valu.store.domain.repository

import com.valu.store.data.model.ProductsResponce
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProducts(): Flow<List<ProductsResponce>>
}