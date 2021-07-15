package com.wecure.patient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.wecure.patient.activity.AppointmentActivity
import kotlinx.android.synthetic.main.fragment_home.*

class DoctorDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_detail)
        supportActionBar?.hide()
        val floatButton:FloatingActionButton = findViewById(R.id.floating_appointent)
        floatButton.setOnClickListener{
            val intent = Intent(this, AppointmentActivity::class.java)
            startActivity(intent)
        }
        val back_btn: ImageView = findViewById(R.id.id_login)!!

        back_btn.setOnClickListener {
            val intent = Intent( this, HomeScreen::class.java)
            startActivity(intent)
        }


        val textviewreview:TextView=findViewById(R.id.viewfeedbacklink)
        textviewreview.setOnClickListener {
            val intent = Intent(this, Feedback::class.java)
            startActivity(intent)
        }


    }
}