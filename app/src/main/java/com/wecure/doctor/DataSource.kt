package com.wecure.doctor

class DataSource{

    companion object{

        fun createDataSet(): ArrayList<DoctorModel>{
            val list = ArrayList<DoctorModel>()
            list.add(
                DoctorModel(
                    "Hrishita",
                    "orthopedic",
                    R.drawable.download
                )
            )
            list.add(
                DoctorModel(
                    "Hrishita",
                    "orthopedic",
                    R.drawable.download
                )
            )
            list.add(
                DoctorModel(
                    "Hrishita",
                    "orthopedic",
                    R.drawable.download
                )
            )


            return list
        }
    }
}