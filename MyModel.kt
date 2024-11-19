package com.example.restaurantreviewapp
/*
 * Data model class to store list of account options
 */
class MySettingsModel {
    var modelName: String? = null
    private var modelOption: Int = 0

    /*
    * Return the option name
    */
    fun getNames(): String {
        return modelName.toString()
    }

    /*
    * Set a option name
    */
    fun setNames(name: String) {
        this.modelName = name
    }
}