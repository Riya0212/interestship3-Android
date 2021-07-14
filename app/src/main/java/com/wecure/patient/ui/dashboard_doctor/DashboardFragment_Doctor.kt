package com.wecure.patient.ui.dashboard_doctor

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.wecure.patient.R
import com.wecure.patient.data.DoctorInfoData
import com.wecure.patient.data.UserInfoData
import com.wecure.patient.databinding.FragmentDashboardBinding
import kotlinx.android.synthetic.main.activity_doctor_registration.view.*
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import kotlinx.android.synthetic.main.fragment_dashboard.view.editTextWeight
import kotlinx.android.synthetic.main.fragment_dashboard_doctor.view.*

class DashboardFragment_Doctor : Fragment() {

    private lateinit var txtName: EditText
    private lateinit var dashboardViewModel: DashboardViewModel_Doctor
    private var _binding: FragmentDashboardBinding? = null
    private lateinit var auth: FirebaseAuth
    private var mFirebaseDatabaseInstances: FirebaseDatabase?=null
    private var mFirebaseDatabase: DatabaseReference?=null
    private var userId:String?=null
    private var user: DoctorInfoData?=null
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
        root.btnSubmitProfile?.setOnClickListener {
            val view = inflater!!.inflate(R.layout.fragment_dashboard_doctor, container, false)
            mFirebaseDatabaseInstances= FirebaseDatabase.getInstance()
            mFirebaseDatabase=mFirebaseDatabaseInstances!!.getReference("users")
            auth = Firebase.auth
            val user= FirebaseAuth.getInstance().currentUser
            userId=user!!.uid
            //txtName = view?.findViewById<EditText>(R.id.editTextNamePatient)!!
            //mFirebaseDatabase!!.child(userId!!).setValue(myUser)
            Log.v("inside","Inside Button code "+(txtName.text.toString()))
            mFirebaseDatabase!!.child(userId!!).child("name").setValue(view?.editTextName?.text.toString())
            mFirebaseDatabase!!.child(userId!!).child("phoneNumber").setValue(view?.editTextPhone?.text.toString())
            mFirebaseDatabase!!.child(userId!!).child("specialization").setValue(view?.editTextDoctorSpecialization?.text.toString())
            mFirebaseDatabase!!.child(userId!!).child("gender").setValue(view?.editTextGender?.text.toString())
            mFirebaseDatabase!!.child(userId!!).child("yearsOfExperience").setValue(view?.editTextYears?.text.toString())
            mFirebaseDatabase!!.child(userId!!).child("introduction").setValue(view?.editTextintro_doctor?.text.toString())

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
                    var introduction : String = it.result?.child("introduction")?.value.toString();
                    var gender : String = it.result?.child("gender")?.value.toString();
                    var phone : String = it.result?.child("phoneNumber")?.value.toString();
                    var yearsOfExperience:String= it.result?.child("yearsOfExperience")?.value.toString()
                    var specialization:String=it.result?.child("specialization")?.value.toString()
                    var email:String=it.result?.child("email")?.value.toString()
                    var category:String =it.result?.child("category")?.value.toString()

                    user = DoctorInfoData(
                        name,
                        phone,
                        specialization,
                        gender,
                        yearsOfExperience,
                        introduction
                    )
                    updateUI()


                }
                else
                {
                    it.exception?.printStackTrace()
                }
            })
    }

    private fun updateUI() {
        System.out.println("us = " + user?.yearsOfExperience+user?.name)
        view?.editTextName?.setText(user?.name)
        view?.editTextPhone?.setText(user?.phoneNumber)
        if (user?.introduction=="null"||user?.introduction==null){
            view?.editTextintro_doctor?.hint = "Enter your introduction"
        }
        else
        {
            view?.editTextintro_doctor?.setText(user?.introduction)
        }
        if (user?.specialization==" "||user?.specialization==null){
            view?.editTextDoctorSpecialization?.hint = "Enter your specialization"
        }
        else{
            view?.editTextDoctorSpecialization?.setText(user?.specialization)

        }
        if (user?.gender==" "||user?.gender==null){
            view?.editTextGender?.hint = "Enter your gender"
        }
        else
        {
            view?.editTextGender?.setText(user?.gender)
        }
        if (user?.yearsOfExperience==" "||user?.yearsOfExperience==null){
            view?.editTextYears?.hint = "Enter your Experience"
        }
        else{
            view?.editTextYears?.setText(user?.yearsOfExperience)

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