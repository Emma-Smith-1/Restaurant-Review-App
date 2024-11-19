package com.example.restaurantreviewapp

data class Review(
    val text: String,
    val starRating: Float,
    val userID: String,
    val title: String,
    val restaurantID: String
)