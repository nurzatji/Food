package com.example.retrofit

import com.example.eat.CategoryList
import com.example.eat.MealsByCategory
import com.example.eat.MealList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("random.php")
    fun  getRandomMeal(): Call<MealList>
    @GET("lookup.php?")
    fun getMealDetails(@Query("i") id:String) : Call<MealList>

    @GET("filter.php?")
    fun getPopularItems(@Query("c") categoryName:String):Call<MealsByCategory>

    @GET("categories.php")
    fun getCategories():Call<CategoryList>
}
//lookup.php?i=