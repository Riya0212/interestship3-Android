package com.wecure.patient

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.wecure.patient.databinding.ActivityHomeScreenDoctorBinding
import com.wecure.patient.ui.dashboard_doctor.DashboardFragment_Doctor
import com.wecure.patient.ui.home_doctor.HomeFragment_Doctor

class HomeScreen_Doctor : AppCompatActivity() {

    private lateinit var binding: ActivityHomeScreenDoctorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding = ActivityHomeScreenDoctorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val homefragment_doctor = HomeFragment_Doctor()
        val profilefragment_doctor = DashboardFragment_Doctor()

        makecurrentfragment(homefragment_doctor)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_home_screen_doctor)
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
            when (it.itemId) {
                R.id.navigation_home -> makecurrentfragment(homefragment_doctor)
                R.id.navigation_profile -> makecurrentfragment(profilefragment_doctor)
            }
            true
        }


    }

    private fun makecurrentfragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment_activity_home_screen_doctor, fragment)
            commit()
        }

    override fun onBackPressed() {
        super.onBackPressed();
        finishAffinity(); // or finish();
    }
}