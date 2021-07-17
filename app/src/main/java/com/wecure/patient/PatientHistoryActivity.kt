package com.wecure.patient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wecure.patient.R
import kotlinx.android.synthetic.main.activity_patient_history.*

class PatientHistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_history)

        btnConfirmAppointment.setOnClickListener {
        }
    }
}