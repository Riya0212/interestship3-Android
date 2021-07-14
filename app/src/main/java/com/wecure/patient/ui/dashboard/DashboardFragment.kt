package com.wecure.patient.ui.dashboard

import android.content.Intent
import android.os.Bundle
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
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.fragment_dashboard.view.*

class DashboardFragment : Fragment() {

    private val TYPE_DOCTOR: String = "doctor"
    private val TYPE_PATIENT: String = "patient"
    private lateinit var dashboardViewModel: DashboardViewModel_Doctor
    private var _binding: FragmentDashboardBinding? = null
    private lateinit var auth: FirebaseAuth
    private var mFirebaseDatabaseInstances: FirebaseDatabase?=null
    private var mFirebaseDatabase: DatabaseReference?=null
    private lateinit var editProfileInputArray: Array<EditText>
    private lateinit var editTextName1: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPhone1: EditText
    //private lateinit var textName: TextView

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

        /*//val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
           // textView.text = it
        })*/
        val view = inflater!!.inflate(R.layout.fragment_dashboard, container, false)
       // textName = view.textName
        editTextName1 = view.findViewById(R.id.editTextNamePatient)
        editTextPhone1 = view.findViewById(R.id.editTextPhonePatient)

        //editProfileInputArray =
            //arrayOf(view.editTextName, view.editTextPhone, view.editTextGender, view.editTextBlood,view.editTextWeight,view.editTextHeight)
        mFirebaseDatabaseInstances= FirebaseDatabase.getInstance()
        mFirebaseDatabase=mFirebaseDatabaseInstances!!.getReference("users")
        auth = Firebase.auth



       val buttonSubmit = view.findViewById<Button>(R.id.btnSubmitProfile)


            buttonSubmit.setOnClickListener {
            val user=FirebaseAuth.getInstance().currentUser


            val myUser= UserInfoData(view.editTextNamePatient.text.toString(),view.editTextPhonePatient.text.toString(),view.editTextPhonePatient.text.toString(),view.editTextPhonePatient.text.toString(),view.editTextWeight.text.toString(),view.editTextHeight.text.toString())
            userId=user!!.uid
            mFirebaseDatabase!!.child(userId!!).setValue(myUser)


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
                    var name : String = it.result?.child("name")?.getValue().toString();
                    var weight : String = it.result?.child("weight")?.getValue().toString();
                    var gender : String = it.result?.child("gender")?.getValue().toString();
                    var phone : String = it.result?.child("phone")?.getValue().toString();
                    var height:String= it.result?.child("height")?.getValue().toString()
                    var bloodGroup:String=it.result?.child("bloodgroup")?.getValue().toString()
                        user = UserInfoData(name, weight, phone, gender,height,bloodGroup)
                        updateUI()


                }
                else
                {
                    it.exception?.printStackTrace()
                }
            })
    }

    private fun updateUI() {
        System.out.println("us = " + user?.height+user?.name)
        view?.editTextNamePatient?.setText(user?.name)
        view?.editTextPhonePatient?.setText(user?.phoneNumber)
        if (user?.height=="null"||user?.height==null){
            view?.editTextHeight?.hint = "Enter your height"
        }
        if (user?.weight=="null"||user?.weight==null){
            view?.editTextWeight?.hint = "Enter your weight"
        }
        if (user?.bloodGroup=="null"||user?.bloodGroup==null){
            view?.editTextBloodPatient?.hint = "Enter your blood group"
        }
        if (user?.gender=="null"||user?.gender==null){
            view?.editTextGenderPatient?.hint = "Enter your gender"
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