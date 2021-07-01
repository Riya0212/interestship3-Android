package com.wecure.doctor

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wecure.doctor.databinding.ActivityHomeScreenBinding

class HomeScreen : AppCompatActivity() {

    private lateinit var binding: ActivityHomeScreenBinding
   private  lateinit var docAdapter: DoctorRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_home_screen)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        if (savedInstanceState == null) {
            // 2
            supportFragmentManager
                // 3
                .beginTransaction()
                // 4
                .add(R.id.nav_host_fragment_container, DogListFragment.newInstance(), "dogList")
                // 5
                .commit()
        }






        // initRecyclerView()
         //   addDataSet()


    }


    //finding view
    //val recyclerView = findViewById<RecyclerView>(R.id.recyclerDoctors)
    //item decorator to separate the items





   /* private fun addDataSet(){
        val data = DataSource.createDataSet()
        docAdapter.submitList(data)
    }

    private fun initRecyclerView(){

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@HomeScreen)
            //val topSpacingDecorator = TopSpacingItemDecoration(30)
           // addItemDecoration(topSpacingDecorator)
            docAdapter = DoctorRecyclerAdapter()
            adapter = docAdapter
        }*/
   //// }
}