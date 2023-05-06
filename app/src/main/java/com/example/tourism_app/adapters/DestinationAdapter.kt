package com.example.tourism_app.adapters

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tourism_app.R
import com.example.tourism_app.models.DestinationModel
import com.google.firebase.storage.FirebaseStorage
import java.io.File

class DestinationAdapter (private val destinationList: ArrayList<DestinationModel>) : RecyclerView.Adapter<DestinationAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener
    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.destination_list_item, parent, false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentDestination = destinationList[position]
        holder.tvDestinationName.text = currentDestination.destinationName

        val filename = currentDestination.destinationImg
        val storageRef = FirebaseStorage.getInstance().reference.child("images/${filename}")
        val localfile = File.createTempFile("tempImage", "jpg")
        storageRef.getFile(localfile).addOnSuccessListener {

            val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
            holder.imgIcon.setImageBitmap(bitmap)
        }



    }

    override fun getItemCount(): Int {
        return destinationList.size
    }

    class ViewHolder(itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val tvDestinationName : TextView = itemView.findViewById(R.id.tvDestinationName)
        val imgIcon : ImageView = itemView.findViewById(R.id.destination_icon)

        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }
}