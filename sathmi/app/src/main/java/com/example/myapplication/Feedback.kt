package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Feedback : AppCompatActivity() {
    private lateinit var EditText5: EditText
    private lateinit var EditText4: EditText
    private lateinit var button6: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)
        EditText5 = findViewById(R.id.EditText5)
        EditText4 = findViewById(R.id.EditText4)
        button6 = findViewById(R.id.button6)
        dbRef = FirebaseDatabase.getInstance().getReference("Feedback")

        button6.setOnClickListener{
            saveFeedback()
        }
    }

    private fun saveFeedback(){
        //getting values
        val stars = EditText5.text.toString()
        val improve = EditText4.text.toString()

        if(stars.isEmpty()){
            EditText5.error = "Please Enter Rating"
        }
        if(improve.isEmpty()){
            EditText4.error = "Please Enter Ideas"
        }
        val fid  = dbRef.push().key!!

        val feedback = FeedModel(fid,stars,improve)
        dbRef.child(fid).setValue(feedback)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()
                EditText5.text.clear()
                EditText4.text.clear()

            }.addOnFailureListener{err->
                Toast.makeText(this, "Error${err.message}", Toast.LENGTH_LONG).show()
            }
    }


}