package com.example.wecureloginactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class userProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
    }
}