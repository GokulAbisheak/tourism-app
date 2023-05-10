package com.example.tourism_app.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.tourism_app.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var btnDisplay2 : Button
    private lateinit var btnDisplay1 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()

        btnDisplay2= findViewById(R.id.submit)
        btnDisplay1= findViewById(R.id.fetch)


        btnDisplay2.setOnClickListener{
            val intent = Intent(this, AddLocationActivity::class.java)
        startActivity(intent)
        }

        btnDisplay1.setOnClickListener{
            val intent = Intent(this, FetchLocation::class.java)
            startActivity(intent)
        }





        val home: ImageView = findViewById(R.id.home)
        val user: ImageView = findViewById(R.id.user)
        val destination: ImageView = findViewById(R.id.destination)
        val vehicle: ImageView = findViewById(R.id.vehicle)
        val hotel: ImageView = findViewById(R.id.hotel)

        val fragment = MydetailsFragment()
        home.setOnClickListener {
            home.setImageResource(R.drawable.home)

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView, fragment)
                commit()
            }
        }
        user.setOnClickListener {
            user.setImageResource(R.drawable.user)

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView, fragment)
                commit()
            }
        }
        vehicle.setOnClickListener {
            vehicle.setImageResource(R.drawable.vehicle)

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView, fragment)
                commit()
            }
        }
        hotel.setOnClickListener {
            hotel.setImageResource(R.drawable.hotel)

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView, fragment)
                commit()
            }
        }
        destination.setOnClickListener {
            destination.setImageResource(R.drawable.destination)

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView, fragment)
                commit()
            }
        }



    }
}