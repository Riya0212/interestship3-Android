package com.wecure.patient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.wecure.patient.data.UserInfoData
import kotlinx.android.synthetic.main.activity_doctor_registration.*
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.activity_registration.btnSignup

class DoctorRegistration : AppCompatActivity() {
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
        setContentView(R.layout.activity_doctor_registration)
        createAccountInputsArray =
            arrayOf(editTextDoctorName!!, editTextDoctorPhone!!, editTextDoctorEmail!!, editTextDoctorPassword!!,editTextDoctorSpecialization!!)
        auth = Firebase.auth
        mFirebaseDatabaseInstances= FirebaseDatabase.getInstance()

        val bundle = intent.extras
        catSelected = bundle!!.getString(getString(R.string.bundle_category))

        if(auth.currentUser!=null){
            startActivity(Intent(this,HomeScreen_Doctor::class.java))
            finish()
        }
        btnSignup.setOnClickListener {

            signUpUser()
        }

    }


    private fun signUpUser(){
        if(editTextDoctorEmail.text.toString().isEmpty() || editTextDoctorName.text.toString().isEmpty() || editTextDoctorPhone.text.toString().isEmpty() ||editTextDoctorSpecialization.text.toString().isEmpty()
            || editTextDoctorPassword.text.toString().isEmpty()) {
            createAccountInputsArray.forEach { input ->
                if (input.text.toString().trim().isEmpty()) {
                    input.error = "Required Field"
                    return
                }

            }
        }
        if(!FieldValidators.isValidEmail(editTextDoctorEmail.text.toString())) {
            editTextDoctorEmail.error = getString(R.string.invald_email)
            editTextDoctorEmail.requestFocus()

        }

        if(editTextDoctorPhone.text.toString().length>10) {
            editTextDoctorPhone.error = getString(R.string.length_greater_than_10)
            editTextDoctorPhone.requestFocus()
            return
        }

        if(editTextDoctorPassword.text.toString().length<8){
            editTextDoctorPassword.error=getString(R.string.length_less_than_8)
            editTextDoctorPassword.requestFocus()
            return
        }
        else if (!FieldValidators.isStringContainNumber(editTextDoctorPassword.text.toString())) {
            editTextDoctorPassword.error = getString(R.string.required_1_digit)
            editTextDoctorPassword.requestFocus()
            return

        } else if (!FieldValidators.isStringLowerAndUpperCase(editTextDoctorPassword.text.toString())) {
            editTextDoctorPassword.error = getString(R.string.must_contain_upper_lower)
            editTextDoctorPassword.requestFocus()
            return
        } else if (!FieldValidators.isStringContainSpecialCharacter(editTextDoctorPassword.text.toString())) {
            editTextDoctorPassword.error = getString(R.string.special_character_required)
            editTextDoctorPassword.requestFocus()
            return
        }
        // System.out.println("uname pwd = " + editTextEmail.text.toString() + " " + editTextPassword.text.toString())
        auth.createUserWithEmailAndPassword(editTextDoctorEmail.text.toString(), editTextDoctorPassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = Firebase.auth.currentUser

                    user!!.sendEmailVerification()
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                mFirebaseDatabase=mFirebaseDatabaseInstances!!.getReference("users")
                                val user= FirebaseAuth.getInstance().currentUser
                                userId=user!!.uid
                                emailAddress=user.email

                                val myUser= UserInfoData(editTextDoctorName.text.toString(),catSelected.toString(),editTextDoctorPhone.text.toString(),editTextDoctorEmail.text.toString(),editTextDoctorSpecialization.text.toString())
                                mFirebaseDatabase!!.child(userId!!).setValue(myUser)


                                Toast.makeText(
                                    this,
                                    getString(R.string.account_created_successfully),
                                    Toast.LENGTH_SHORT
                                ).show()

                                    startActivity(Intent(this, HomeScreen_Doctor::class.java))
                                    finish()



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