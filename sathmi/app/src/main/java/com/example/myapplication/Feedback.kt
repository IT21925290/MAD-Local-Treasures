package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class feedback : AppCompatActivity() {
    private lateinit var EditText5: EditText
    private lateinit var EditText4: EditText
    private lateinit var nameFeed: EditText
    private lateinit var button6: Button
    private lateinit var btnView: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)
        EditText5 = findViewById(R.id.EditText5)
        EditText4 = findViewById(R.id.EditText4)
        nameFeed = findViewById(R.id.nameFeed)
        button6 = findViewById(R.id.button6)
        dbRef = FirebaseDatabase.getInstance().getReference("Feedback")

        button6.setOnClickListener{
            saveFeedback()
        }

        btnView = findViewById(R.id.button7)

        btnView.setOnClickListener{
            val intent = Intent(this, feedbackView::class.java)
            startActivity(intent)

        }
    }

    private fun saveFeedback(){
        //getting values
        val stars = EditText5.text.toString()
        val improve = EditText4.text.toString()
        val name = nameFeed.text.toString()

        if(stars.isEmpty()){
            EditText5.error = "Please Enter Rating"
        }
        if(improve.isEmpty()){
            EditText4.error = "Please Enter Ideas"
        }
        if(name.isEmpty()){
            nameFeed.error = "Please Enter Name"
        }

        val fid = dbRef.push().key!!

        val feedback = FeedModel(fid,name,stars,improve)
        dbRef.child(fid).setValue(feedback)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()
                EditText5.text.clear()
                EditText4.text.clear()
                nameFeed.text.clear()

            }.addOnFailureListener{err->
                Toast.makeText(this, "Error${err.message}", Toast.LENGTH_LONG).show()
            }
    }


}