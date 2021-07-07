package com.example.wecureloginactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.wecureloginactivity.databinding.ActivityForgetPasswordBinding


class forgetPassword_activity : AppCompatActivity() {
    private lateinit var binding: ActivityForgetPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityForgetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListeners()
        binding.textviewGoBack.setOnClickListener{
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
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