package com.wecure.patient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Appointment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointment)
        supportActionBar?.hide()
    }
}