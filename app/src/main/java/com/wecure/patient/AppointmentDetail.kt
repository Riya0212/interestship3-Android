package com.wecure.patient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class AppointmentDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointmentdetails_doctor)
        supportActionBar?.hide()
        val back_btn:ImageView = findViewById(R.id.id_login)!!

        back_btn.setOnClickListener {
            val intent = Intent( this, HomeScreen_Doctor::class.java)
            startActivity(intent)
        }
    }
}