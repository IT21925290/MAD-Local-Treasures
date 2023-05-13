package com.example.localtreasures

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ResetPasswordDone : AppCompatActivity() {
    private lateinit var btnSign: Button
    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password_done)
        dbRef = FirebaseDatabase.getInstance().getReference("User")
        btnSign = findViewById(R.id.button4)

        btnSign.setOnClickListener {
            val Intent = Intent(this,MainActivity::class.java)
            startActivity(Intent)
        }



    }
}