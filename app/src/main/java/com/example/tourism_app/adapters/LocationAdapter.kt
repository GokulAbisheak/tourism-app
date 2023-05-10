package com.example.touristregister.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.touristregister.R
import com.example.touristregister.models.CustomerModel

class LocationAdapter (private val locationList:ArrayList<CustomerModel>):
    RecyclerView.Adapter<LocationAdapter.ViewHolder>(){

    private lateinit var mListener: OnItemClickListener
    interface OnItemClickListener{
        fun onClickItem(position: Int)
    }
    fun setonItemClickListener(clickListener: OnItemClickListener){

        mListener = clickListener

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationAdapter.ViewHolder {
        val itemView =LayoutInflater.from(parent.context).inflate(R.layout. location_list_item,parent,false)
        return ViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: LocationAdapter.ViewHolder, position: Int) {
        val currentVeh =  locationList[position]
        holder.tvmyName.text = currentVeh.name
    }

    override fun getItemCount(): Int {
        return locationList.size
    }
    class ViewHolder(itemView: View, clickListener: OnItemClickListener): RecyclerView.ViewHolder(itemView) {

        val tvmyName : TextView =itemView.findViewById(R.id.tvLocationName)
        init{
            itemView.setOnClickListener {
                clickListener.onClickItem(adapterPosition)
            }
        }

    }



}