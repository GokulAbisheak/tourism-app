package com.example.tourism_app.activities

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tourism_app.R
import com.example.tourism_app.adapters.DestinationAdapter
import com.example.tourism_app.models.DestinationModel
import com.google.firebase.database.*

class FetchDestinationActivity : AppCompatActivity() {

    private lateinit var destinationRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var destinationList: ArrayList<DestinationModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fetch_destination)

        destinationRecyclerView = findViewById(R.id.rvDestination)
        destinationRecyclerView.layoutManager = LinearLayoutManager(this)
        destinationRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        destinationList = arrayListOf<DestinationModel>()

        getDestinationData()

    }

    private fun getDestinationData() {
        destinationRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Destination")

        dbRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                destinationList.clear()
                if(snapshot.exists()){
                    for(destinationSnap in snapshot.children) {
                        val destinationData = destinationSnap.getValue(DestinationModel::class.java)
                        destinationList.add(destinationData!!)
                    }
                    val mAdapter = DestinationAdapter(destinationList)
                    destinationRecyclerView.adapter = mAdapter

                    destinationRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}