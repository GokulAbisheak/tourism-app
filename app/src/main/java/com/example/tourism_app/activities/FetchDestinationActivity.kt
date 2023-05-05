package com.example.tourism_app.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tourism_app.R

class FetchDestinationActivity : AppCompatActivity() {

    private lateinit var destinationRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fetch_destination)

        destinationRecyclerView = findViewById(R.id.rvDestination)
        destinationRecyclerView.layoutManager = LinearLayoutManager(this)
        destinationRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

    }
}