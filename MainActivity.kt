package com.example.restaurantreviewapp

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import com.example.restaurantreviewapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("myHomepage", "in onCreate")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        firebaseAuth = FirebaseAuth.getInstance()

        val mToolbar = binding.mainToolbar
        setSupportActionBar(mToolbar)

        binding.localRestaurantsButton.setOnClickListener {
            val intent = Intent(this, LocalRestaurantsActivity::class.java)
            startActivity(intent)
        }

        binding.myReviewsButton.setOnClickListener{
            val intent = Intent(this, MyReviewsActivity::class.java)
            startActivity(intent)
        }


        binding.accountSettingsButton.setOnClickListener{
            val intent = Intent(this, AccountSettingsActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate((R.menu.tool_bar_layout), menu)
        return true
    }

//functions to respond to button clicks

    fun localRestaurantsClick() {
        val restaurantsActivityIntent = Intent(this, LocalRestaurantsActivity::class.java)
        startActivity(restaurantsActivityIntent)
    }

    fun myReviewsClick() {
        val myReviewsActivityIntent = Intent(this, MyReviewsActivity::class.java)
        startActivity(myReviewsActivityIntent)
    }

    fun accountClick() {
        val accountSettingsActivityIntent = Intent(this, AccountSettingsActivity::class.java)
        startActivity(accountSettingsActivityIntent)
    }
}