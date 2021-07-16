package com.wecure.patient

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.*
import com.google.firebase.auth.FirebaseUser

object FireBaseUtils {
    val firebaseAuth: FirebaseAuth = getInstance()
    val firebaseUser: FirebaseUser? = firebaseAuth.currentUser
}