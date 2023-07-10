package com.example.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eat.Category
import com.example.food.databinding.ItemCategoriesBinding

class CategoriesAdapter():RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> (){
    private var  categoriesList = ArrayList<Category>()
    var onItemClick: ((Category) ->Unit)? = null
    fun  setCategoryList(categoriesList:List<Category>){
        this.categoriesList = categoriesList as ArrayList<Category>
        notifyDataSetChanged()
    }
    class CategoryViewHolder( val binding: ItemCategoriesBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
return  CategoryViewHolder(ItemCategoriesBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return  categoriesList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
       Glide.with(holder.itemView).load(categoriesList[position].strCategoryThumb).into(holder.binding.imgCategory)
        holder.binding.tvCategoryName.text = categoriesList[position].strCategory
        holder.itemView.setOnClickListener{
            onItemClick!!.invoke(categoriesList[position])
        }
    }
}