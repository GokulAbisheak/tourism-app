package com.example.tourism_app.activities

import com.example.tourism_app.R
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var homeButton: ImageButton
    private lateinit var destinationButton: ImageButton
    private lateinit var vehicleButton: ImageButton
    private lateinit var hotelButton: ImageButton
    private lateinit var userButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        destinationButton = findViewById(R.id.destination_nav)
        vehicleButton = findViewById(R.id.vehicle_nav)
        hotelButton = findViewById(R.id.hotel_nav)
        userButton = findViewById(R.id.user_nav)

//        homeButton?.let { button ->
//            button.setOnClickListener {
//                val intent = Intent(this, HomeActivity::class.java)
//                startActivity(intent)
//            }
//        }

//        homeButton.setOnClickListener {
//            val intent = Intent(this, HomeActivity::class.java)
//            startActivity(intent)
//        }

        destinationButton.setOnClickListener {
            val intent = Intent(this, FetchDestinationActivity::class.java)
            startActivity(intent)
        }

//        vehicleButton.setOnClickListener {
//            val intent = Intent(this, VehicleActivity::class.java)
//            startActivity(intent)
//        }

        hotelButton.setOnClickListener {
            val intent = Intent(this, FetchLocation::class.java)
            startActivity(intent)
        }

//        userButton.setOnClickListener {
//            val intent = Intent(this, UserActivity::class.java)
//            startActivity(intent)
//        }
    }
}
