package com.example.tourism_app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.tourism_app.R
import com.example.tourism_app.models.DestinationModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddDestinationActivity : AppCompatActivity() {

    private lateinit var destinationName: EditText
    private lateinit var destinationLocation: EditText
    private lateinit var destinationDescription: EditText
    //private lateinit var destinationImage: EditText
    private lateinit var saveBtn: Button

    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_destination)

        destinationName = findViewById(R.id.destination_name_input)
        destinationLocation = findViewById(R.id.destination_location_input)
        destinationDescription = findViewById(R.id.destination_description_input)
        //destinationImg = findViewById(R.id.destination_image_input)
        saveBtn = findViewById(R.id.add_destination_button)

        dbRef = FirebaseDatabase.getInstance().getReference("Destination")

        saveBtn.setOnClickListener {
            saveDestinationData()
        }
    }

    private fun saveDestinationData() {

        //getting values
        val name = destinationName.text.toString()
        val location = destinationLocation.text.toString()
        val description = destinationDescription.text.toString()

        if (name.isEmpty()) {
            destinationName.error = "Please Enter Destination Name"
        }

        if (location.isEmpty()) {
            destinationLocation.error = "Please Enter Destination Location"
        }

        if (description.isEmpty()) {
            destinationDescription.error = "Please Enter Destination Description"
        }

        val empId = dbRef.push().key!!

        val destination = DestinationModel(name, location, description)

        dbRef.child(empId).setValue(destination)
            .addOnCompleteListener{
                Toast.makeText(this, "Destination Added Successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}