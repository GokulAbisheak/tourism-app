package com.example.tourism_app.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tourism_app.R
import com.example.tourism_app.adapters.LocationAdapter
import com.example.tourism_app.models.CustomerModel
import com.google.firebase.database.*


class FetchLocation : AppCompatActivity() {

    private lateinit var myLocationView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var locationList:ArrayList<CustomerModel>
    private lateinit var dbRef:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch_location)

        myLocationView=findViewById(R.id.rvLocation)
        myLocationView.layoutManager= LinearLayoutManager(this)
        myLocationView.setHasFixedSize(true)
        tvLoadingData=findViewById(R.id.tvLoadingData)

        locationList= arrayListOf<CustomerModel>()

        getLocationData()
    }
    private fun getLocationData(){
        myLocationView.visibility= View.GONE
        tvLoadingData.visibility=View.VISIBLE

        dbRef=FirebaseDatabase.getInstance().getReference("MyDetails")
        dbRef.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                locationList.clear()
                if(snapshot.exists()){
                    for(locationSnap in snapshot.children){
                        val locationData =locationSnap.getValue(CustomerModel::class.java)
                        locationList.add(locationData!!)
                    }
                    val mAdapter=LocationAdapter(locationList)
                    myLocationView.adapter=mAdapter

                    mAdapter.setonItemClickListener(object : LocationAdapter.OnItemClickListener{
                        override fun onClickItem(position: Int) {
                            val intent = Intent(this@FetchLocation,LocationDetailsActivity::class.java)

                            intent.putExtra("CusId",locationList[position].CusId)
                            intent.putExtra("myName",locationList[position].name)
                            intent.putExtra("myPhone",locationList[position].phone)
                            intent.putExtra("myRent",locationList[position].rent)
                            intent.putExtra("myAddress",locationList[position].address)
                            startActivity(intent)
                        }

                    })

                    myLocationView.visibility=View.VISIBLE
                    tvLoadingData.visibility=View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}