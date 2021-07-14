package com.wecure.patient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

@Suppress("DEPRECATION")
class SplashScreen : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private var mFirebaseDatabase: DatabaseReference?=null
    private var mFirebaseInstance: FirebaseDatabase?=null
    var userId:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()
        auth = Firebase.auth
        mFirebaseInstance= FirebaseDatabase.getInstance()
        val user=FirebaseAuth.getInstance().currentUser


        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        Handler().postDelayed({
            if (user != null) {
                userId=user.uid
            }
            else
            {
                val intent = Intent(this, SelectCategory::class.java)
                startActivity(intent)
                finish()
            }
            //get reference to 'users' node
            mFirebaseDatabase=mFirebaseInstance!!.getReference("users")

            // val firebaseUser = Firebase.auth.currentUser
            if(auth!!.currentUser!=null){

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


            }





        }, 3000) // 3000 is the delayed time in milliseconds.
    }
}