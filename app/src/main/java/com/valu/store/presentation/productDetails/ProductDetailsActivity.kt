package com.valu.store.presentation.productDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.valu.store.R
import com.valu.store.data.model.ProductsResponce
import com.valu.store.databinding.ActivityProductDetailsBinding
import com.valu.store.databinding.ActivityProductsBinding
import com.valu.store.presentation.base.BaseActivity
import com.valu.store.util.Constants.PRODUCTS_OBJ
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsActivity :
    BaseActivity<ActivityProductDetailsBinding>()
{
    override val bindLayout: (LayoutInflater) -> ActivityProductDetailsBinding
        get() = ActivityProductDetailsBinding::inflate

    lateinit var productdetails:ProductsResponce
    override fun prepareView() {
      productdetails= intent.extras?.getSerializable(PRODUCTS_OBJ) as ProductsResponce
        initUi()
    }
    private fun initUi() {
        binding.tvProductNameDt.text=productdetails.title
        binding.tvProductPriceDt.text=productdetails.price.toString() +" EGP"
        binding.tvProductDescDtValue.text=productdetails.description
        binding.rateProduct.rating=productdetails.rating.rate
        Glide.with(this)
            .load(productdetails.image)
            .into(binding.ivProductPicDt);

    }
}

