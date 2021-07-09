package com.wecure.patient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wecure.patient.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.activity_feedback.*
import kotlinx.android.synthetic.main.fragment_home.*

class Feedback : AppCompatActivity() {
    private var nameFeedbackList = mutableListOf<String>()
    private var rateFeedbackList = mutableListOf<Float>()
    private var descFeedbackList = mutableListOf<String>()

    private var _binding: FragmentHomeBinding? = null

    private val dataHistory = DataSource.createDataSetFeedBack()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)
        supportActionBar?.hide()




        postToListFeedback()
        feedbackrecycle.layoutManager= LinearLayoutManager(this,
            RecyclerView.HORIZONTAL,false)
        feedbackrecycle.adapter = FeedbackRecyclerAdapter(nameFeedbackList,descFeedbackList,rateFeedbackList)
    }


    private fun addToListFeedback(name:String,desc:String,rate:Float){

        nameFeedbackList.add(name)
        descFeedbackList.add(desc)
        rateFeedbackList.add(rate)


    }
    private fun postToListFeedback(){
        for( i in 0 until dataHistory.size){
            addToListFeedback(dataHistory[i].name,dataHistory[i].feedback,dataHistory[i].rate)
        }
    }
}