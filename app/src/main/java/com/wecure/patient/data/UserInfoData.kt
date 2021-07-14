package com.wecure.patient.data

class UserInfoData{

    lateinit var name:String
    lateinit var email:String
    lateinit var category:String
    lateinit var phoneNumber: String
    lateinit var gender:String
    lateinit var height:String
    lateinit var weight:String
    lateinit var bloodGroup:String

    constructor(){

    }


  /*  constructor(name:String,category: String,phoneNumber:String,email:String){
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
        this.specialization=specialization
    }*/



    constructor(
        email: String,
        name: String,
        category: String,
        phoneNumber: String,
        gender: String,
        weight: String,
        height: String,
        bloodGroup: String
    ){
        //print("successful inside")
        this.name=name
        this.phoneNumber=phoneNumber
        this.weight=weight
        this.gender=gender
        this.height=height
        this.email=email
        this.category=category
        this.bloodGroup=bloodGroup


    }

    constructor(

        name: String,
        phoneNumber: String,
        gender: String,
        weight: String,
        height: String,
        bloodGroup: String
    ){
        //print("successful inside")
        this.name=name
        this.phoneNumber=phoneNumber
        this.weight=weight
        this.gender=gender
        this.height=height
        this.bloodGroup=bloodGroup


    }
}
