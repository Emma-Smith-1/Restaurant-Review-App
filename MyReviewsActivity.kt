package com.example.restaurantreviewapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MyReviewsActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var reviewAdapter: ReviewAdapter
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_reviews_activity)

        recyclerView = findViewById(R.id.recyclerViewReviews)
        recyclerView.layoutManager = LinearLayoutManager(this)

        reviewAdapter = ReviewAdapter(this, ArrayList())
        recyclerView.adapter = reviewAdapter

        fetchReviewsFromFirestore()


        Log.i("myReviewsActivity", "in onCreate")
    }

    private fun fetchReviewsFromFirestore() {
        val db = FirebaseFirestore.getInstance()

        auth = FirebaseAuth.getInstance()
        val currentUserID = auth.currentUser?.uid ?: ""

        db.collection("reviews")
            .whereEqualTo("userID", currentUserID)
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
        reviewAdapter.setReviewList(reviews)
        reviewAdapter.notifyDataSetChanged()
    }

}