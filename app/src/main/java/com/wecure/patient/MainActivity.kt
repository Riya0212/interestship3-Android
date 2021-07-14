package com.wecure.patient



import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.wecure.patient.FieldValidators.isValidEmail
import com.wecure.patient.data.UserInfoData
import com.wecure.patient.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    lateinit var signInInputsArray: Array<EditText>
    private var mFirebaseDatabase: DatabaseReference?=null
    private var mFirebaseInstance: FirebaseDatabase?=null
    var userId:String?=null
    var catSelected:String? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide() //hide actionbar
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = Firebase.auth
        //Getting instances of FirebaseDatabase
        mFirebaseInstance= FirebaseDatabase.getInstance()

        //get reference to 'users' node
        mFirebaseDatabase=mFirebaseInstance!!.getReference("users")

        val user=FirebaseAuth.getInstance().currentUser

        //add it only if it is not saved to database
        if (user != null) {
            userId=user.uid
        }
        val bundle = intent.extras
        catSelected = bundle!!.getString(getString(R.string.bundle_category))
        signInInputsArray = arrayOf(editTextEmail, editTextPassword)


        textViewSignup.setOnClickListener {
            bundle.putString(getString(R.string.bundle_category), catSelected)
            if(catSelected.equals("doctor")){
                val intent = Intent(this, DoctorRegistration::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
                finish()
            }
            else
            {
                val intent = Intent(this, registrationActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
                finish()
            }

        }
        btnLogin.setOnClickListener {
           /* startActivity(Intent(this,HomeScreen::class.java))
            finish()

           */
           // progressBar.visibility=View.VISIBLE

            doLogin()
        }
        textViewForgot.setOnClickListener{
            val intent= Intent(this,forgetPassword_activity::class.java)
            startActivity(intent)
        }

    }
    override fun onResume() {
        super.onResume()
      //  progressBar.visibility= View.GONE
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
                val user = UserInfoData()
                mFirebaseDatabase!!.child(userId.toString()).get().addOnCompleteListener(
                    OnCompleteListener {
                        if (it.isSuccessful){
                         Log.v("values ",it.result?.value.toString())
                            val hashMap: HashMap<String,String> = it.result?.value as HashMap<String, String>
                            if (hashMap.get("category").equals("doctor"))
                            {
                                startActivity(Intent(this, HomeScreen_Doctor::class.java))
                                finish()
                            }
                            else
                            {
                                startActivity(Intent(this, HomeScreen::class.java))
                                finish()
                            }


                        }
                        else{
                            it.exception?.printStackTrace()
                        }
                    })

                            } else {

                Toast.makeText(
                    baseContext, "Please verify your email",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        else{
            Toast.makeText(
                baseContext, "Login failed",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


}

