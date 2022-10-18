package com.valu.store.presentation.competitions.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.valu.store.data.model.ProductsResponce
import com.valu.store.databinding.ItemProductBinding
import kotlinx.coroutines.flow.*

class ProductsAdapter constructor(
   private val context: Context,private val onItemClicked: (product:ProductsResponce) -> Unit
) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    private var productItem: ArrayList<ProductsResponce> = ArrayList()


    inner class ProductViewHolder(binding: ItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        val name = binding.tvProductName
        val price = binding.tvProductPrice
        val image = binding.ivProductImage
        val container=binding.containerView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val viewBinding = ItemProductBinding.inflate(LayoutInflater.from(parent.context))
        return ProductViewHolder(viewBinding)



    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

      productItem[position].also {
            holder.name.text = it.title
            holder.price.text = it.price.toString() +" EGP"
            Glide.with(context)
                .load(it.image)
                .into(holder.image);
         val product=it
//         holder?.containerView?.setOnClickListener { clickListener(item, position) }
            holder.container.setOnClickListener{onItemClicked(product)}

     }
    }
    override fun getItemCount(): Int = productItem.size

    @SuppressLint("NotifyDataSetChanged")
    fun update(product: List<ProductsResponce>) {
        productItem.run {
            clear()
            addAll(product)
            notifyDataSetChanged()

        }
    }


}
