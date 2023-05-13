package com.example.localtreasures

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {
    private lateinit var etName: EditText
    private lateinit var etEMail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var btnCreate: Button
    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        dbRef = FirebaseDatabase.getInstance().getReference("User")
        etName = findViewById(R.id.editTextTextPersonName2)
        etEMail = findViewById(R.id.editTextTextEmailAddress)
        etPassword = findViewById(R.id.editTextTextPassword)
        etConfirmPassword = findViewById(R.id.editTextTextPassword2)
        btnCreate = findViewById(R.id.button)

        btnCreate.setOnClickListener {
            createAccount()
        }
    }

    private fun createAccount() {
        val User_name= etName.text.toString()
        val User_Email= etEMail.text.toString()
        val User_Password= etPassword.text.toString()
        val Confirm_Password= etConfirmPassword.text.toString()
        if (User_Password == Confirm_Password){
            val user = User(User_name,User_Email,User_Password)
            dbRef.child(User_name).setValue(user)
                .addOnCompleteListener {
                    Toast.makeText(this, "User Created successfully", Toast.LENGTH_LONG).show()
                    etName.text.clear()
                    etEMail.text.clear()
                    etPassword.text.clear()
                    etConfirmPassword.text.clear()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }.addOnFailureListener { err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                }
        } else{
            Toast.makeText(this, "Password Miss Match", Toast.LENGTH_LONG).show()
        }

    }
}