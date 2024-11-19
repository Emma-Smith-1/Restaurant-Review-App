package com.example.restaurantreviewapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.restaurantreviewapp.databinding.GuestActivityMainBinding

class GuestMainActivity : AppCompatActivity() {

    private lateinit var binding: GuestActivityMainBinding

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = GuestActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.localRestaurantsButton.setOnClickListener {
            val localRestaurantsIntent = Intent(this, GuestLocalRestaurantsActivity::class.java)
            startActivity(localRestaurantsIntent)
        }

        binding.guestMainSignUpButton.setOnClickListener {
            val signUpIntent = Intent(this, SignUpActivity::class.java)
            startActivity(signUpIntent)
        }

        binding.guestMainSignInButton.setOnClickListener {
            val signInIntent = Intent(this, SignInActivity::class.java)
            startActivity(signInIntent)
        }
    }

    fun localRestaurantsClick() {
        val localRestaurantsIntent = Intent(this, GuestLocalRestaurantsActivity::class.java)
        startActivity(localRestaurantsIntent)
    }

    fun guestMainSignUpClick() {
        val signUpIntent = Intent(this, SignUpActivity::class.java)
        startActivity(signUpIntent)
    }

    fun guestMainSignInClick() {
        val signInIntent = Intent(this, SignInActivity::class.java)
        startActivity(signInIntent)
    }
}