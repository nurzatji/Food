package com.example.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eat.CategoryMeals
import com.example.food.databinding.ItemsPopularBinding

class MostPopularAdapter():RecyclerView.Adapter<MostPopularAdapter.MealPopularViewHolder>() {
private var mealsList =  ArrayList<CategoryMeals>()
 lateinit var onItemClick : ((CategoryMeals) -> Unit)
    fun setMeals(mealsList:ArrayList<CategoryMeals>){
        this.mealsList = mealsList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealPopularViewHolder {
return MealPopularViewHolder(ItemsPopularBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MealPopularViewHolder, position: Int) {
Glide.with(holder.itemView).load(mealsList[position].strMealThumb).into(holder.binding.imgPopularItem)

        holder.itemView.setOnClickListener {
            onItemClick.invoke(mealsList[position])
        }
    }

    override fun getItemCount(): Int {
return  mealsList.size

    }

class MealPopularViewHolder (  var binding: ItemsPopularBinding):RecyclerView.ViewHolder(binding.root)

}
