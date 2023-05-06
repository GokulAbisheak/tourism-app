package com.example.tourism_app.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tourism_app.R

class DestinationDetailsActivity: AppCompatActivity() {

    private lateinit var tvDestinationId: TextView
    private lateinit var tvDestinationName: TextView
    private lateinit var tvDestinationLocation: TextView
    private lateinit var tvDestinationDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.destination_details)

        initView()
        setValuesToViews()
    }

    private fun initView() {
        tvDestinationName = findViewById(R.id.tv_destination_name)
        tvDestinationLocation = findViewById(R.id.tv_destination_location)
        tvDestinationDescription = findViewById(R.id.tv_destination_description)
    }

    private fun setValuesToViews() {
        tvDestinationName.text = intent.getStringExtra("destinationName")
        tvDestinationLocation.text = intent.getStringExtra("destinationLocation")
        tvDestinationDescription.text = intent.getStringExtra("destinationDescription")
    }
}