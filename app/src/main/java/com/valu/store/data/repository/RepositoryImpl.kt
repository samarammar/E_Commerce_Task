package com.valu.store.data.repository

import com.glamera.football.util.Source
import com.valu.store.data.factory.ProductFactory
import com.valu.store.data.model.ProductsResponce
import com.valu.store.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val factory: ProductFactory

) : ProductRepository {

    override suspend fun getProducts(): Flow<List<ProductsResponce>>{
        return flow{
            emit( factory.create(Source.NETWORK).getProducts())
        }.flowOn(Dispatchers.IO)
    }
}