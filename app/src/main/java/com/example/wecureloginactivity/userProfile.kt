package com.wecure.patient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wecure.patient.Extensions.toast
import com.wecure.patient.FireBaseUtils.firebaseAuth
import kotlinx.android.synthetic.main.activity_user_profile.*

class userProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        btnok.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(this, RegistrationActivity::class.java))
            toast("signed out")
            finish()
        }
    }
}