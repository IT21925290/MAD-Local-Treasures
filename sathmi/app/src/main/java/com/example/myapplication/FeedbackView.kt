package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class feedbackView : AppCompatActivity() {

        private lateinit var feedRecyclerView: RecyclerView
        private lateinit var tvLoadingData: TextView
        private lateinit var feedList: ArrayList<FeedModel>
        private lateinit var dbRef: DatabaseReference

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_feedback_view)

            feedRecyclerView = findViewById(R.id.rvFeed)
            feedRecyclerView.layoutManager = LinearLayoutManager(this)
            feedRecyclerView.setHasFixedSize(true)
            tvLoadingData = findViewById(R.id.tvLoadingData)


            feedList = arrayListOf<FeedModel>()

            getFeedbackData()

        }

        private fun getFeedbackData() {

            feedRecyclerView.visibility = View.GONE
            tvLoadingData.visibility = View.VISIBLE

            dbRef = FirebaseDatabase.getInstance().getReference("Feedback")

            dbRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    feedList.clear()
                    if (snapshot.exists()){
                        for (feedSnap in snapshot.children){
                            val feedData = feedSnap.getValue(FeedModel::class.java)
                            feedList.add(feedData!!)
                        }
                        val mAdapter = feedAdapter(feedList)
                        feedRecyclerView.adapter = mAdapter

                        mAdapter.setOnItemClickListener(object : feedAdapter.OnItemClickListener{
                            override fun onItemClick(position: Int) {

                                val intent = Intent(this@feedbackView, feedDetails::class.java)

                                //put extras
                                intent.putExtra("name", feedList[position].name)
                                intent.putExtra("fid", feedList[position].fid)
                                intent.putExtra("stars", feedList[position].stars)
                                intent.putExtra("improve", feedList[position].improve)
                                startActivity(intent)
                            }

                        })

                        tvLoadingData.visibility = View.GONE
                        feedRecyclerView.visibility = View.VISIBLE
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }

}
