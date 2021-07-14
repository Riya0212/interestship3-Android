package com.wecure.patient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.UserData
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.wecure.patient.data.UserInfoData
import kotlinx.android.synthetic.main.activity_registration.*

class registrationActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private var mFirebaseDatabaseInstances: FirebaseDatabase?=null
    private var mFirebaseDatabase: DatabaseReference?=null
    private lateinit var createAccountInputsArray: Array<EditText>
    private var userId:String?=null
    private var emailAddress:String?=null
    var catSelected:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide() //hide actionbar
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        createAccountInputsArray =
            arrayOf(editTextName, editTextPhone, editTextEmail, editTextPassword)
        auth = Firebase.auth
        mFirebaseDatabaseInstances= FirebaseDatabase.getInstance()

        val bundle = intent.extras
        catSelected = bundle!!.getString(getString(R.string.bundle_category))

        if(auth!!.currentUser!=null){
            startActivity(Intent(this,HomeScreen::class.java))
            finish()
        }
        btnSignup.setOnClickListener {

            signUpUser()
        }

    }


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
            editTextEmail.error = getString(R.string.invald_email)
            editTextEmail.requestFocus()
        }
        if(editTextPhone.text.toString().length>10) {
            editTextPhone.error = getString(R.string.length_greater_than_10)
            editTextPhone.requestFocus()
            return
        }
        if(editTextPassword.text.toString().length<8){
            editTextPassword.error=getString(R.string.length_less_than_8)
            editTextPassword.requestFocus()
            return
        }
        else if (!FieldValidators.isStringContainNumber(editTextPassword.text.toString())) {
            editTextPassword.error = getString(R.string.required_1_digit)
            editTextPassword.requestFocus()
            return

        } else if (!FieldValidators.isStringLowerAndUpperCase(editTextPassword.text.toString())) {
            editTextPassword.error = getString(R.string.must_contain_upper_lower)
            editTextPassword.requestFocus()
            return
        } else if (!FieldValidators.isStringContainSpecialCharacter(editTextPassword.text.toString())) {
            editTextPassword.error = getString(R.string.special_character_required)
            editTextPassword.requestFocus()
            return
        }
       // System.out.println("uname pwd = " + editTextEmail.text.toString() + " " + editTextPassword.text.toString())
        auth.createUserWithEmailAndPassword(editTextEmail.text.toString(), editTextPassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = Firebase.auth.currentUser

                    user!!.sendEmailVerification()
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                mFirebaseDatabase=mFirebaseDatabaseInstances!!.getReference("users")
                                val user=FirebaseAuth.getInstance().currentUser
                                userId=user!!.uid
                                emailAddress=user.email

                                val myUser=UserInfoData(editTextName.text.toString(),catSelected.toString(),editTextPhone.text.toString(),editTextEmail.text.toString())
                                mFirebaseDatabase!!.child(userId!!).setValue(myUser)


                                Toast.makeText(
                                    this,
                                    getString(R.string.account_created_successfully),
                                    Toast.LENGTH_SHORT
                                ).show()
                                if(catSelected.equals("doctor"))
                                {
                                    startActivity(Intent(this, HomeScreen_Doctor::class.java))
                                    finish()
                                }
                                else{

                                    startActivity(Intent(this, HomeScreen::class.java))
                                    finish()
                                }

                            }
                        }
                }

                else {
                    task.exception?.printStackTrace();
                    Toast.makeText(baseContext, getString(R.string.signup_failed),
                        Toast.LENGTH_SHORT).show()

                }
            }
    }

}