package com.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eat.*
import com.example.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel():ViewModel() {
    private var  categoriesLiveData = MutableLiveData<List<Category>>()
    private var randomMealLiveData = MutableLiveData<FoodList>()
    private var popularItemsLiveData = MutableLiveData<List<CategoryMeals>>()
    fun getRandomMeal() {
        RetrofitClient.api.getRandomMeal().enqueue(object : Callback<MealList> {
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if (response.body() != null) {

                    val randomMeal: FoodList = response.body()!!.meals[0]
                    randomMealLiveData.value = randomMeal

                } else {
                    return
                }
            }


            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.d("HomeFragment", t.message.toString())
            }


        })
    }

    fun getPopularItems() {
        RetrofitClient.api.getPopularItems("Seafood").enqueue(object : Callback<MealsByCategory> {
            override fun onResponse(call: Call<MealsByCategory>, response: Response<MealsByCategory>) {
                if (response.body() != null) {
                    popularItemsLiveData.value = response.body()!!.meals
//
                }
            }


            override fun onFailure(call: Call<MealsByCategory>, t: Throwable) {
                Log.d("HomeFragment", t.message.toString())
            }

        })
    }

    fun  getCategories(){
        RetrofitClient.api.getCategories().enqueue(object:Callback<CategoryList> {
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {

                    response.body()?.let { categoryList ->
                        categoriesLiveData.postValue(categoryList.categories)
                    }
                }



            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
Log.e("HomeFragment",t.message.toString())
            }

        })

    }


    fun observeRandomMealLiveData(): LiveData<FoodList> {
        return randomMealLiveData
    }

    fun observePopularItemsLiveData(): MutableLiveData<List<CategoryMeals>> {
        return popularItemsLiveData

    }
    fun  observeCategoriesLiveData():LiveData<List<Category>>{
        return categoriesLiveData
    }
}
