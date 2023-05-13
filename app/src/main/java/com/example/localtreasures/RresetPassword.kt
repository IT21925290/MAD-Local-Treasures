package com.example.localtreasures

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RresetPassword : AppCompatActivity() {
    private lateinit var etNewPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var btnResert: Button
    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rreset_password)
        val nameId = intent.getStringExtra("uname")
        dbRef = FirebaseDatabase.getInstance().getReference("User")
        etNewPassword = findViewById(R.id.editTextTextPassword3)
        etConfirmPassword = findViewById(R.id.editTextTextPassword4)
        btnResert = findViewById(R.id.button3)
        btnResert.setOnClickListener {
            println(nameId)
            if (nameId != null) {
                resetpassword(nameId)
            }
        }

    }

    private fun resetpassword(nameId:String) {
        val password = etNewPassword.text.toString()
        val confirmpassword = etConfirmPassword.text.toString()
        if (password == confirmpassword){
            val user = mapOf<String,String>(
                "user_Password" to password,
            )
            println(nameId)
            dbRef.child(nameId).updateChildren(user).addOnSuccessListener {

                Toast.makeText(this,"Successfuly Updated", Toast.LENGTH_SHORT).show()
                val Intent = Intent(this,ResetPasswordDone::class.java)
                startActivity(Intent)

            }.addOnFailureListener{

                Toast.makeText(this,"Failed to Update", Toast.LENGTH_SHORT).show()

            }
        }


    }
}