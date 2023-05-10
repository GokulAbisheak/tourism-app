package com.example.tourism_app.activities

import android.content.Context
import android.content.Intent
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

                    mAdapter.setOnItemClickListener(object: DestinationAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {

                            // Get an instance of SharedPreferences
                            val sharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)

                            // Retrieve the email from SharedPreferences
                            val email = sharedPreferences.getString("email", "")
                            val intent: Intent

                            if(email.toString() == "admin@app.com"){
                                intent = Intent(this@FetchDestinationActivity, DestinationDetailsActivity::class.java)
                            } else {
                                intent = Intent(this@FetchDestinationActivity, DestinationDetailsUserActivity::class.java)
                            }


                            intent.putExtra("destinationId", destinationList[position].destinationId)
                            intent.putExtra("destinationName", destinationList[position].destinationName)
                            intent.putExtra("destinationLocation", destinationList[position].destinationLocation)
                            intent.putExtra("destinationDescription", destinationList[position].destinationDescription)
                            intent.putExtra("destinationImg", destinationList[position].destinationImg)
                            startActivity(intent)
                        }

                    })

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