package com.wecure.patient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SelectCategory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_category)
        supportActionBar?.hide()
        val img_doctor:ImageView = findViewById(R.id.img_cat_doctor)
        val img_patient:ImageView = findViewById(R.id.img_cat_patient)
        val btn_next:FloatingActionButton =findViewById(R.id.fabnext)
        btn_next.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }

    }


}