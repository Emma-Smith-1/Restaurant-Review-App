package com.example.restaurantreviewapp

class MyRestaurantsModel(val image: Array<Int>, val name: String){

    var restaurantModelName: String? = null
    private var restaurantModelImage: Int = 0

    /*
    * Return the option name
    */
    fun getNames(): String {
        return restaurantModelName.toString()
    }

    /*
    * Set a option name
    */
    fun setNames(name: String) {
        this.restaurantModelName = name
    }

    fun getImages(): Int {
        return restaurantModelImage
    }

    fun setImages(imageRestaurant: Int) {
        this.restaurantModelImage = imageRestaurant
    }

}