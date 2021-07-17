package com.wecure.patient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class DoctorLogin : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    lateinit var signInInputsArray: Array<EditText>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_login)
        auth = Firebase.auth


        signInInputsArray = arrayOf(editTextEmail, editTextPassword)


        textViewSignup.setOnClickListener {

            startActivity(Intent(this,DoctorRegistration::class.java))
            finish()
        }
        btnLogin.setOnClickListener {

            doLogin()
        }
        textViewForgot.setOnClickListener{
            val intent= Intent(this,ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

    }
    private fun doLogin() {
        if(editTextEmail.text.toString().isEmpty() || editTextPassword.text.toString().isEmpty()) {
            signInInputsArray.forEach { input ->
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
        auth.signInWithEmailAndPassword(editTextEmail.text.toString(), editTextPassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    updateUI(null)
                }
            }
    }


    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            reload();
        }
    }

    private fun reload() {
    }

    private fun updateUI(currentUser: FirebaseUser?) {

        if(currentUser !=null) {
            if (currentUser.isEmailVerified) {
                startActivity(Intent(this, HomeScreen_Doctor::class.java))
                finish()
            } else {

                Toast.makeText(
                    baseContext, "Please verify your email",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        else{
            Toast.makeText(
                baseContext, "Invalid email or password.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


}
