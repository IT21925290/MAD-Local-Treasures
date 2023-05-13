package com.example.localtreasures

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var etUserName: EditText
    private lateinit var etPassword: EditText
    private lateinit var txForgetPassword: TextView
    private lateinit var txSignUp: TextView
    private lateinit var btnSign: Button
    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbRef = FirebaseDatabase.getInstance().getReference("User")
        etUserName = findViewById(R.id.editTextTextEmailAddress4)
        etPassword = findViewById(R.id.editTextTextPassword6)
        txForgetPassword = findViewById(R.id.textView15)
        txSignUp = findViewById(R.id.textView17)
        btnSign = findViewById(R.id.button6)

        txSignUp.setOnClickListener {
            val intent = Intent(this,SignUp::class.java)
            startActivity(intent)
        }
        txForgetPassword.setOnClickListener {
            val intent = Intent(this,ResetPasswordEmail::class.java)
            startActivity(intent)
        }

        btnSign.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val uName = etUserName.text.toString()
        val uPassword = etPassword.text.toString()
        dbRef.child(uName).get().addOnSuccessListener {
            Log.i("firebase", "Got value ${it.value}")
            if (it.value != null) {
                val valueMap = it.value as Map<String, Any>
                val upassword = valueMap["user_Password"]
                if (upassword == uPassword) {
                    Toast.makeText(this, "Log successfully", Toast.LENGTH_LONG).show()
                    etUserName.text.clear()
                    etPassword.text.clear()
                }
            } else {
                Log.i("firebase", "No value for this username")
            }
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
    }
}