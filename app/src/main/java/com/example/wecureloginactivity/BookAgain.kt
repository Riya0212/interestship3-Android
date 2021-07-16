package com.wecure.patient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BookAgain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_again)
        val floatButton: FloatingActionButton = findViewById(R.id.btnfloating)
        floatButton.setOnClickListener{
        //val intent=Intent(this,Appointment::class.java)
          //  startActivity(intent)
        }
    }
}