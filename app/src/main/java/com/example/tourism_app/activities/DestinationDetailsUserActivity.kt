package com.example.tourism_app.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.tourism_app.R
import com.example.tourism_app.models.DestinationModel
import com.google.firebase.database.FirebaseDatabase
import android.graphics.BitmapFactory
import com.google.firebase.storage.FirebaseStorage
import java.io.File

class DestinationDetailsUserActivity: AppCompatActivity() {

    private lateinit var tvDestinationId: TextView
    private lateinit var tvDestinationName: TextView
    private lateinit var tvDestinationLocation: TextView
    private lateinit var tvDestinationDescription: TextView
    private lateinit var ivDestinationImage: ImageView
    private lateinit var destinationImg: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.destination_details_user)

        initView()
        setValuesToViews()

    }

    private fun deleteRecord(
        id:String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Destination").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Destination deleted successfully", Toast.LENGTH_LONG).show()
            val intent = Intent(this, FetchDestinationActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{error -> {
            Toast.makeText(this, "Destination delete unsuccessful ${error.message}", Toast.LENGTH_LONG).show()
        }

        }
    }

    private fun initView() {
        tvDestinationName = findViewById(R.id.tv_destination_name)
        tvDestinationLocation = findViewById(R.id.tv_destination_location)
        tvDestinationDescription = findViewById(R.id.tv_destination_description)
        ivDestinationImage = findViewById(R.id.display_destination_image)
    }

    private fun setValuesToViews() {
        tvDestinationName.text = intent.getStringExtra("destinationName")
        tvDestinationLocation.text = intent.getStringExtra("destinationLocation")
        tvDestinationDescription.text = intent.getStringExtra("destinationDescription")

        val filename = intent.getStringExtra("destinationImg")
        val storageRef = FirebaseStorage.getInstance().reference.child("images/$filename")
        val localfile = File.createTempFile("tempImage", "jpeg")
        storageRef.getFile(localfile).addOnSuccessListener {

            val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
            ivDestinationImage.setImageBitmap(bitmap)
        }
    }

}