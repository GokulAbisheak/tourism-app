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

class DestinationDetailsActivity: AppCompatActivity() {

    private lateinit var tvDestinationId: TextView
    private lateinit var tvDestinationName: TextView
    private lateinit var tvDestinationLocation: TextView
    private lateinit var tvDestinationDescription: TextView
    private lateinit var ivDestinationImage: ImageView
    private lateinit var destinationImg: String
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.destination_details)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener{
            openUpdateDialog(
                intent.getStringExtra("destinationId").toString(),
                intent.getStringExtra("destinationName").toString()
            )
        }

        btnDelete.setOnClickListener{
            deleteRecord(
                intent.getStringExtra("destinationId").toString()
            )
        }
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
        btnUpdate = findViewById(R.id.update_button)
        btnDelete = findViewById(R.id.delete_button)
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

    private fun openUpdateDialog(
        destinationId: String,
        destinationName: String
    ){
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_destination, null)

        mDialog.setView(mDialogView)

        val etDestinationName = mDialogView.findViewById<EditText>(R.id.destination_name_update)
        val etDestinationLocation = mDialogView.findViewById<EditText>(R.id.destination_location_update)
        val etDestinationDescription = mDialogView.findViewById<EditText>(R.id.destination_description_update)
        val btnUpdateData = mDialogView.findViewById<Button>(R.id.destination_update_button)

        etDestinationName.setText(intent.getStringExtra("destinationName").toString())
        etDestinationLocation.setText(intent.getStringExtra("destinationLocation").toString())
        etDestinationDescription.setText(intent.getStringExtra("destinationDescription").toString())
        destinationImg = intent.getStringExtra("destinationImg").toString()

        mDialog.setTitle("Updating $destinationName")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener{
            updateDestination(
                destinationId,
                etDestinationName.text.toString(),
                etDestinationLocation.text.toString(),
                etDestinationDescription.text.toString(),
                destinationImg

            )

            Toast.makeText(applicationContext, "Destination Data Updated Successfully", Toast.LENGTH_LONG).show()

            tvDestinationName.text = etDestinationName.text.toString()
            tvDestinationLocation.text = etDestinationLocation.text.toString()
            tvDestinationDescription.text = etDestinationDescription.text.toString()

            alertDialog.dismiss()
        }
    }

    private fun updateDestination(
        id: String,
        name: String,
        location: String,
        description: String,
        destinationImg: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Destination").child(id)
        val destinationInfo = DestinationModel(id, name, location, description, destinationImg)
        dbRef.setValue(destinationInfo)
    }

}