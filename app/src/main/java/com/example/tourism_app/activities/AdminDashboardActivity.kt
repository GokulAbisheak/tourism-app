package com.example.tourism_app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.tourism_app.R


class AdminDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_dashboard)

        // Initialize buttons
        val destinationManagementButton = findViewById<Button>(R.id.destinationManagementButton)
        val vehicleManagementButton = findViewById<Button>(R.id.vehicleManagementButton)
        val accommodationManagementButton = findViewById<Button>(R.id.accommodationManagementButton)

        // Set click listeners for buttons
        destinationManagementButton.setOnClickListener {
            val intent = Intent(this, AddDestinationActivity::class.java)
            startActivity(intent)
        }

        vehicleManagementButton.setOnClickListener {
            val intent = Intent(this, AddVehicle::class.java)
            startActivity(intent)
        }
        }
    }
