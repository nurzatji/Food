package com.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eat.FoodList
import com.example.eat.MealList
import com.example.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealViewModel():ViewModel() {
    private var mealDetailsLiveData = MutableLiveData<FoodList>()

    fun getMealDetail(id: String) {
        RetrofitClient.api.getMealDetails(id).enqueue(object : Callback<MealList> {
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
if (response.body()!= null){
    mealDetailsLiveData.value = response.body()!!.meals[0]
}else{
    return
}
            }


            override fun onFailure(call: Call<MealList>, t: Throwable) {
Log.d("MealActivity",t.message.toString())
            }


        })
    }
    fun observerMealDetailsLiveData(): MutableLiveData<FoodList> {
      return mealDetailsLiveData

    }
}



