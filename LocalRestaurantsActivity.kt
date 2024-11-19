package com.example.restaurantreviewapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot


class LocalRestaurantsActivity : AppCompatActivity() {

    private lateinit var recyclerView : RecyclerView
    private lateinit var restaurantsArrayList : ArrayList<Restaurant>
    private lateinit var restaurantAdapter : RestaurantAdaptor
    private lateinit var db : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.local_restaurants_page)

        recyclerView = findViewById<RecyclerView>(R.id.localRestaurantsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        restaurantsArrayList = arrayListOf()

        restaurantAdapter = RestaurantAdaptor(this, restaurantsArrayList)
        recyclerView.adapter = restaurantAdapter

        eventChangeListener()

        Log.i("myRestaurants", "in onCreate")
    }

    private fun eventChangeListener() {
        db = FirebaseFirestore.getInstance()
        db.collection("restaurants").orderBy("name", Query.Direction.ASCENDING)
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {

                if (error != null) {
                    Log.i("Firestore error", error.message.toString())
                    return
                }

                for (dc : DocumentChange in value?.documentChanges!!) {
                    if (dc.type == DocumentChange.Type.ADDED) {
                        restaurantsArrayList.add(dc.document.toObject(Restaurant::class.java))
                    }
                }
                restaurantAdapter.notifyDataSetChanged()
            }
        })
    }
}