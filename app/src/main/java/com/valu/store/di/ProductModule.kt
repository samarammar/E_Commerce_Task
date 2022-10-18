package com.valu.store.di

import com.valu.store.data.repository.RepositoryImpl
import com.valu.store.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ProductModule {

    @Binds
    abstract fun bindProductRepository(repository: RepositoryImpl): ProductRepository
}