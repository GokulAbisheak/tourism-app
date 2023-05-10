package com.example.tourism_app.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tourism_app.R
import com.example.tourism_app.models.CustomerModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddLocationActivity : AppCompatActivity() {

    private lateinit var etmyName : EditText
    private lateinit var etmyPhone : EditText
    private lateinit var etmyRent : EditText
    private lateinit var etmyAddress : EditText
    private lateinit var butnSave : Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_location)

        etmyName =findViewById(R.id.myName)
        etmyPhone =findViewById(R.id.myPhone)
        etmyRent =findViewById(R.id.myRent)
        etmyAddress =findViewById(R.id.myAddress)
        butnSave =findViewById(R.id.btnSave)

        dbRef = FirebaseDatabase.getInstance().getReference("MyDetails")

        butnSave.setOnClickListener{
            saveMyDetails()
        }
    }
    private fun saveMyDetails(){
        val name= etmyName.text.toString()
        val phone= etmyPhone.text.toString()
        val rent= etmyRent.text.toString()
        val address= etmyAddress.text.toString()

        if(name.isEmpty()){
          etmyName.error="Please enter Hotel name"

        }
        if(phone.isEmpty()){
            etmyPhone.error="Please enter contact No"

        }
        if(rent.isEmpty()){
            etmyRent.error="Please enter the rent for a night"

        }
        if(address.isEmpty()){
            etmyAddress.error="Please enter the address"

        }
        val CusId = dbRef.push().key!!

        val customer = CustomerModel(CusId, name, phone, rent,address)
        dbRef.child(CusId).setValue(customer)
            .addOnCompleteListener {

                etmyName.text.clear()
                etmyPhone.text.clear()
                etmyRent.text.clear()
                etmyAddress.text.clear()



                Toast.makeText(this, "Details inserted successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener {err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }

    }