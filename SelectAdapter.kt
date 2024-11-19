package com.example.restaurantreviewapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class SelectAdapter(
    private val context: Context,
    private val restaurantList: ArrayList<Restaurant>,
    private var listener: OnItemClickListener? = null
) : RecyclerView.Adapter<SelectAdapter.RestaurantsViewHolder>() {

    inner class RestaurantsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.restaurantName)
        val location: TextView = itemView.findViewById(R.id.locationName)
        val icon: ImageView = itemView.findViewById(R.id.restaurantIcon)
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.restaurants_row_layout, parent, false)
        return RestaurantsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantsViewHolder, position: Int) {
        val data: Restaurant = restaurantList[position]
        holder.name.text = data.name
        holder.location.text = data.location

        Glide.with(context)
            .load(data.logo)
            .placeholder(R.drawable.home_icon)
            .error(R.drawable.bell)
            .into(holder.icon)

        holder.itemView.setOnClickListener {
            listener?.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return restaurantList.size
    }
}
