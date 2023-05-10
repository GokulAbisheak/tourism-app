package com.example.tourism_app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.tourism_app.R
import com.example.tourism_app.models.VehicleModel
import com.google.firebase.database.FirebaseDatabase

class VehicleDetailsUserActivity : AppCompatActivity() {

    private lateinit var tvVehiTy :TextView
    private lateinit var tvVehiDesc :TextView
    private lateinit var tvVehiFare :TextView
    private lateinit var tvVehiId :TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_details_user)

        initView()
        setValuesToVeiws()

    }

    private fun initView(){

        tvVehiId = findViewById(R.id.tvVehiId)
        tvVehiTy = findViewById(R.id.tvVehiTy)
        tvVehiDesc = findViewById(R.id.tvVehiDesc)
        tvVehiFare = findViewById(R.id.tvVehiFare)

    }

    private fun setValuesToVeiws(){

        tvVehiId.text = intent.getStringExtra("vehId")
        tvVehiTy.text = intent.getStringExtra("vehTy")
        tvVehiDesc.text = intent.getStringExtra("vehDesc")
        tvVehiFare.text = intent.getStringExtra("vehFare")

    }

}