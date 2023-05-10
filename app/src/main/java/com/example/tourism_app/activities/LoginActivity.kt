package com.example.tourism_app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.tourism_app.R
import com.google.android.material.textview.MaterialTextView
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var createAccountButton: MaterialTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Find views by ID
        emailEditText = findViewById(R.id.login_email)
        passwordEditText = findViewById(R.id.login_pass)
        loginButton = findViewById(R.id.loginBtn)
        createAccountButton = findViewById(R.id.signRedirect)

        // Set up click listener for login button
        loginButton.setOnClickListener {
            signIn(emailEditText.text.toString(), passwordEditText.text.toString())
        }

        // Set up click listener for create account button
        createAccountButton.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }

    // Implement sign in with email and password method
    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    Toast.makeText(this, "Authentication successful.", Toast.LENGTH_SHORT).show()

                    if (emailEditText.text.toString() == "admin@app.com") {
                        startActivity(Intent(this, MainActivity::class.java))
                    }else {
                        startActivity(Intent(this, MainActivity::class.java))
                    }

                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
