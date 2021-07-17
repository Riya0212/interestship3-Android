package com.wecure.patient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.wecure.patient.databinding.ActivityForgetPasswordBinding
import kotlinx.android.synthetic.main.activity_forget_password.*


class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var userEmail:String
    private lateinit var binding: ActivityForgetPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        auth = FirebaseAuth.getInstance()
        binding= ActivityForgetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListeners()
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



    private fun setupListeners() {
        binding.textEmail.addTextChangedListener(TextFieldValidation(binding.textEmail))
    }
    private fun validateEmail(): Boolean {
        if (!FieldValidators.isValidEmail(binding.textEmail.text.toString())) {
            binding.textEmail.error = "Invalid Email!"
            binding.textEmail.requestFocus()
            return false
        } else {
            binding.emailLayout.isErrorEnabled = false
        }
        return true
    }
    inner class TextFieldValidation(private val view: View) : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            //check ids of each textfield and apply functions accordigly
            when (view.id){
                R.id.textEmail-> {
                    validateEmail()
                }
            }
        }
    }
}