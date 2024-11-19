package com.example.restaurantreviewapp

data class Restaurant(val logo : String ?= null, val name: String ?= null, val location: String ?= null, val restaurantID: String ?= null) {
    constructor():this ("", "", "", "")
}
