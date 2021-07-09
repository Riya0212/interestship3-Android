package com.wecure.patient



import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.wecure.patient.FieldValidators.isValidEmail
import com.wecure.patient.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide() //hide actionbar
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupListeners()

        binding.textViewSignup.setOnClickListener {
            val intent = Intent(this, registrationActivity::class.java)
            startActivity(intent)
        }
        binding.textViewForgot.setOnClickListener{
            val intent= Intent(this,forgetPassword_activity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val intent= Intent(this,HomeScreen::class.java)
            startActivity(intent)
        }




    }
    private fun isValidate(): Boolean= validateEmail() && validatePassword()

    private fun setupListeners(){
        binding.editTextEmail.addTextChangedListener(TextFieldValidation(binding.editTextEmail))
        binding.editTextPassword.addTextChangedListener(TextFieldValidation(binding.editTextPassword))
    }


    //validation check for username
    private fun validateEmail(): Boolean {
        if (binding.editTextEmail.text.toString().trim().isEmpty()) {
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
        }
    }

}

