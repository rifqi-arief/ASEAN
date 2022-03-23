package com.example.test.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.test.R
import com.example.test.databinding.ItemProductBinding
import com.example.test.domain.entity.response.ProductTable
import com.example.test.utils.Constants
import com.example.test.utils.Converter.toRupiah

class ProductAdapter (private val productList: List<ProductTable>, val onSelect: (ProductTable?) -> Unit): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

     class ViewHolder(private val binding : ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
         fun bind(product : ProductTable, onSelect: (ProductTable?) -> Unit){
             with(binding){
                 tvItemProductName.text = product.name
                 tvItemProductDate.text = product.date
                 tvItemProductPrice.text = product.price.toRupiah()
                 if (!product.image.isNullOrEmpty()) {
                     Glide.with(itemView.context)
                         .load("${Constants.BASE_URL}${Constants.IMAGE_URL}${product.image}")
                         .apply(
                             RequestOptions.placeholderOf(R.drawable.ic_loading)
                                 .error(R.drawable.ic_error)
                         )
                         .into(ivItemProduct)
                 }
                 root.setOnClickListener {
                     onSelect(product)
                 }
             }
         }
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemsBinding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductAdapter.ViewHolder(itemsBinding)
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val land = productList[position]
        holder.bind(land, onSelect)
    }

}