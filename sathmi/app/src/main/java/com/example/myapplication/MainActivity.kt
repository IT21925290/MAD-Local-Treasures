package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat.startActivity

import android.view.View
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var feeds: Button
    private lateinit var edit:Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        feeds = findViewById(R.id.button2)

        feeds.setOnClickListener{
            val intent = Intent(this, feedback::class.java)
            startActivity(intent)

        }
        edit = findViewById(R.id.button5)

        edit.setOnClickListener{
            val intent = Intent(this, editProfile::class.java)
            startActivity(intent)

        }


    }


}