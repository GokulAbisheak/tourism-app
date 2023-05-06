package com.example.tourism_app.activities
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.tourism_app.R
import com.example.tourism_app.models.VehicleModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddVehicle : AppCompatActivity() {

    private lateinit var etAddVehicleTy: EditText
    private lateinit var etAddVehicleDesc: EditText
    private lateinit var etAddVehicleFare: EditText
    private lateinit var etAddVehicleAvail: EditText
    private lateinit var btnSaveData: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_vehicle)

        etAddVehicleTy = findViewById(R.id.etVehicleTypeInp)
        etAddVehicleDesc = findViewById(R.id.etVehicleDescriptionInp)
        etAddVehicleFare = findViewById(R.id.etVehicleFaresInp)
        etAddVehicleAvail = findViewById(R.id.etVehicleAvailInp)
        btnSaveData = findViewById(R.id.AddvehiSavebtn)

        dbRef = FirebaseDatabase.getInstance().getReference("Vehicles")

        btnSaveData.setOnClickListener{
            dbRef.child("Vehicles").setValue(null)
            Log.d(TAG, "saveVehiData called")
            saveVehiData()
        }

    }

    private fun saveVehiData(){

        //Getting inputs
        val vehType = etAddVehicleTy.text.toString()
        val vehFare = etAddVehicleFare.text.toString()
        val vehDesc = etAddVehicleDesc.text.toString()
        val vehAvail = etAddVehicleAvail.text.toString()

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