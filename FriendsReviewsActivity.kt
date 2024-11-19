package com.example.restaurantreviewapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class FriendsReviewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.friends_reviews_page)
        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState)
        }
        Log.i("friendsReviewsActivity", "in onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.i("friendsReviewsActivity", "in onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("friendsReviewsActivity", "in onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("friendsReviewsActivity", "in onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("friendsReviewsActivity", "in onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("friendsReviewsActivity", "in onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("exampleKey", "exampleValue")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        if (savedInstanceState != null) {
            val text = savedInstanceState.putString("examplekey", "exampleValue")
        }
        super.onRestoreInstanceState(savedInstanceState)
    }
}