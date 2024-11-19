package com.example.restaurantreviewapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MyAdapter (private val optionModelArrayList: MutableList<MySettingsModel>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    /*
     * Inflate our views using the layout defined in row_layout.xml
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.row_layout, parent, false)

        return ViewHolder(v)
    }

    /*
     * Bind the data to the child views of the ViewHolder
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = optionModelArrayList[position]
        holder.txtMsg.text = item.getNames()

        /*holder.itemView.setOnClickListener {
            when (item.identifier) {
                3 -> {
                    showChangePasswordDialog(holder.itemView)
                }
            }
        }*/
    }

    /*
     * Get the maximum size of the list
     */
    override fun getItemCount(): Int {
        return optionModelArrayList.size
    }

    /*
     * The parent class that handles layout inflation and child view use
     */
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        var txtMsg = itemView.findViewById<View>(R.id.firstLine) as TextView

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val msg = txtMsg.text
            val snackbar = Snackbar.make(v, "$msg", Snackbar.LENGTH_LONG)
            snackbar.show()
        }
    }
}
