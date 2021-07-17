package com.wecure.patient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.activity_registration.editTextEmail
import kotlinx.android.synthetic.main.activity_registration.editTextPassword


class RegistrationActivity : AppCompatActivity() {
    var catSelected:String? = null
    private lateinit var auth: FirebaseAuth

    private lateinit var createAccountInputsArray: Array<EditText>

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide() //hide actionbar
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        createAccountInputsArray =
            arrayOf(editTextName, editTextPhone, editTextEmail, editTextPassword)
        auth = Firebase.auth

        // val bundle = intent.extras
        //catSelected = bundle!!.getString(getString(R.string.bundle_category))

        btnSignup.setOnClickListener {
            signUpUser()
        }
        textViewLogin.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
        id_login.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

    }

    private fun saveFirestore(userName: String, userPhone: String, userEmail: String, userPassword: String) {
        val db= FirebaseFirestore.getInstance()
        val user: MutableMap<String,Any> = HashMap()
        user["userName"]= userName
        user["userPhone"]= userPhone
        user["userEmail"]= userEmail
        user["userPassword"]= userPassword

        db.collection("users")
            .add(user)
            .addOnSuccessListener {
                Toast.makeText(this@RegistrationActivity," Data added successfully",Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{
                Toast.makeText(this@RegistrationActivity," Data failed to add",Toast.LENGTH_SHORT).show()
            }



    }


    private fun signUpUser(){
        val userName=editTextName.text.toString()
        val userPhone=editTextPhone.text.toString()
        val userEmail=editTextEmail.text.toString()
        val userPassword=editTextPassword.text.toString()
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
        saveFirestore(userName,userPhone,userEmail,userPassword)
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

