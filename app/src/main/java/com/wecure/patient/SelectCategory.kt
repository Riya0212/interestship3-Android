package com.wecure.patient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import de.hdodenhof.circleimageview.CircleImageView

class SelectCategory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_category)
        supportActionBar?.hide()
        val img_doctor:CircleImageView = findViewById(R.id.img_cat_doctor)
        val img_patient:CircleImageView = findViewById(R.id.img_cat_patient)
        val btn_next:FloatingActionButton =findViewById(R.id.fabnext)
        var flag:Boolean=false
        img_doctor.setOnClickListener {
            img_patient.setCircleBackgroundColorResource(R.color.blue3)
            img_doctor.setCircleBackgroundColorResource(R.color.pink)
            flag=true
        }
        img_patient.setOnClickListener {
            img_doctor.setCircleBackgroundColorResource(R.color.blue3)
            img_patient.setCircleBackgroundColorResource(R.color.pink)
            flag=true
        }

        btn_next.setOnClickListener{
            if(flag){
                val intent = Intent(this, MainActivity::class.java)

                startActivity(intent)
            }
            else{
                Toast.makeText(this,"Please select a category",Toast.LENGTH_SHORT).show()

            }
        }

    }


}