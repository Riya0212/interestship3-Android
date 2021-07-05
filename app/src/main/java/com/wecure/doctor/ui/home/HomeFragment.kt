package com.wecure.doctor.ui.home

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wecure.doctor.DataSource
import com.wecure.doctor.DoctorRecyclerAdapter
import com.wecure.doctor.HistoryRecyclerAdapter
import com.wecure.doctor.R
import com.wecure.doctor.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.lang.Exception

class HomeFragment : Fragment() {

    private var nameList = mutableListOf<String>()
    private var categoryList = mutableListOf<String>()
    private var profileList = mutableListOf<Int>()
    private var nameListHistory = mutableListOf<String>()
    private var categoryListHistory = mutableListOf<String>()
    private var profileListHistory = mutableListOf<Int>()
    private var dateListHistory = mutableListOf<String>()
    private var _binding: FragmentHomeBinding? = null
    private val data = DataSource.createDataSet()
    private val dataHistory = DataSource.createDataSetHistory()
    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        val imgsetting:ImageView = itemView.findViewById(R.id.imgsettings)!!

        val popupMenu =PopupMenu(context,imgsetting)

        popupMenu.inflate(R.menu.top_bar_menu)
        popupMenu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.action_contact_us ->{
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
    postToList()
        postToListHistory()
        recyclerDoctors.layoutManager= LinearLayoutManager(this.context,RecyclerView.HORIZONTAL,false)
        recyclerDoctors.adapter = DoctorRecyclerAdapter(nameList,categoryList,profileList)

        recyclerHistory.layoutManager= LinearLayoutManager(this.context,RecyclerView.VERTICAL,false)
        recyclerHistory.adapter = HistoryRecyclerAdapter(nameListHistory,categoryListHistory,profileListHistory,dateListHistory)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

private fun popUpMenu(){

}
private fun addToList(name:String,category:String,proile:Int){

    nameList.add(name)
    categoryList.add(category)
    profileList.add(proile)

}
private fun postToList(){
    for( i in 0 until data.size){
        addToList(data[i].name,data[i].desc,data[i].image)
    }
}

    private fun addToListHistory(namehist:String,categoryhist:String,proilehist:Int,datehist:String){

        nameListHistory.add(namehist)
        categoryListHistory.add(categoryhist)
        profileListHistory.add(proilehist)
        dateListHistory.add(datehist)

    }
    private fun postToListHistory(){
        for( i in 0 until dataHistory.size){
            addToListHistory(dataHistory[i].name,dataHistory[i].category,dataHistory[i].image,dataHistory[i].date)
        }
    }
}