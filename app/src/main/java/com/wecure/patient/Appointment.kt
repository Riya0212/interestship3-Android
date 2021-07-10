package com.wecure.patient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton

class Appointment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointment)
        supportActionBar?.hide()

        val btn_submit:AppCompatButton = findViewById(R.id.btn_submit_patient)
        btn_submit.setOnClickListener {

            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
            finish()
            Toast.makeText(this,"Appointment booked successfully", Toast.LENGTH_SHORT).show()
        }
    }
}