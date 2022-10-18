package com.valu.store.data.source.network

import com.valu.store.data.api.ApiService
import com.valu.store.data.model.ProductsResponce
import com.valu.store.data.source.EntityData
import javax.inject.Inject

class NetworkEntityData  @Inject constructor(
    private val apiService: ApiService
) : EntityData {
    override suspend fun getProducts(): List<ProductsResponce> {
        return apiService.getProducts()
    }
}
