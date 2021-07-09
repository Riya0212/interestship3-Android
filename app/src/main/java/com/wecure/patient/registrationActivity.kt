package com.wecure.patient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.wecure.patient.databinding.ActivityRegistrationBinding

class registrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide() //hide actionbar
        super.onCreate(savedInstanceState)
        binding= ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListeners()
        binding.btnSignup.setOnClickListener {
            val intent= Intent(this,userProfile::class.java)
            startActivity(intent)
        }
        binding.textViewLogin.setOnClickListener {
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)

        }
        binding.btnSignup.setOnClickListener {
            val intent= Intent(this,HomeScreen::class.java)
            startActivity(intent)
        }
    }
    private fun isValidate(): Boolean= validateEmail() && validatePassword() && validateName() && validatePhoneNumber()

    private fun setupListeners(){
        binding.editTextName.addTextChangedListener(TextFieldValidation(binding.editTextName))
        binding.editTextEmail.addTextChangedListener(TextFieldValidation(binding.editTextEmail))
        binding.editTextPhone.addTextChangedListener(TextFieldValidation(binding.editTextPhone))
        binding.editTextPassword.addTextChangedListener(TextFieldValidation(binding.editTextPassword))
    }
    //validation check for name
    private fun validateName(): Boolean{
        if(binding.editTextName.text.toString().trim().isEmpty()){
            binding.editTextName.error="Required Field!"
            binding.editTextName.requestFocus()
            return false
        }
        else{
            binding.nameLayout.isErrorEnabled=false
        }
        return true

    }
    //validation check for phonenumber
    private fun validatePhoneNumber(): Boolean{
        if(binding.editTextPhone.text.toString().trim().isEmpty()){
            binding.editTextPhone.error="Required Field!"
            binding.editTextPhone.requestFocus()
            return false
        }
        else if(binding.editTextPhone.text.toString().length>10){
            binding.editTextPhone.error="Number cannot be greater than 10 digits"
            binding.editTextPhone.requestFocus()
            return false
        }
        else{
            binding.phoneLayout.isErrorEnabled=false
        }
        return true
    }
    //validation check for password
    private fun validatePassword(): Boolean{
        if(binding.editTextPassword.text.toString().trim().isEmpty()){
            binding.editTextPassword.error="Required Field!"
            binding.editTextPassword.requestFocus()
            return false
        }
        else if(binding.editTextPassword.text.toString().length<8){
            binding.editTextPassword.error="password can't be less than 8 digits"
            binding.editTextPassword.requestFocus()
            return false
        }
        else if (!FieldValidators.isStringContainNumber(binding.editTextPassword.text.toString())) {
            binding.editTextPassword.error = "Required at least 1 digit"
            binding.editTextPassword.requestFocus()
            return false
        } else if (!FieldValidators.isStringLowerAndUpperCase(binding.editTextPassword.text.toString())) {
            binding.editTextPassword.error = "Password must contain upper and lower case letters"
            binding.editTextPassword.requestFocus()
            return false
        } else if (!FieldValidators.isStringContainSpecialCharacter(binding.editTextPassword.text.toString())) {
            binding.editTextPassword.error="Required atleast 1 special character"
            binding.editTextPassword.requestFocus()
            return false
        } else {
            binding.passwordLayout2.isErrorEnabled = false
        }
        return true
    }
    //validation for email
    private fun validateEmail(): Boolean {
        if (binding.editTextEmail.text.toString().trim().isEmpty()) {
            binding.editTextEmail.error = "Required Field!"
            binding.editTextEmail.requestFocus()
            return false
        } else if (!FieldValidators.isValidEmail(binding.editTextEmail.text.toString())) {
            binding.editTextEmail.error = "Invalid Email!"
            binding.editTextEmail.requestFocus()
            return false
        } else {
            binding.emailLayout2.isErrorEnabled = false
        }
        return true
    }
    //private val textWatcher= object : TextWatcher{
    inner class TextFieldValidation(private val view: View) : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            //check ids of each textfield and apply functions accordigly
            when (view.id){
                R.id.editTextEmail-> {
                    validateEmail()
                }
                R.id.editTextPassword->{
                    validatePassword()
                }
                R.id.editTextName->{
                    validateName()
                }
                R.id.editTextPhone->{
                    validatePhoneNumber()
                }
            }
        }
    }

}