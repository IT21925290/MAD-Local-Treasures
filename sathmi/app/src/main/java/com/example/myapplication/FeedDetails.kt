package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.FirebaseDatabase

class feedDetails : AppCompatActivity() {
    private lateinit var tvName: TextView
    private lateinit var tvStars: TextView
    private lateinit var tvImprove: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_details)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("id").toString(),
                intent.getStringExtra("name").toString()
            )
        }

        btnDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("fid").toString()
            )
        }

    }

    private fun initView() {
        tvName = findViewById(R.id.tvName)
        tvStars = findViewById(R.id.tvStars)
        tvImprove = findViewById(R.id.tvImprove)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews() {
        tvName.text = intent.getStringExtra("name")
        tvStars.text = intent.getStringExtra("stars")
        tvImprove.text = intent.getStringExtra("improve")

    }

    private fun openUpdateDialog(
        id: String,
        name: String
    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.activity_update_feed, null)

        mDialog.setView(mDialogView)

        val etName = mDialogView.findViewById<EditText>(R.id.etName)
        val etStars = mDialogView.findViewById<EditText>(R.id.etStars)
        val etImprove = mDialogView.findViewById<EditText>(R.id.etImprove)

        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        etName.setText(intent.getStringExtra("name").toString())
        etStars.setText(intent.getStringExtra("stars").toString())
        etImprove.setText(intent.getStringExtra("improve").toString())

        mDialog.setTitle("Updating $name Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updateEmpData(
                id,
                etName.text.toString(),
                etStars.text.toString(),
                etImprove.text.toString()
            )

            Toast.makeText(applicationContext, "Employee Data Updated", Toast.LENGTH_LONG).show()

            tvName.text = etName.text.toString()
            tvStars.text = etStars.text.toString()
            tvImprove.text = etImprove.text.toString()

            alertDialog.dismiss()
        }
    }

    private fun updateEmpData(
        fid: String,
        name: String,
        stars: String,
        improve: String
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Feedback").child(fid)
        val empInfo = FeedModel(fid, name, stars, improve)
        dbRef.setValue(empInfo)
    }
    private fun deleteRecord(
        fid: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Feedback").child(fid)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Cart Item deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, feedbackView::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

}
