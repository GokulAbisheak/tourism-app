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

class LocationDetailsActivity : AppCompatActivity() {

    private lateinit var tvCusId: TextView
    private lateinit var tvmyName: TextView
    private lateinit var tvmyPhone: TextView
    private lateinit var tvmyRent: TextView
    private lateinit var tvmyAddress: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_details)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("CusId").toString(),
                intent.getStringExtra("myName").toString()

            )
        }
        btnDelete.setOnClickListener{
            deleteRecord(
                intent.getStringExtra("CusId").toString()
            )
        }
    }
    private fun deleteRecord(CusId:String){
        val dbRef =FirebaseDatabase.getInstance().getReference("MyDetails").child(CusId)
        val mTask =dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this,"Hotel data deleted", Toast.LENGTH_LONG).show()
            val intent= Intent(this,FetchLocation::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{error ->
            Toast.makeText(this,"Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }
    private fun initView(){
        tvCusId = findViewById(R.id.tvCusId)
        tvmyName = findViewById(R.id.tv_my_name)
        tvmyPhone = findViewById(R.id.tv_my_phone)
        tvmyRent = findViewById(R.id.tv_my_Rent)
        tvmyAddress = findViewById(R.id.tv_my_Address)
        btnUpdate = findViewById(R.id.update_button)
        btnDelete = findViewById(R.id.delete_button)

    }
    private fun setValuesToViews(){

        tvCusId.text=intent.getStringExtra("CusId")
        tvmyName.text=intent.getStringExtra("myName")
        tvmyPhone.text=intent.getStringExtra("myPhone")
        tvmyRent.text=intent.getStringExtra("myRent")
        tvmyAddress.text=intent.getStringExtra("myAddress")

    }

    private fun openUpdateDialog(
        CusId: String,
        myName:String
    )
    {
        val mDialog = AlertDialog.Builder(this)
        val inflater=layoutInflater
        val mDialogView=inflater.inflate(R.layout.update_dialog_l,null)

        mDialog.setView(mDialogView)

        val et_myName = mDialogView.findViewById<EditText>(R.id.upmyName)
        val et_myPhone = mDialogView.findViewById<EditText>(R.id.upmyPhone)
        val et_myRent = mDialogView.findViewById<EditText>(R.id.upmyRent)
        val et_myAddress = mDialogView.findViewById<EditText>(R.id.upmyAddress)
        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        et_myName.setText(intent.getStringExtra("myName").toString())
        et_myPhone.setText(intent.getStringExtra("myPhone").toString())
        et_myRent.setText(intent.getStringExtra("myRent").toString())
        et_myAddress.setText(intent.getStringExtra("myAddress").toString())

        mDialog.setTitle("Updating $myName Record")

        val alertDialog=mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updateLocationData(
                CusId,
                et_myName.text.toString(),
                et_myPhone.text.toString(),
                et_myRent.text.toString(),
                et_myAddress.text.toString()

            )
            Toast.makeText(applicationContext,"Hotel Data Updated",Toast.LENGTH_LONG).show()

            //Update Data to our txt view
            tvmyName.text=et_myName.text.toString()
            tvmyPhone.text=et_myPhone.text.toString()
            tvmyRent.text=et_myRent.text.toString()
            tvmyAddress.text=et_myAddress.text.toString()

            alertDialog.dismiss()
        }
    }
    private fun updateLocationData(
        CusId:String,
        name:String,
        phone:String,
        rent:String,
        address:String
    ){
        val dbRef =FirebaseDatabase.getInstance().getReference("MyDetails").child(CusId)
        val locationInfo = CustomerModel(CusId,name,phone,rent,address)
        dbRef.setValue(locationInfo)
    }
}