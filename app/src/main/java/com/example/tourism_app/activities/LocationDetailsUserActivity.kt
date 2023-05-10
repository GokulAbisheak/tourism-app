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
import com.example.tourism_app.models.CustomerModel
import com.google.firebase.database.FirebaseDatabase

class LocationDetailsUserActivity : AppCompatActivity() {

    private lateinit var tvCusId: TextView
    private lateinit var tvmyName: TextView
    private lateinit var tvmyPhone: TextView
    private lateinit var tvmyRent: TextView
    private lateinit var tvmyAddress: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_details_user)

        initView()
        setValuesToViews()

    }

    private fun initView(){
        tvCusId = findViewById(R.id.tvCusId)
        tvmyName = findViewById(R.id.tv_my_name)
        tvmyPhone = findViewById(R.id.tv_my_phone)
        tvmyRent = findViewById(R.id.tv_my_Rent)
        tvmyAddress = findViewById(R.id.tv_my_Address)

    }

    private fun setValuesToViews(){

        tvCusId.text=intent.getStringExtra("CusId")
        tvmyName.text=intent.getStringExtra("myName")
        tvmyPhone.text=intent.getStringExtra("myPhone")
        tvmyRent.text=intent.getStringExtra("myRent")
        tvmyAddress.text=intent.getStringExtra("myAddress")

    }
}