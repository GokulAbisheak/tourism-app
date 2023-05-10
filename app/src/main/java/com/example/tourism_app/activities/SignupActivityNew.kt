package com.example.tourism_app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tourism_app.R
import com.example.tourism_app.databinding.ActivitySignupNewBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class SignupActivityNew : AppCompatActivity() {
    private lateinit var binding: ActivitySignupNewBinding
    private lateinit var firebaseAuth :FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_new)

        binding = ActivitySignupNewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        val name = binding.signupName.text.toString()
        val email =  binding.signupEmail.text.toString()
        val password = binding.signupPassw.text.toString()
        val conPassword = binding.signupConfirmPassw.text.toString()

        if(email.isNotEmpty() && name.isNotEmpty() && password.isNotEmpty() && conPassword.isNotEmpty()){
            if(password == conPassword){

                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
                    if(it.isSuccessful){

                        val intent = Intent(this, loginActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this,"Password does not match",Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this,"Fields cannot be Empty",Toast.LENGTH_SHORT).show()
        }
        binding.logRedirect.setOnClickListener{
            val loginIntent = Intent(this, loginActivity::class.java)
            startActivity(loginIntent)
        }
    }
}