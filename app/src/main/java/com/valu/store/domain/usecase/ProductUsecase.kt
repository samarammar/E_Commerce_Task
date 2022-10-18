package com.valu.store.domain.usecase

import com.valu.store.data.model.ProductsResponce
import com.valu.store.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductUsecase @Inject constructor(
    private val productRepository: ProductRepository
) {

    suspend fun execute(): Flow<List<ProductsResponce>> {
        return productRepository.getProducts()
    }

}