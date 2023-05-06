package com.example.tourism_app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tourism_app.R
import com.example.tourism_app.models.VehicleModel

class VehicleAdapter (private val vehList: ArrayList<VehicleModel>) :
    RecyclerView.Adapter<VehicleAdapter.ViewHolder>() {

    private lateinit var mListener: OnItemClickListener
    interface OnItemClickListener{
        fun onClickItem(position: Int)
    }

    fun setonItemClickListener(clickListener: OnItemClickListener){

        mListener = clickListener

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleAdapter.ViewHolder {
        val itemView =LayoutInflater.from(parent.context).inflate(R.layout. vehicle_list_item,parent,false)
        return ViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: VehicleAdapter.ViewHolder, position: Int) {
        val currentVeh =  vehList[position]
        holder.tvVehName.text = currentVeh.vehTy
    }

    override fun getItemCount(): Int {
        return vehList.size
    }

    class ViewHolder( itemView: View,clickListener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val tvVehName :TextView = itemView.findViewById(R.id.tvVehicleName)

        init{

            itemView.setOnClickListener {
                clickListener.onClickItem(adapterPosition)
            }

        }
    }



}