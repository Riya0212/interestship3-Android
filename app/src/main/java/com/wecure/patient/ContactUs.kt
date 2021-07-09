package com.wecure.patient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.wecure.patient.databinding.ActivityContactUsBinding

class ContactUs : AppCompatActivity() {
    private lateinit var binding:ActivityContactUsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding= ActivityContactUsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val window: Window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.setStatusBarColor(this.resources.getColor(R.color.pink))

        binding.btnsubmit.setOnClickListener {
            Toast.makeText(this,"Thank You for Contactig us",Toast.LENGTH_SHORT).show()
            val intent=Intent(this,HomeScreen::class.java)
            startActivity(intent)
        }

    }
}