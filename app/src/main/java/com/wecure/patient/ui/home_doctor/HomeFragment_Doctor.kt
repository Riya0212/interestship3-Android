package com.wecure.patient.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wecure.patient.*
import com.wecure.patient.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home_doctor.*
import java.lang.Exception

class HomeFragment_Doctor : Fragment() {

    private var namePatientList_doctor = mutableListOf<String>()
    private var datePateientList_doctor = mutableListOf<String>()
    private var profilePatientList_doctor = mutableListOf<Int>()
    private var nameListHistory_doctor = mutableListOf<String>()
    private var reasonListHistory_doctor = mutableListOf<String>()
    private var profileListHistory_doctor = mutableListOf<Int>()
    private var dateListHistory_doctor = mutableListOf<String>()
    private var _binding: FragmentHomeBinding? = null
    private val dataAppointmentReq = DataSource.createDataSetreq()
    private val dataHistoryDoctor =DataSource.createDataSethistdoc()
   // private val dataHistory = DataSource.createDataSetHistory()
    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v:View=inflater.inflate(R.layout.fragment_home_doctor, container, false)

        return v
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        val imgsetting:ImageView = itemView?.findViewById(R.id.imgsettings)!!
        val popupMenu =PopupMenu(context,imgsetting)

        popupMenu.inflate(R.menu.top_bar_menu)
        popupMenu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.action_contact_us ->{
                    val intent = Intent (getActivity(), ContactUs::class.java)
                    startActivity(intent)
                    true
                }
                R.id.action_exit ->{
                    true
                }
                else ->{
                    true
                }
            }

        }


    imgsetting.setOnClickListener{
            try{
                val popup= PopupMenu::class.java.getDeclaredField("mPopup")
                popup.isAccessible = true
                val menu =popup.get(popupMenu)
                menu.javaClass.getDeclaredMethod("setForceShowIcon",Boolean::class.java)
                    .invoke(menu,true)
                true
            }catch (e:Exception){
                e.printStackTrace()
            }
            finally {
                popupMenu.show()
            }

        }

        postToListAppointmentReq_doctor()
        postToListHistory_doctor()
        recycler_newreq_Doctors.layoutManager=LinearLayoutManager(this.context,RecyclerView.HORIZONTAL,false)
        recycler_newreq_Doctors.adapter=ApointmentReqRecyclerAdapter(dataAppointmentReq,namePatientList_doctor,datePateientList_doctor,profilePatientList_doctor)
        recyclerHistory_doctor.layoutManager= LinearLayoutManager(this.context,RecyclerView.VERTICAL,false)
        recyclerHistory_doctor.adapter=HistoryDoctorRecyclerAdapter(nameListHistory_doctor,reasonListHistory_doctor,profileListHistory_doctor,dateListHistory_doctor)

/*
        recyclerDoctors.layoutManager= LinearLayoutManager(this.context,RecyclerView.HORIZONTAL,false)
        recyclerDoctors.adapter = DoctorRecyclerAdapter(nameList,categoryList,profileList)

        recyclerHistory.layoutManager= LinearLayoutManager(this.context,RecyclerView.HORIZONTAL,false)
        recyclerHistory.adapter = HistoryRecyclerAdapter(nameListHistory,categoryListHistory,profileListHistory,dateListHistory)
    */

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

private fun popUpMenu(){

}

    private fun addToListAppointmentReq_doctor(namelist:String,datelist:String,proilelist:Int){

        namePatientList_doctor.add(namelist)
            datePateientList_doctor.add(datelist)
            profileListHistory_doctor.add(proilelist)

    }
    private fun postToListAppointmentReq_doctor(){
        for( i in 0 until dataAppointmentReq.size){
            addToListAppointmentReq_doctor(dataAppointmentReq[i].name,dataAppointmentReq[i].date,dataAppointmentReq[i].image)
        }
    }

    private fun addToListHistory_doctor(namelist_doctor:String,reasonlist_doctor:String,proilelist_doctor:Int,datelist_doctor:String){

       nameListHistory_doctor.add(namelist_doctor)
        reasonListHistory_doctor.add(reasonlist_doctor)
        profileListHistory_doctor.add(proilelist_doctor)
        dateListHistory_doctor.add(datelist_doctor)

    }
    private fun postToListHistory_doctor(){
        for( i in 0 until dataHistoryDoctor.size){
            addToListHistory_doctor(dataHistoryDoctor[i].name,dataHistoryDoctor[i].reason,dataHistoryDoctor[i].image,dataHistoryDoctor[i].date)
        }
    }
}