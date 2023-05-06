package com.example.tourism_app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.tourism_app.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var redirectAddDestinationBtn: Button
    private lateinit var redirectFetchDestinationBtn: Button
    private lateinit var redirectLoginBtn: Button
    private lateinit var redirectSignUpBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        redirectAddDestinationBtn = findViewById(R.id.redirect_add_destination_button)
        redirectFetchDestinationBtn = findViewById(R.id.redirect_fetch_destination_button)
        redirectLoginBtn = findViewById(R.id.redirect_login_button)
        redirectSignUpBtn = findViewById(R.id.redirect_signup_button)

        var firebase: DatabaseReference = FirebaseDatabase.getInstance().getReference()

        redirectAddDestinationBtn.setOnClickListener {
            val intent = Intent(this, AddDestinationActivity::class.java)
            startActivity(intent)
        }

        redirectFetchDestinationBtn.setOnClickListener {
            val intent = Intent(this, FetchDestinationActivity::class.java)
            startActivity(intent)
        }

        redirectLoginBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        redirectSignUpBtn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}