package com.wecure.patient.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserInfo
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.wecure.patient.HomeScreen
import com.wecure.patient.R
import com.wecure.patient.data.UserInfoData
import com.wecure.patient.databinding.FragmentDashboardBinding
import com.wecure.patient.ui.dashboard_doctor.DashboardViewModel_Doctor
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.fragment_dashboard.view.*

class DashboardFragment : Fragment() {

    private lateinit var txtName: EditText
    private val TYPE_DOCTOR: String = "doctor"
    private val TYPE_PATIENT: String = "patient"
    private lateinit var dashboardViewModel: DashboardViewModel_Doctor
    private var _binding: FragmentDashboardBinding? = null
    private lateinit var auth: FirebaseAuth
    private var mFirebaseDatabaseInstances: FirebaseDatabase?=null
    private var mFirebaseDatabase: DatabaseReference?=null

    private var userId:String?=null
    private var user: UserInfoData?=null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel_Doctor::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val view = inflater!!.inflate(R.layout.fragment_dashboard, container, false)
        mFirebaseDatabaseInstances= FirebaseDatabase.getInstance()
        mFirebaseDatabase=mFirebaseDatabaseInstances!!.getReference("users")
        auth = Firebase.auth
        val user=FirebaseAuth.getInstance().currentUser
        userId=user!!.uid
      // val myUser= UserInfoData(view.editTextNamePatient.text.toString(),view.editTextPhonePatient.text.toString(),view.editTextGenderPatient.text.toString(),view.editTextWeight.text.toString(),view.editTextHeight.text.toString(),view.editTextBloodPatient.text.toString())

        root.btnSubmitProfile?.setOnClickListener {

            txtName = view?.findViewById<EditText>(R.id.editTextNamePatient)!!
            //mFirebaseDatabase!!.child(userId!!).setValue(myUser)
            Log.v("inside","Inside Button code "+(txtName.text.toString()))
            mFirebaseDatabase!!.child(userId!!).child("name").setValue(view?.editTextNamePatient?.text.toString())
            mFirebaseDatabase!!.child(userId!!).child("phoneNumber").setValue(view?.editTextPhonePatient?.text.toString())
            mFirebaseDatabase!!.child(userId!!).child("gender").setValue(view?.editTextGenderPatient?.text.toString())
            mFirebaseDatabase!!.child(userId!!).child("weight").setValue(view?.editTextWeight?.text.toString())
            mFirebaseDatabase!!.child(userId!!).child("height").setValue(view?.editTextHeight?.text.toString())
            mFirebaseDatabase!!.child(userId!!).child("bloodGroup").setValue(view?.editTextBloodPatient?.text.toString())

            Toast.makeText(
                context,
                getString(R.string.profile_updated),
                Toast.LENGTH_SHORT
            ).show()
                   }


        return root
    }

    private fun loadData() {
        mFirebaseDatabase?.child(auth.currentUser?.uid.toString())
            ?.get()
            ?.addOnCompleteListener(OnCompleteListener {
                if(it.isSuccessful)
                {
                    var name : String = it.result?.child("name")?.value.toString();
                    var weight : String = it.result?.child("weight")?.value.toString();
                    var gender : String = it.result?.child("gender")?.value.toString();
                    var phone : String = it.result?.child("phoneNumber")?.value.toString();
                    var height:String= it.result?.child("height")?.value.toString()
                    var bloodGroup:String=it.result?.child("bloodgroup")?.value.toString()
                    var email:String=it.result?.child("email")?.value.toString()
                    var category:String =it.result?.child("category")?.value.toString()

                        user = UserInfoData(email,name,category, phone, gender, weight,height,bloodGroup)
                        updateUI()


                }
                else
                {
                    it.exception?.printStackTrace()
                }
            })
    }
private fun updateData(){



}
    private fun updateUI() {
        System.out.println("us = " + user?.height+user?.name)
        view?.editTextNamePatient?.setText(user?.name)
        view?.editTextPhonePatient?.setText(user?.phoneNumber)
        if (user?.height=="null"||user?.height==null){
            view?.editTextHeight?.hint = "Enter your height"
        }
        else
        {
            view?.editTextHeight?.setText(user?.height)
        }
        if (user?.weight==" "||user?.weight==null){
            view?.editTextWeight?.hint = "Enter your weight"
        }
        else{
            view?.editTextWeight?.setText(user?.weight)

        }
        if (user?.bloodGroup==" "||user?.bloodGroup==null){
            view?.editTextBloodPatient?.hint = "Enter your blood group"
        }
        else
        {
            view?.editTextBloodPatient?.setText(user?.bloodGroup)
        }
        if (user?.gender==" "||user?.gender==null){
            view?.editTextGenderPatient?.hint = "Enter your gender"
        }
        else{
            view?.editTextGenderPatient?.setText(user?.gender)

        }
       // textName.text = user?.name
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }
    override fun onResume() {
        super.onResume()
        loadData();
    }
}