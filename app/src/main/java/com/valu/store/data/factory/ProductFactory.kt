package com.valu.store.data.factory

import com.glamera.football.util.Source
import com.valu.store.data.source.EntityData
import com.valu.store.data.source.network.NetworkEntityData
import javax.inject.Inject

class ProductFactory  @Inject constructor(
    private val networkEntityData: NetworkEntityData
) {
    fun create(source: Source): EntityData {
        return networkEntityData

    }
}