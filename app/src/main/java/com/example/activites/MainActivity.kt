package com.example.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation

import androidx.navigation.ui.NavigationUI
import com.example.food.R
import com.example.food.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
      initListener()


      }
    private fun initListener() {
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.btn_nav)
        val  navController = Navigation.findNavController(this, R.id.hots_fragment)
        NavigationUI.setupWithNavController(bottomNavigation,navController)

    }
}