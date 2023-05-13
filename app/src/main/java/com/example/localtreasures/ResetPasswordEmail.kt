package com.example.localtreasures

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ResetPasswordEmail : AppCompatActivity() {
    private lateinit var etUserEmail: EditText
    private lateinit var btnContinue: Button
    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password_email)
        dbRef = FirebaseDatabase.getInstance().getReference("User")
        etUserEmail = findViewById(R.id.editTextTextEmailAddress)
        btnContinue = findViewById(R.id.button2)

        btnContinue.setOnClickListener {
            validateEmail()
        }

    }

    private fun validateEmail() {
        val uName = etUserEmail.text.toString()
        dbRef.child(uName).get().addOnSuccessListener {
            Log.i("firebase", "Got value ${it.value}")
            if (it.value != null) {
                val intent = Intent(this, RresetPassword::class.java)
                intent.putExtra("uname",uName)
                startActivity(intent)
            } else {
                Log.i("firebase", "No value for this username")
            }
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
    }
}