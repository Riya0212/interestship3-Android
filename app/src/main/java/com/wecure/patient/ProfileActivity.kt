package com.wecure.patient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_registration.*

class ProfileActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        auth = Firebase.auth
        val back= findViewById<ImageView>(R.id.id_login)
        back.setOnClickListener{
            startActivity(Intent(this,HomeScreen::class.java))
            finish()
        }

        btnSubmitProfile.setOnClickListener {
            updateProfile()
            startActivity(Intent(this,HomeScreen::class.java))
            finish()
        }

    }
    private fun saveFirestore(userName: String, userPhone: String, userGender: String, userBlood: String,userHeight:String,userWeight: String) {
        val db= FirebaseFirestore.getInstance()
        val user: MutableMap<String,Any> = HashMap()
        user["userName"]= userName
        user["userPhone"]= userPhone
        user["userGender"]= userGender
        user["userBlood"]= userBlood
        user["userHeight"]=userHeight
        user["userWeight"]=userWeight

        db.collection("Profile")
            .add(user)
            .addOnSuccessListener {
                Toast.makeText(this@ProfileActivity," Profile Updated", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{
                Toast.makeText(this@ProfileActivity," Data failed to add", Toast.LENGTH_SHORT).show()
            }



    }
    private fun updateProfile() {
        val userName=editTextNamePatient.text.toString()
        val userPhone=editTextPhonePatient.text.toString()
        val userGender=editTextGenderPatient.text.toString()
        val userBlood=editTextBloodPatient.text.toString()
        val userHeight=editTextHeight.text.toString()
        val userWeight=editTextWeight.text.toString()
        saveFirestore(userName,userPhone,userGender,userBlood,userHeight, userWeight)


    }
}