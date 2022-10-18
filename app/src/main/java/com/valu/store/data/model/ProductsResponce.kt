package com.valu.store.data.model

import com.valu.store.domain.entity.Rating
import java.io.Serializable

data class ProductsResponce (val id:Int,val title:String, val price:Double, val description:String,
val category:String,
val image:String,
val rating: Rating
):Serializable
