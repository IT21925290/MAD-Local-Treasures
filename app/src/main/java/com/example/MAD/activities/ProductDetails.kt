package com.example.MAD.activities

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.MAD.R
import com.example.MAD.model.ProductModel
import com.google.firebase.database.FirebaseDatabase

class ProductDetails : AppCompatActivity() {
    private lateinit var tvpId: TextView
    private lateinit var tvpName: TextView
    private lateinit var tvpAmount: TextView
    private lateinit var tvpDesc: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("pId").toString(),
                intent.getStringExtra("pName").toString()
            )

        }
        btnDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("pId").toString()
            )
        }
    }
private fun deleteRecord(
    id: String
){
    val dbRef = FirebaseDatabase.getInstance().getReference("Products").child(id)
    val mTask = dbRef.removeValue()

    mTask.addOnSuccessListener {
        Toast.makeText(this, "Product data deleted", Toast.LENGTH_LONG).show()

        val intent = Intent(this, viewItems::class.java)
        finish()
        startActivity(intent)
    }.addOnFailureListener{ error ->
        Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
    }
}

private fun initView() {
        tvpId = findViewById(R.id.tvpId)
        tvpName = findViewById(R.id.tvpName)
        tvpAmount = findViewById(R.id.tvpAmount)
        tvpDesc = findViewById(R.id.tvpDesc)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews() {
        tvpId.text = intent.getStringExtra("pId")
        tvpName.text = intent.getStringExtra("pName")
        tvpAmount.text = intent.getStringExtra("pAmount")
        tvpDesc.text = intent.getStringExtra("pDesc")

    }
        private fun openUpdateDialog(
            pId: String,
            pName: String
        ) {
            val mDialog = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val mDialogView = inflater.inflate(R.layout.activity_update_dialog, null)

            mDialog.setView(mDialogView)

            val etpName = mDialogView.findViewById<EditText>(R.id.etpName)
            val etpAmount = mDialogView.findViewById<EditText>(R.id.etpAmount)
            val etpDesc = mDialogView.findViewById<EditText>(R.id.etpDesc)

            val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

            etpName.setText(intent.getStringExtra("pName").toString())
            etpAmount.setText(intent.getStringExtra("pAmount").toString())
            etpDesc.setText(intent.getStringExtra("pDesc").toString())

            mDialog.setTitle("Updating $pName Record")

            val alertDialog = mDialog.create()
            alertDialog.show()

            btnUpdateData.setOnClickListener {
                updateProductData(
                    pId,
                    etpName.text.toString(),
                    etpAmount.text.toString(),
                    etpDesc.text.toString()
                )

                Toast.makeText(applicationContext, "Product Data Updated", Toast.LENGTH_LONG).show()

                //we are setting updated data to our textviews
                tvpName.text = etpName.text.toString()
                tvpAmount.text = etpAmount.text.toString()
                tvpDesc.text = etpDesc.text.toString()

                alertDialog.dismiss()
            }
        }

        private fun updateProductData(
            id: String,
            name: String,
            amount: String,
            desc: String
        ) {
            val dbRef = FirebaseDatabase.getInstance().getReference("Products").child(id)
            val productInfo = ProductModel(id, name, amount, desc)
            dbRef.setValue(productInfo)
        }

    }


