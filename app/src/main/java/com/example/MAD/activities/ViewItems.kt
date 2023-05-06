package com.example.MAD.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.MAD.R
import com.example.MAD.adapters.ProductAdapter
import com.example.MAD.model.ProductModel
import com.google.firebase.database.*

class viewItems : AppCompatActivity() {
    private lateinit var productRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var productList: ArrayList<ProductModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_items)

        productRecyclerView = findViewById(R.id.rvProduct)
        productRecyclerView.layoutManager = LinearLayoutManager(this)
        productRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        productList = arrayListOf<ProductModel>()

        getProductsData()
    }
    private fun getProductsData() {

        productRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Products")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                productList.clear()
                if (snapshot.exists()){
                    for (productSnap in snapshot.children){
                        val productData = productSnap.getValue(ProductModel::class.java)
                        productList.add(productData!!)
                    }
                    val mAdapter = ProductAdapter(productList)
                    productRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : ProductAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {

                            val intent = Intent(this@viewItems, ProductDetails::class.java)

                            //put extras
                            intent.putExtra("pId", productList[position].pId)
                            intent.putExtra("pName", productList[position].pName)
                            intent.putExtra("pAmount", productList[position].pAmount)
                            intent.putExtra("pDesc", productList[position].pDesc)
                            startActivity(intent)
                        }

                    })

                    productRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}
