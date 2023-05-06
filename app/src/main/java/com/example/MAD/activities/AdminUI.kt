package com.example.MAD.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.MAD.R

class adminUI : AppCompatActivity() {

    private lateinit var View: Button
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_ui)

        View = findViewById(R.id.button14)

        View.setOnClickListener{
            val intent = Intent(this, viewItems::class.java)
                startActivity(intent)
             }

        }


    }
