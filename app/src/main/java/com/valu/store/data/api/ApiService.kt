package com.valu.store.data.api

import com.valu.store.data.model.ProductsResponce
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @GET("products/")
    suspend fun getProducts(): List<ProductsResponce>
}