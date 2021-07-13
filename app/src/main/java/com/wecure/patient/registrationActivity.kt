package com.wecure.patient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.wecure.patient.databinding.ActivityRegistrationBinding
import kotlinx.android.synthetic.main.activity_registration.*

class registrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var auth: FirebaseAuth

    private lateinit var createAccountInputsArray: Array<EditText>
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide() //hide actionbar
        super.onCreate(savedInstanceState)
        binding= ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setupListeners()

        createAccountInputsArray =
            arrayOf(editTextName, editTextPhone, editTextEmail, editTextPassword)
        auth = Firebase.auth

        btnSignup.setOnClickListener {

            signUpUser()
        }



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
   /* private fun isValidate(): Boolean= validateEmail() && validatePassword() && validateName() && validatePhoneNumber()

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
    }*/
   private fun signUpUser(){
       if(editTextEmail.text.toString().isEmpty() || editTextName.text.toString().isEmpty() || editTextPhone.text.toString().isEmpty()
           || editTextPassword.text.toString().isEmpty()) {
           createAccountInputsArray.forEach { input ->
               if (input.text.toString().trim().isEmpty()) {
                   input.error = "Required Field"
                   return
               }

           }
       }
       if(!FieldValidators.isValidEmail(editTextEmail.text.toString())) {
           editTextEmail.error = "Invalid Email!"
           editTextEmail.requestFocus()
       }
       if(editTextPhone.text.toString().length>10) {
           editTextPhone.error = "Number cannot be greater than 10 digits"
           editTextPhone.requestFocus()
           return
       }
       if(editTextPassword.text.toString().length<8){
           editTextPassword.error="password can't be less than 8 digits"
           editTextPassword.requestFocus()
           return
       }
       else if (!FieldValidators.isStringContainNumber(editTextPassword.text.toString())) {
           editTextPassword.error = "Required at least 1 digit"
           editTextPassword.requestFocus()
           return

       } else if (!FieldValidators.isStringLowerAndUpperCase(editTextPassword.text.toString())) {
           editTextPassword.error = "Password must contain upper and lower case letters"
           editTextPassword.requestFocus()
           return
       } else if (!FieldValidators.isStringContainSpecialCharacter(editTextPassword.text.toString())) {
           editTextPassword.error = "Required atleast 1 special character"
           editTextPassword.requestFocus()
           return
       }
       auth.createUserWithEmailAndPassword(editTextEmail.text.toString(), editTextPassword.text.toString())
           .addOnCompleteListener(this) { task ->
               if (task.isSuccessful) {
                   val user = Firebase.auth.currentUser

                   user!!.sendEmailVerification()
                       .addOnCompleteListener { task ->
                           if (task.isSuccessful) {
                               Toast.makeText(
                                   this,
                                   "Account created successfully",
                                   Toast.LENGTH_SHORT
                               ).show()
                               startActivity(Intent(this, MainActivity::class.java))
                               finish()
                           }
                       }
               }

               else {
                   Toast.makeText(baseContext, "Signup failed.Try Again",
                       Toast.LENGTH_SHORT).show()

               }
           }



   }
}