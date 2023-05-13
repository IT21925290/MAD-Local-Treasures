package com.example.MAD.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.MAD.R

class ProductDetailsCustomer : AppCompatActivity() {
    private lateinit var tvpId: TextView
    private lateinit var tvpName: TextView
    private lateinit var tvpAmount: TextView
    private lateinit var tvpDesc: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details_customer)

        initView()
        setValuesToViews()


    }

    private fun initView() {
        tvpId = findViewById(R.id.tvpId)
        tvpName = findViewById(R.id.tvpName)
        tvpAmount = findViewById(R.id.tvpAmount)
        tvpDesc = findViewById(R.id.tvpDesc)



    }

    private fun setValuesToViews() {
        tvpId.text = intent.getStringExtra("pId")
        tvpName.text = intent.getStringExtra("pName")
        tvpAmount.text = intent.getStringExtra("pAmount")
        tvpDesc.text = intent.getStringExtra("pDesc")

    }




}


