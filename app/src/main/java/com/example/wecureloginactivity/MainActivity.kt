package com.wecure.patient


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.wecure.patient.Extensions.toast
import com.wecure.patient.FireBaseUtils.firebaseAuth
import com.wecure.patient.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var signInEmail: String
    lateinit var signInPassword: String
    private lateinit var binding: ActivityMainBinding
    lateinit var signInInputsArray: Array<EditText>

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide() //hide actionbar
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        signInInputsArray = arrayOf(editTextEmail, editTextPassword)


        binding.textViewSignup.setOnClickListener {
            val intent = Intent(this, registrationActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogin.setOnClickListener {

            isValidate()
        }
        binding.textViewForgot.setOnClickListener{
            val intent= Intent(this,forgetPassword_activity::class.java)
            startActivity(intent)
        }

    }
    private fun notEmpty(): Boolean = signInEmail.isNotEmpty() && signInPassword.isNotEmpty()

    private fun isValidate(){
        signInEmail = editTextEmail.text.toString().trim()
        signInPassword = editTextPassword.text.toString().trim()
        if (notEmpty()) {
            firebaseAuth.signInWithEmailAndPassword(signInEmail,signInPassword)
                .addOnCompleteListener { signIn ->
                    if (signIn.isSuccessful) {
                        startActivity(Intent(this, registrationActivity::class.java))
                        toast("signed in successfully")
                        finish()
                    } else {
                        toast("sign in failed")
                    }
                }
        }
        else {
            signInInputsArray.forEach { input ->
                if (input.text.toString().trim().isEmpty()) {
                    input.error = "Required Field"
                       // "${input.hint} is required"
                }
            }
    }

/*
//validation check for username
    private fun validateEmail(): Boolean {
        if (binding.editTextEmail.text.toString().trim()) {
            binding.editTextEmail.error = "Required Field!"
            binding.editTextEmail.requestFocus()
            return false
        } else if (!isValidEmail(binding.editTextEmail.text.toString())) {
            binding.editTextEmail.error = "Invalid Email!"
            binding.editTextEmail.requestFocus()
            return false
        } else {
            binding.emailLayout.isErrorEnabled = false
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
        else {
            binding.passwordLayout.isErrorEnabled = false
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
            }
        }*/
    }

}



