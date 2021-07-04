package com.wecure.doctor

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wecure.doctor.databinding.ActivityHomeScreenBinding
import com.wecure.doctor.ui.dashboard.DashboardFragment
import com.wecure.doctor.ui.home.HomeFragment

class HomeScreen : AppCompatActivity() {

    private lateinit var binding: ActivityHomeScreenBinding
 //  private  lateinit var docAdapter: DoctorRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val homefragment = HomeFragment()
        val proflefragment = DashboardFragment()

        makecurrentfragment(homefragment)

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
        navView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_home -> makecurrentfragment(homefragment)
                R.id.navigation_profile -> makecurrentfragment(proflefragment)
            }
            true
        }
    }
private fun makecurrentfragment(fragment: Fragment)= supportFragmentManager.beginTransaction().apply {
    replace(R.id.nav_host_fragment_activity_home_screen,fragment)
    commit()
}

}