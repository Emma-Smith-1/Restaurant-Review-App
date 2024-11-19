package com.example.restaurantreviewapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReviewAdapter(private val context: Context, private var reviewList: ArrayList<Review>) :
    RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val reviewText: TextView = itemView.findViewById(R.id.textViewReview)
        val ratingBar: RatingBar = itemView.findViewById(R.id.ratingBar)
        val userID: TextView = itemView.findViewById(R.id.textViewUserID)
        val title: TextView = itemView.findViewById(R.id.textViewReviewTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.review_item_layout, parent, false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = reviewList[position]
        holder.reviewText.text = review.text
        holder.ratingBar.rating = review.starRating
        holder.userID.text = "User ID: ${review.userID}"
        holder.title.text = review.title
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    fun setReviewList(reviews: ArrayList<Review>) {
        this.reviewList = reviews
        notifyDataSetChanged()
    }
}
