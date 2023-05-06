package com.example.tourism_app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.tourism_app.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var redirectAddDestinationBtn: Button
    private lateinit var redirectAddVehicleBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        redirectAddDestinationBtn = findViewById(R.id.redirect_add_destination_button)
        redirectAddVehicleBtn = findViewById(R.id.redirect_add_vehicle_button)

        var dbRef: DatabaseReference = FirebaseDatabase.getInstance().getReference()
        var firebase: DatabaseReference = FirebaseDatabase.getInstance().getReference()

        redirectAddDestinationBtn.setOnClickListener {
            val intent = Intent(this, AddDestinationActivity::class.java)
            startActivity(intent)
        }

        redirectAddVehicleBtn.setOnClickListener {
            val intent2 = Intent(this, AddVehicle::class.java)
            startActivity(intent2)
        }
    }
}