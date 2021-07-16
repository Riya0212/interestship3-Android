package com.wecure.patient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_update_password.*
import kotlinx.android.synthetic.main.activity_update_password.editTextPassword

class UpdatePasswordActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_password)
        auth = FirebaseAuth.getInstance()

        btnSet.setOnClickListener {
            changePassword()
        }
    }

    private fun changePassword() {
        if (editTextCurrentPassword.text.toString().isNotEmpty() &&
            editTextPassword.text.toString().isNotEmpty() &&
            editTextConfirm.text.toString().isNotEmpty()
        ) {
            if(editTextPassword.text.toString().equals(editTextConfirm.text.toString())){
                val user = Firebase.auth.currentUser!!
                if(user!= null && user.email !=null){
                    val credential = EmailAuthProvider
                        .getCredential(user.email!!, editTextCurrentPassword.text.toString())

                    user.reauthenticate(credential)
                        .addOnCompleteListener {
                            if(it.isSuccessful){
                                Toast.makeText(this,"Re-authentication successfull",Toast.LENGTH_SHORT).show()
                                user!!.updatePassword(editTextConfirm.text.toString())
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            Toast.makeText(this,"Password changed successfully",Toast.LENGTH_SHORT).show()
                                            auth.signOut()
                                            startActivity(Intent(this,MainActivity::class.java))
                                            finish()
                                        }
                                    }
                            }
                            else{
                                Toast.makeText(this,"Re-authentication failed",Toast.LENGTH_SHORT).show()
                            }
                        }



                }else{
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()
                }

            }else{
                Toast.makeText(this,"Passwords are not matching",Toast.LENGTH_SHORT).show()
            }

        } else {
            Toast.makeText(this, "Please enter all the fields", Toast.LENGTH_SHORT).show()
        }
    }
}