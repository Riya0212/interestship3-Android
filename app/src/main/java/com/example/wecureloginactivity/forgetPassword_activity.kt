package com.wecure.patient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils

import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forget_password.*



class forgetPassword_activity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var userEmail:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        setContentView(R.layout.activity_forget_password)

        textviewGoBack.setOnClickListener{
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        btnSend.setOnClickListener {
            userEmail = textEmail.text.toString()
            if( TextUtils.isEmpty(userEmail)){
                Toast.makeText(this, "Required Field", Toast.LENGTH_LONG).show()
            }else{
                auth.sendPasswordResetEmail(userEmail)
                    .addOnCompleteListener(this, OnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Reset link sent to your email", Toast.LENGTH_LONG)
                                .show()
                        } else {
                            Toast.makeText(this, "Unable to send reset mail", Toast.LENGTH_LONG)
                                .show()
                        }
                    })
            }
        }

    }

}