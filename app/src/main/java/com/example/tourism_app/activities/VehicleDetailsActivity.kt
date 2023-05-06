package com.example.tourism_app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.tourism_app.R

class VehicleDetailsActivity : AppCompatActivity() {

    private lateinit var tvVehiTy :TextView
    private lateinit var tvVehiDesc :TextView
    private lateinit var tvVehiFare :TextView
    private lateinit var tvVehiId :TextView

    private lateinit var btnUpdate : Button
    private lateinit var btnDelete: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_details)

        initView()
        setValuesToVeiws()
    }

    private fun initView(){
        tvVehiId = findViewById(R.id.tvVehiId)
        tvVehiTy = findViewById(R.id.tvVehiTy)
        tvVehiDesc = findViewById(R.id.tvVehiDesc)
        tvVehiFare = findViewById(R.id.tvVehiFare)

        btnUpdate = findViewById(R.id.btnVehiUpdate)
        btnDelete = findViewById(R.id.btnVehiDelete)


    }

    private fun setValuesToVeiws(){

        tvVehiId.text = intent.getStringExtra("vehId")
        tvVehiTy.text = intent.getStringExtra("vehTy")
        tvVehiDesc.text = intent.getStringExtra("vehDesc")
        tvVehiFare.text = intent.getStringExtra("vehFare")

    }

}