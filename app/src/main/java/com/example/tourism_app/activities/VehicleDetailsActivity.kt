package com.example.tourism_app.activities

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

        btnUpdate.setOnClickListener{

            openUpdateDilog(

                        intent.getStringExtra("vehId").toString(),
                        intent.getStringExtra("vehTy").toString()
            )
        }
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

    private fun openUpdateDilog(
        vehId:String,
        VehTy:String
    ){
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_dialog, null)

        mDialog.setView(mDialogView)

        val etVehicleTy = mDialogView.findViewById<EditText>(R.id.etVehicleTypeInp)
        val etVehicleDesc = mDialogView.findViewById<EditText>(R.id.etVehicleDescriptionInp)
        val etVehicleFare = mDialogView.findViewById<EditText>(R.id.etVehicleFaresInp)
        val btnUpdateVehiData = mDialogView.findViewById<Button>(R.id.btnUpdatevehiData)

        mDialog.setTitle("Updating $VehTy Record")

        val alertDialog =mDialog.create()
        alertDialog.show()

        btnUpdateVehiData.setOnClickListener{
            updateVehiData(
                vehId,
                etVehicleTy.text.toString(),
                etVehicleDesc.text.toString(),
                etVehicleFare.text.toString()
            )
            Toast.makeText(applicationContext,"Vehicle Data Updated",Toast.LENGTH_LONG).show()

            tvVehiTy.text = etVehicleTy.text.toString()
            tvVehiDesc.text = etVehicleDesc.text.toString()
            tvVehiFare.text = etVehicleFare.text.toString()

            alertDialog.dismiss()
        }

    }

    private fun updateVehiData(
        id: String,
        Type:String,
        Desc:String,
        fare:String){

        val dbRef = FirebaseDatabase.getInstance().getReference("Vehicles").child(id)
        val vehInfo = VehicleModel(vehId = null, vehTy = null , vehDesc = null, vehAvail = null)
        dbRef.setValue(vehInfo)

    }

}