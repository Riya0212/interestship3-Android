package com.wecure.patient.data

class UserInfoData{

    lateinit var name:String
    lateinit var email:String
    lateinit var category:String
    lateinit var phoneNumber: String
    lateinit var specialization:String
    lateinit var gender:String
    lateinit var yearsOfExperience:String
    lateinit var height:String
    lateinit var weight:String
    lateinit var bloodGroup:String
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
        this.specialization=specialization
    }

    constructor(name:String,category: String,phoneNumber:String,email:String,specialization:String,gender:String,yearsOfExperience:String){
        this.name=name
        this.phoneNumber=phoneNumber
        this.specialization=specialization
        this.gender=gender
        this.yearsOfExperience=yearsOfExperience


    }

    constructor(name:String,phoneNumber:String,gender:String,weight:String,height:String,bloodGroup:String){
        this.name=name
        this.phoneNumber=phoneNumber
        this.weight=weight
        this.gender=gender
        this.height=height
        this.bloodGroup=bloodGroup
    }
}
