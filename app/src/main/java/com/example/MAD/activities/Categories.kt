package com.example.MAD.activities

import android.icu.util.CurrencyAmount
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.MAD.R
import com.example.MAD.model.ProductModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class categories : AppCompatActivity() {
    private lateinit var etpname: EditText
    private lateinit var etpAmount: EditText
    private lateinit var etDesc: EditText
    private lateinit var addItem: Button

    private lateinit var dbRef:DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        etpname = findViewById(R.id.etpname)
        etpAmount = findViewById(R.id.etpAmount)
        etDesc = findViewById(R.id.etDesc)
        addItem = findViewById(R.id.addItem)
        dbRef = FirebaseDatabase.getInstance().getReference("Products")

        addItem.setOnClickListener{
            saveProductsData()
        }


    }

    private fun saveProductsData(){
        //getting values
        val pName = etpname.text.toString()
        val pAmount = etpAmount.text.toString()
        val pDesc= etDesc.text.toString()

        if (pName.isEmpty()) {
            etpname.error = "Please enter product name"
        }
        if (pAmount.isEmpty()) {
            etpAmount.error = "Please enter amount"
        }
        if (pDesc.isEmpty()) {
            etDesc.error = "Please enter description"
        }

        val pId = dbRef.push().key!!

        val product = ProductModel(pId, pName, pAmount, pDesc)

        dbRef.child(pId).setValue(product)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                etpname.text.clear()
                etpAmount.text.clear()
                etDesc.text.clear()


            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }

    }

}

