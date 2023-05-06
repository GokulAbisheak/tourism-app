package com.example.tourism_app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tourism_app.R
import com.example.tourism_app.models.VehicleModel
import com.example.tourism_app.adapters.VehicleAdapter
import com.google.firebase.database.*

class FetchVehicleActivity : AppCompatActivity() {
    private lateinit var vehRecyclerVeiw : RecyclerView
    private lateinit var tvLoadingData : TextView
    private lateinit var vehList: ArrayList<VehicleModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch_vehicle)

        vehRecyclerVeiw = findViewById(R.id.rvVehicle)
        vehRecyclerVeiw.layoutManager = LinearLayoutManager(this)
        vehRecyclerVeiw.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingVehData)

        vehList = arrayListOf<VehicleModel>()

        getVehData()


    }

    private fun getVehData() {

        vehRecyclerVeiw.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Vehicles")

        dbRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                vehList.clear()
                if(snapshot.exists()){
                    for(vehSnap in snapshot.children) {
                        val vehData = vehSnap.getValue(VehicleModel::class.java)
                        vehList.add(vehData!!)
                    }
                    val mAdapter = VehicleAdapter(vehList)
                    vehRecyclerVeiw.adapter = mAdapter

                    mAdapter.setonItemClickListener(object : VehicleAdapter.OnItemClickListener{
                        override fun onClickItem(position: Int) {
                            val intent = Intent(this@FetchVehicleActivity,VehicleDetailsActivity::class.java)

                            intent.putExtra("vehId",vehList[position].vehId)
                            intent.putExtra("vehTy",vehList[position].vehTy)
                            intent.putExtra("vehAvail",vehList[position].vehAvail)
                            intent.putExtra("vehDesc",vehList[position].vehDesc)
                            intent.putExtra("vehFare",vehList[position].vehFare)

                            startActivity(intent)
                        }

                    })

                    vehRecyclerVeiw.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

}
