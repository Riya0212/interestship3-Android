package com.wecure.doctor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DoctorDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_detail)
        supportActionBar?.hide()
        val floatButton:FloatingActionButton = findViewById(R.id.floating_appointent)
        floatButton.setOnClickListener{
            val intent = Intent(this, Appointment::class.java)
            startActivity(intent)
        }
    }
}