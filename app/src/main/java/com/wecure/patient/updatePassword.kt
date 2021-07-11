package com.wecure.patient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_update_password.*

class updatePassword : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var userNewPass: String
    private lateinit var userConfirmPass: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_password)
        auth = FirebaseAuth.getInstance()

        btnSet.setOnClickListener {
            userNewPass = editTextPassword.text.toString()
            userConfirmPass = editTextConfirm.text.toString()
            if (TextUtils.isEmpty(userConfirmPass) || TextUtils.isEmpty(userNewPass)) {
                Toast.makeText(this, "Required Field", Toast.LENGTH_LONG).show()
            } else if (userNewPass != userConfirmPass) {
                Toast.makeText(this, "Password dosen't match", Toast.LENGTH_LONG).show()
            } else {
                auth.currentUser?.updatePassword(userNewPass)
                    ?.addOnCompleteListener(this, OnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Password changes successfully", Toast.LENGTH_LONG)
                                .show()
                            finish()
                            val intent= Intent(this,MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, "password not changed", Toast.LENGTH_LONG)
                                .show()
                        }
                    })
            }
        }
    }
}