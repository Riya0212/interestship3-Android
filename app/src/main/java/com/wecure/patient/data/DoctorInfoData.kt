package com.wecure.patient.data

class DoctorInfoData {
    lateinit var name:String
    lateinit var email:String
    lateinit var category:String
    lateinit var phoneNumber: String
    lateinit var specialization:String
    lateinit var gender:String
    lateinit var yearsOfExperience:String
    lateinit var introduction: String
    lateinit var address: String
    constructor(){

    }

    constructor(
        name: String,
        category: String,
        phoneNumber: String,
        email: String,
        specialization: String,
        gender: String,
        yearsOfExperience: String,
        address:String,
        introduction: String,
    ){
        this.name=name
        this.phoneNumber=phoneNumber
        this.specialization=specialization
        this.gender=gender
        this.category=category
        this.yearsOfExperience=yearsOfExperience
        this.email=email
        this.address = address
        this.introduction=introduction


    }
}