package com.example.activites

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.eat.FoodList
import com.example.food.R
import com.example.food.databinding.ActivityMealBinding
import com.example.fragments.HomeFragment
import com.viewModel.MealViewModel

class MealActivity : AppCompatActivity() {
    private lateinit var mealMvvm:MealViewModel
    private  lateinit var  mealThumb:String
    private lateinit var  mealId:String
    private lateinit var mealName:String
    private lateinit var youTubeLink:String
    private  lateinit var  binding:ActivityMealBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mealMvvm = ViewModelProvider(this)[MealViewModel::class.java]

        getMealInformationFromIntent()

        setInformationInViews()
        loadingCase()
        mealMvvm.getMealDetail(mealId)
        observerMealDetailsLiveData()
        onYouTubeImageClick()
    }

    private fun onYouTubeImageClick() {
        binding.imgYoutube.setOnClickListener{
            val  intent = Intent(Intent.ACTION_VIEW, Uri.parse(youTubeLink))
            startActivity(intent)
        }
    }

    private fun observerMealDetailsLiveData() {
       mealMvvm.observerMealDetailsLiveData().observe(this,object: Observer<FoodList>{
           override fun onChanged(value: FoodList) {
               onResponseCase()
             val meal = value
               binding.tvCategory.text = "Category: ${meal!!.strCategory}"
               binding.tvArea.text = "Area:${meal.strArea}"
               binding.tvInctructor.text = "${meal.strInstructions}"
               youTubeLink = meal.strYoutube!!
           }

       } )
    }

    private fun setInformationInViews() {
       Glide.with(applicationContext).load(mealThumb).into(binding.imgDetail)
        binding.coolapsingToolBar.title = mealName
        binding.coolapsingToolBar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.coolapsingToolBar.setExpandedTitleColor(resources.getColor(R.color.white))
    }

    private fun getMealInformationFromIntent() {
        val intent = intent
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!
    }
    private  fun  loadingCase(){
binding.btnAddToFav.visibility = View.VISIBLE
binding.tvInctructor.visibility = View.INVISIBLE
binding.tvCategory.visibility = View.INVISIBLE
binding.tvArea.visibility = View.INVISIBLE
        binding.imgYoutube.visibility = View.INVISIBLE

    }
    private fun  onResponseCase(){
        binding.btnAddToFav.visibility = View.INVISIBLE
        binding.tvInctructor.visibility = View.VISIBLE
        binding.tvCategory.visibility = View.VISIBLE
        binding.tvArea.visibility = View.VISIBLE
        binding.imgYoutube.visibility = View.VISIBLE
    }
}