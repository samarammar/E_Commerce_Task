package com.valu.store.data.source

import com.valu.store.data.model.ProductsResponce

interface EntityData {
    suspend fun getProducts(): List<ProductsResponce>
}