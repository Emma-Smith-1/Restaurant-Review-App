package com.example.restaurantreviewapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.restaurantreviewapp.databinding.ActivityRestaurantDetailsBinding
import com.google.firebase.firestore.FirebaseFirestore

class RestaurantDetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRestaurantDetailsBinding
    private val reviewList = ArrayList<Review>()
    private lateinit var reviewAdapter: ReviewAdapter

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name: String? = intent.getStringExtra("name")
        val logo: String? = intent.getStringExtra("logo")
        val location: String? = intent.getStringExtra("location")

        val restaurantID: String? = intent.getStringExtra("selectedRestaurantID")

        if (name != null && logo != null && location != null) {
            populateDetails(name, logo, location)
            fetchReviewsFromFirestore()
        }

        binding.btnAddReview.setOnClickListener {
            val intent = Intent(this, WriteReviewActivity::class.java)
            startActivity(intent)
        }
        Log.i("myDetailsActivity", "in onCreate")
    }

    private fun populateDetails(name: String, logo: String, location: String) {
        Glide.with(this)
            .load(logo)
            .placeholder(R.drawable.home_icon)
            .error(R.drawable.bell)
            .into(binding.imageViewLogo)

        binding.textViewName.text = name
        binding.textViewLocation.text = location

        reviewAdapter = ReviewAdapter(this, reviewList)
        binding.recyclerViewReviews.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewReviews.adapter = reviewAdapter
    }

    private fun fetchReviewsFromFirestore() {
        val db = FirebaseFirestore.getInstance()

        val restaurantID: String? = intent.getStringExtra("restaurantID")

        db.collection("reviews")
            .whereEqualTo("restaurantID", restaurantID)
            .get()
            .addOnSuccessListener { result ->
                val reviews = ArrayList<Review>()

                for (document in result) {
                    val title = document.getString("title") ?: ""
                    val starRating = document.getDouble("starRating")?.toFloat() ?: 0.0f
                    val text = document.getString("text") ?: ""
                    val userID = document.getString("userID") ?: ""
                    val restaurantID = document.getString("restaurantID") ?: ""

                    val review = Review(text, starRating, userID, title, restaurantID)
                    reviews.add(review)
                }
                updateUIWithReviews(reviews)
            }
            .addOnFailureListener { exception ->
                Log.i("Firestore", "Error getting reviews", exception)
            }
    }

    private fun updateUIWithReviews(reviews: ArrayList<Review>) {
        reviewList.clear()
        reviewList.addAll(reviews)
        reviewAdapter.notifyDataSetChanged()
    }

    fun writeReviewDetailsClick() {
        val selectedRestaurantID: String = intent.getStringExtra("restaurantID") ?: ""
        val writeReviewActivityIntent = Intent(this, WriteReviewActivity::class.java)
        writeReviewActivityIntent.putExtra("restaurantID", selectedRestaurantID)
        Log.i("myDetailsActivity", selectedRestaurantID)
        startActivity(writeReviewActivityIntent)
    }
}