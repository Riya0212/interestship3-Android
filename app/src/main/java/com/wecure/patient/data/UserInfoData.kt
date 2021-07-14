package com.wecure.patient.data

class UserInfoData{

    lateinit var name:String
    lateinit var email:String
    lateinit var category:String
    lateinit var phoneNumber: String
    lateinit var spcialization:String
    constructor(){

    }


    constructor(name:String,category: String,phoneNumber:String,email:String){
        this.name=name
        this.email=email
        this.category=category
        this.phoneNumber=phoneNumber
    }
    constructor(name:String,category: String,phoneNumber:String,email:String,specialization:String){
        this.name=name
        this.email=email
        this.category=category
        this.phoneNumber=phoneNumber
        this.spcialization=spcialization
    }
}