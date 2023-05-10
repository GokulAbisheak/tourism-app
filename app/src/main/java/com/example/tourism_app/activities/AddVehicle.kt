package com.example.tourism_app.activities
import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.tourism_app.R
import com.example.tourism_app.models.VehicleModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.text.SimpleDateFormat
import java.util.*

class AddVehicle : AppCompatActivity() {

    private lateinit var etAddVehicleTy: EditText
    private lateinit var etAddVehicleDesc: EditText
    private lateinit var etAddVehicleFare: EditText
    private lateinit var etAddVehicleAvail: EditText
    private lateinit var btnSaveData: Button
    private lateinit var ImageBtn: Button
    private lateinit var vehImage: ImageView
    private lateinit var imageURL: Uri


    private lateinit var dbRef: DatabaseReference
    private lateinit var storageRef: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_vehicle)

        etAddVehicleTy = findViewById(R.id.etVehicleTypeInp)
        etAddVehicleDesc = findViewById(R.id.etVehicleDescriptionInp)
        etAddVehicleFare = findViewById(R.id.etVehicleFaresInp)
        etAddVehicleAvail = findViewById(R.id.etVehicleAvailInp)
        ImageBtn = findViewById(R.id.vehicle_image_button)
        vehImage = findViewById(R.id.vehicle_image)
        btnSaveData = findViewById(R.id.AddvehiSavebtn)

        dbRef = FirebaseDatabase.getInstance().getReference("Vehicles")

        ImageBtn.setOnClickListener {
            selectImage()
        }

        btnSaveData.setOnClickListener{
            dbRef.child("Vehicles").setValue(null)
            Log.d(TAG, "saveVehiData called")
            saveVehiData()
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
            vehImage.setImageURI(imageURL)

        }

    }

    private fun saveVehiData(){

        //Getting inputs
        val vehType = etAddVehicleTy.text.toString()
        val vehFare = etAddVehicleFare.text.toString()
        val vehDesc = etAddVehicleDesc.text.toString()
        val vehAvail = etAddVehicleAvail.text.toString()
        val url = imageURL.toString()

        //Add validations
        if(vehType.isEmpty()){
            etAddVehicleTy.error ="Please Enter Vehicle Type"
        }
        if(vehFare.isEmpty()){
            etAddVehicleFare.error ="Please Enter Vehicle Fare"
        }
        if(vehDesc.isEmpty()){
            etAddVehicleDesc.error ="Please Enter Vehicle Descripition"
        }
        if(vehAvail.isEmpty()){
            etAddVehicleAvail.error ="Please Enter Vehicle  Availability"
        }

        val formatter: SimpleDateFormat = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.CANADA);
        val now:Date = Date();
        var filename = formatter.format(now)


        storageRef = FirebaseStorage.getInstance().getReference(("images/${filename}"))
        storageRef.putFile(imageURL)

        val vehId = dbRef.push().key!!



        val vehicle = VehicleModel(vehId,vehType,vehDesc,vehFare,vehAvail)

        dbRef.child(vehId).setValue(vehicle).addOnCompleteListener{ task ->
            Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_LONG).show()
            etAddVehicleTy.text.clear()
            etAddVehicleFare.text.clear()
            etAddVehicleAvail.text.clear()
            etAddVehicleDesc.text.clear()

        }.addOnFailureListener{exception ->
            val errorMsg = "Failed to save vehicle data: ${exception.message}"
            Log.e(TAG, errorMsg, exception)
            Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
        }
    }
}