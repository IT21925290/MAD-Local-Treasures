package com.example.MAD.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.MAD.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()
       val profile_user:ImageView = findViewById(R.id.imageView41)

       profile_user.setOnClickListener{
           val intent = Intent(this, adminUI::class.java)
           startActivity(intent)
       }

        val carrot:ImageView = findViewById(R.id.imageView3)

        carrot.setOnClickListener{
            val intent = Intent(this, product::class.java)
            startActivity(intent)
        }

        val leeks:ImageView = findViewById(R.id.imageView16)

        leeks.setOnClickListener{
            val intent = Intent(this, product2::class.java)
            startActivity(intent)
        }
        val turmeric:ImageView = findViewById(R.id.imageView20)

        turmeric.setOnClickListener{
            val intent = Intent(this, product4::class.java)
            startActivity(intent)
        }
        val ginger:ImageView = findViewById(R.id.imageView19)

        ginger.setOnClickListener{
            val intent = Intent(this, product5::class.java)
            startActivity(intent)
        }
        val _b4c845172c02f5e0ca1addee2809247:ImageView = findViewById(R.id.imageView22)

        _b4c845172c02f5e0ca1addee2809247.setOnClickListener{
            val intent = Intent(this, product8::class.java)
            startActivity(intent)
        }
        val craft:ImageView = findViewById(R.id.imageView23)

        craft.setOnClickListener{
            val intent = Intent(this, product7::class.java)
            startActivity(intent)
        }


    }
}