package com.example.tourism_app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tourism_app.R
import com.example.tourism_app.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class loginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.loginBtn.setOnClickListener{
            val email = binding.loginEmail.text.toString()
            val password = binding.loginPassw.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()){

                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(){

                    if(it.isSuccessful){
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this,it.exception.toString(),Toast.LENGTH_LONG).show()
                    }
                }
            }else{
                Toast.makeText(this,"Feilds cannot be Empty",Toast.LENGTH_LONG).show()
            }

        }
        binding.signRedirect.setOnClickListener{
            val signupIntent = Intent(this, SignupActivityNew::class.java)
            startActivity(signupIntent)
        }
    }
}