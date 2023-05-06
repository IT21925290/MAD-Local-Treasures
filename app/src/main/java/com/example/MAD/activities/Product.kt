package com.example.MAD.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.MAD.R

class product : AppCompatActivity() {
    private lateinit var add1: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        add1 = findViewById(R.id.button7)
        add1.setOnClickListener{
            //val intent = Intent(this,)
        }

    }
}