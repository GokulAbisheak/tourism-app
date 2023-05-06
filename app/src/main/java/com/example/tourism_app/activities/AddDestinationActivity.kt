package com.example.tourism_app.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.tourism_app.R
import com.example.tourism_app.models.DestinationModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class AddDestinationActivity : AppCompatActivity() {

    private lateinit var destinationName: EditText
    private lateinit var destinationLocation: EditText
    private lateinit var destinationDescription: EditText
    private lateinit var destinationImageBtn: Button
    private lateinit var destinationImage: ImageView
    private lateinit var saveBtn: Button
    private lateinit var imageURL: Uri

    private lateinit var dbRef: DatabaseReference
    private lateinit var storageRef: StorageReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_destination)

        destinationName = findViewById(R.id.destination_name_input)
        destinationLocation = findViewById(R.id.destination_location_input)
        destinationDescription = findViewById(R.id.destination_description_input)
        destinationImageBtn = findViewById(R.id.destination_image_button)
        destinationImage = findViewById(R.id.destination_image)
        saveBtn = findViewById(R.id.add_destination_button)

        dbRef = FirebaseDatabase.getInstance().getReference("Destination")

        destinationImageBtn.setOnClickListener {
            selectImage()
        }

        saveBtn.setOnClickListener {
            saveDestinationData()
        }
    }

    private fun selectImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 100 && resultCode == RESULT_OK){
            imageURL = data?.data!!
            destinationImage.setImageURI(imageURL)

        }

    }

    private fun saveDestinationData() {

        //getting values
        val name = destinationName.text.toString()
        val location = destinationLocation.text.toString()
        val description = destinationDescription.text.toString()
        val url = imageURL.toString()

        if (name.isEmpty()) {
            destinationName.error = "Please Enter Destination Name"
            return
        }

        if (location.isEmpty()) {
            destinationLocation.error = "Please Enter Destination Location"
            return
        }

        if (description.isEmpty()) {
            destinationDescription.error = "Please Enter Destination Description"
            return
        }

        val formatter:SimpleDateFormat = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.CANADA);
        val now:Date = Date();
        var filename = formatter.format(now)

        storageRef = FirebaseStorage.getInstance().getReference(("images/${filename}"))

        storageRef.putFile(imageURL)

        val destinationId = dbRef.push().key!!

        val destination = DestinationModel(destinationId, name, location, description, filename)

        dbRef.child(destinationId).setValue(destination)
            .addOnCompleteListener{
                Toast.makeText(this, "Destination Added Successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}