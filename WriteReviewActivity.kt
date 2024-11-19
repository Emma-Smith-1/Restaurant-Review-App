package com.example.restaurantreviewapp


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatRatingBar
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class WriteReviewActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private var titleText: String = ""
    private var contentText: String = ""
    private var starRating: Float = 0.0f

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.review_page)
        FirebaseApp.initializeApp(this)
        Log.i("myWriteReview", "in onCreate")

        savedInstanceState?.let {
            titleText = it.getString("title", "")
            contentText = it.getString("content", "")
            starRating = it.getFloat("starRating", 0.0f)
        }

        val submitButton = findViewById<Button>(R.id.submitButton)
        submitButton.setOnClickListener {
            Log.i("myWriteReview", "submit button clicked")
            submitButtonClick()
        }
    }


    private fun submitButtonClick() {
        Log.i("myWriteReview", "in submit button")
        if (auth.currentUser != null) {
            Log.i("myWriteReview", "in user not null")
            try {
                Log.i("myWriteReview", "trying")
                val titleInput = findViewById<AppCompatEditText>(R.id.addTitle)
                val title = titleInput.text.toString()
                val contentInput = findViewById<AppCompatEditText>(R.id.writeReviewBox)
                val content = contentInput.text.toString()
                val ratingInput = findViewById<AppCompatRatingBar>(R.id.ratingBar)
                val starRating = ratingInput.rating.toDouble()

                val userID = auth.currentUser?.uid ?: ""
                val restaurantID = intent.getStringExtra("selectedRestaurantID").toString()

                val reviewData = hashMapOf(
                    "title" to title,
                    "starRating" to starRating,
                    "text" to content,
                    "userID" to userID,
                    "restaurantID" to restaurantID
                )

                db.collection("reviews")
                    .add(reviewData)
                    .addOnSuccessListener { documentReference ->
                        Log.i(
                            "myWriteReview",
                            "DocumentSnapshot added with ID: ${documentReference.id}"
                        )
                        val newIntent = Intent(this, MainActivity::class.java)
                        startActivity(newIntent)
                    }
                    .addOnFailureListener { e ->
                        Log.i("myWriteReview", "Error adding document", e)
                    }
            } catch (e: Exception) {
                Log.i("myWriteReview", "null input")
            }
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("title", titleText)
        outState.putString("content", contentText)
        outState.putFloat("starRating", starRating)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Restore saved data
        titleText = savedInstanceState.getString("title", "")
        contentText = savedInstanceState.getString("content", "")
        starRating = savedInstanceState.getFloat("starRating", 0.0f)
    }
}
