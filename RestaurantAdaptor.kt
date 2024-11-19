package com.example.restaurantreviewapp

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class RestaurantAdaptor(private val context: Context, private val restaurantList: ArrayList<Restaurant>) :
    RecyclerView.Adapter<RestaurantAdaptor.RestaurantsViewHolder>() {

    inner class RestaurantsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val name : TextView = itemView.findViewById(R.id.restaurantName)
        val location : TextView = itemView.findViewById(R.id.locationName)
        val icon : ImageView = itemView.findViewById(R.id.restaurantIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.restaurants_row_layout, parent, false)
        return RestaurantsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantsViewHolder, position: Int) {
        val data : Restaurant = restaurantList[position]
        holder.name.text = data.name
        holder.location.text = data.location

        Glide.with(context)
            .load(data.logo)
            .placeholder(R.drawable.home_icon)
            .error(R.drawable.bell)
            .into(holder.icon)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, RestaurantDetailsActivity::class.java)

            intent.putExtra("name", data.name)
            intent.putExtra("logo", data.logo)
            intent.putExtra("location", data.location)
            intent.putExtra("restaurantID", data.restaurantID)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        Log.i("RestaurantAdapter", "getItemCount: ${restaurantList.size}")
        return restaurantList.size
    }
}