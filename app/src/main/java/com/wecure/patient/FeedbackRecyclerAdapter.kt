package com.wecure.patient

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FeedbackRecyclerAdapter(private var nameFeedback:List<String>,private var textFeedback:List<String>,private var ratingFeedback:List<Float>):
RecyclerView.Adapter<FeedbackRecyclerAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val itemnameFeedback:TextView=itemView.findViewById(R.id.txtNameOfPatient_feedback)
        val itemtextFeedback:TextView=itemView.findViewById(R.id.txtdescription_feedback)
        val itemratingFeedback:RatingBar=itemView.findViewById(R.id.imgrate_feedback)
        val itemtextratingFeedback:TextView=itemView.findViewById(R.id.txtrate_feedback)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FeedbackRecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.activity_feedback_recycler_view, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: FeedbackRecyclerAdapter.ViewHolder, position: Int) {
        holder.itemnameFeedback.text = nameFeedback[position]
        holder.itemtextFeedback.text=textFeedback[position]
        holder.itemratingFeedback.rating=ratingFeedback[position].toFloat()
        holder.itemtextratingFeedback.text=ratingFeedback[position].toString()
    }

    override fun getItemCount(): Int {
      return nameFeedback.size
    }

}
