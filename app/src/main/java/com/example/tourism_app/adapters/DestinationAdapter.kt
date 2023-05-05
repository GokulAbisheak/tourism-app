package com.example.tourism_app.adapters

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tourism_app.R
import com.example.tourism_app.models.DestinationModel

class DestinationAdapter (private val destinationList: ArrayList<DestinationModel>) : RecyclerView.Adapter<DestinationAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.destination_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentDestination = destinationList[position]
        holder.tvDestinationName.text = currentDestination.destinationName
    }

    override fun getItemCount(): Int {
        return destinationList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvDestinationName : TextView = itemView.findViewById(R.id.tvDestinationName)
    }
}