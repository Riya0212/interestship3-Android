package com.wecure.patient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener

import com.google.firebase.auth.FirebaseAuth

import kotlinx.android.synthetic.main.activity_registration.*

class registrationActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    private lateinit var userName: String
    private lateinit var userEmail: String
    private lateinit var userPassword: String
    private lateinit var userPhone: String
    private lateinit var createAccountInputsArray: Array<EditText>

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide() //hide actionbar
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        createAccountInputsArray =
            arrayOf(editTextName, editTextPhone, editTextEmail, editTextPassword)

        auth = FirebaseAuth.getInstance()

        btnSignup.setOnClickListener {
            userName = editTextName.text.toString()
            userPhone = editTextPhone.text.toString()
            userEmail = editTextEmail.text.toString()
            userPassword = editTextPassword.text.toString()

            if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(userPhone) || TextUtils.isEmpty(
                    userEmail
                ) || TextUtils.isEmpty(userPassword)
            )
                createAccountInputsArray.forEach { input ->
                    if (input.text.toString().trim().isEmpty()) {
                        input.error = "Required Field"
                    } else {
                        auth.signInWithEmailAndPassword(userEmail, userPassword)
                            .addOnCompleteListener(this, OnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(
                                        this,
                                        "Account Created successfully",
                                        Toast.LENGTH_LONG
                                    )
                                        .show()
                                    val intent = Intent(this, MainActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(
                                        this,
                                        "Failed to authenticate",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            })
                    }
                }
/*
        textViewLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
    }

        override fun onStart() {
            super.onStart()
            val user: FirebaseUser? = firebaseAuth.currentUser
            user?.let {
                startActivity(Intent(this, userProfile::class.java))
                toast("Welcome!")
            }
        }
    private fun notEmpty(): Boolean = editTextName.text.toString().trim().isNotEmpty() &&
            editTextPhone.text.toString().trim().isNotEmpty()&&
        editTextEmail.text.toString().trim().isNotEmpty() &&
            editTextPassword.text.toString().trim().isNotEmpty()

    private fun identicalPassword():Boolean{
        var identical = false
        if (notEmpty()) {
            identical = true
        } else if (!notEmpty()) {
            createAccountInputsArray.forEach { input ->
                if (input.text.toString().trim().isEmpty()) {
                    input.error = "Required Field"
                }
            }
        }
        return identical
    }


    private fun signIn() {
        if (identicalPassword() ){
            auth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(this, OnCompleteListener { task ->
                if(task.isSuccessful) {
                    Toast.makeText(this, "Account Created successfully", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }else {
                    Toast.makeText(this, "Failed to authenticate", Toast.LENGTH_LONG).show()
                }
            })

        }
    }
    private fun sendEmailVerification() {
        firebaseUser?.let {
            it.sendEmailVerification().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    toast("email sent to $userEmail")
                }
            }
        }
    }


    private fun isValidate(): Boolean= validateEmail() && validatePassword() && validateName() && validatePhoneNumber()


    //validation check for name
    private fun validateName(): Boolean{
        if(editTextName.text.toString().trim().isEmpty()){
            editTextName.error="Required Field!"
            editTextName.requestFocus()
            return false
        }
        else{
            nameLayout.isErrorEnabled=false
        }
        return true

    }
    //validation check for phonenumber
    private fun validatePhoneNumber(): Boolean{
        if(editTextPhone.text.toString().trim().isEmpty()){
            editTextPhone.error="Required Field!"
            editTextPhone.requestFocus()
            return false
        }
        else if(editTextPhone.text.toString().length>10){
            editTextPhone.error="Number cannot be greater than 10 digits"
            editTextPhone.requestFocus()
            return false
        }
        else{
            phoneLayout.isErrorEnabled=false
        }
        return true
    }
    //validation check for password
    private fun validatePassword(): Boolean{
        if(editTextPassword.text.toString().trim().isEmpty()){
            editTextPassword.error="Required Field!"
            editTextPassword.requestFocus()
            return false
        }
        else if(editTextPassword.text.toString().length<8){
            editTextPassword.error="password can't be less than 8 digits"
            editTextPassword.requestFocus()
            return false
        }
        else if (!FieldValidators.isStringContainNumber(editTextPassword.text.toString())) {
            editTextPassword.error = "Required at least 1 digit"
            editTextPassword.requestFocus()
            return false
        } else if (!FieldValidators.isStringLowerAndUpperCase(editTextPassword.text.toString())) {
            editTextPassword.error = "Password must contain upper and lower case letters"
            editTextPassword.requestFocus()
            return false
        } else if (!FieldValidators.isStringContainSpecialCharacter(editTextPassword.text.toString())) {
            editTextPassword.error="Required atleast 1 special character"
            editTextPassword.requestFocus()
            return false
        } else {
            passwordLayout2.isErrorEnabled = false
        }
        return true
    }
    //validation for email
    private fun validateEmail(): Boolean {
        if (editTextEmail.text.toString().trim().isEmpty()) {
            editTextEmail.error = "Required Field!"
            editTextEmail.requestFocus()
            return false
        } else if (!FieldValidators.isValidEmail(editTextEmail.text.toString())) {
            editTextEmail.error = "Invalid Email!"
            editTextEmail.requestFocus()
            return false
        } else {
            emailLayout2.isErrorEnabled = false
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
        }*/
        }
    }
}

