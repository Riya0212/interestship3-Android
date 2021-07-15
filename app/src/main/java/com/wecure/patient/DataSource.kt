package com.wecure.patient

class DataSource{

    companion object{

        fun createDataSet(): ArrayList<DoctorModel>{
            val list = ArrayList<DoctorModel>()
            list.add(
                DoctorModel(
                    "Hrishita Mavani",
                    "orthopedic",
                    R.drawable.download
                )
            )
            list.add(
                DoctorModel(
                    "Ria",
                    "Gastroentrologist",
                    R.drawable.ellipsesampleimage2
                )
            )
           /* list.add(
                DoctorModel(
                    "abc",
                    "orthopedic",
                    R.drawable.download
                )
            )*/
           /* list.add(
                DoctorModel(
                    "4-Hrishita",
                    "orthopedic surgeon",
                    R.drawable.download
                )
            )
            list.add(
                DoctorModel(
                    "5- Hrishita",
                    "orthopedic",
                    R.drawable.download
                )
            )
            list.add(
                DoctorModel(
                    "6- Hrishita",
                    "orthopedic",
                    R.drawable.download
                )
            )
            list.add(
                DoctorModel(
                    "7- Hrishita",
                    "orthopedic",
                    R.drawable.download
                )
            )
            list.add(
                DoctorModel(
                    "8-Hrishita",
                    "orthopedic",
                    R.drawable.download
                )
            )
            list.add(
                DoctorModel(
                    "8- Hrishita",
                    "orthopedic",
                    R.drawable.download
                )
            )
            list.add(
                DoctorModel(
                    "9-Hrishita",
                    "orthopedic",
                    R.drawable.download
                )
            )
            list.add(
                DoctorModel(
                    "10-Hrishita Mavani",
                    "orthopedic",
                    R.drawable.download
                )
            )
            list.add(
                DoctorModel(
                    "11- Hrishita Mavani",
                    "orthopedic",
                    R.drawable.download
                )
            )
            list.add(
                DoctorModel(
                    "12-Hrishita",
                    "orthopedic",
                    R.drawable.download
                )
            )
            list.add(
                DoctorModel(
                    "13-Hrishita",
                    "orthopedic",
                    R.drawable.download
                )
            )
            list.add(
                DoctorModel(
                    "14-Hrishita",
                    "orthopedic",
                    R.drawable.download
                )
            )*/
            return list
        }
        fun createDataSethistdoc(): ArrayList<HistoryDoctorModel>{
            val listReqhistdoc = ArrayList<HistoryDoctorModel>()
            listReqhistdoc.add(
                HistoryDoctorModel(
                    "1-Hrishita Mavani",
                    "head ache and fever",
                    R.drawable.download,
                    "10-07-2020"
                )
            )
            listReqhistdoc.add(
                HistoryDoctorModel(
                    "Ria",
                    "fever",
                    R.drawable.ellipsesampleimage3,
                    "15-07-2021"
                )
            )
           /* listReqhistdoc.add(
                HistoryDoctorModel(
                    "1-Hrishita Mavani",
                    "head ache and fever",
                    R.drawable.download,
                    "10-07-2020"
                )
            )
            listReqhistdoc.add(
                HistoryDoctorModel(
                    "1-Hrishita Mavani",
                    "head ache and fever",
                    R.drawable.download,
                    "10-07-2020"
                )
            )
            listReqhistdoc.add(
                HistoryDoctorModel(
                    "1-Hrishita Mavani",
                    "head ache and fever",
                    R.drawable.download,
                    "10-07-2020"
                )
            )
            listReqhistdoc.add(
                HistoryDoctorModel(
                    "1-Hrishita Mavani",
                    "head ache and fever",
                    R.drawable.download,
                    "10-07-2020"
                )
            )
            listReqhistdoc.add(
                HistoryDoctorModel(
                    "1-Hrishita Mavani",
                    "head ache and fever",
                    R.drawable.download,
                    "10-07-2020"
                )
            )*/
        return listReqhistdoc
        }
        fun createDataSetreq(): ArrayList<AppointmentReqModel>{
            val listReq = ArrayList<AppointmentReqModel>()
            listReq.add(
                AppointmentReqModel(
                    "Hrishita Mavani",
                    "10-07-2020",
                    R.drawable.download
                )
            )
            listReq.add(
                AppointmentReqModel(
                    "Ria",
                    "15-07-2021",
                    R.drawable.ellipsesampleimage3
                )
            )
           /* listReq.add(
                AppointmentReqModel(
                    "1-Hrishita Mavani",
                    "10-07-2020",
                    R.drawable.download
                )
            )*/
           /* listReq.add(
                AppointmentReqModel(
                    "1-Hrishita Mavani",
                    "10-07-2020",
                    R.drawable.download
                )
            )
            listReq.add(
                AppointmentReqModel(
                    "1-Hrishita Mavani",
                    "10-07-2020",
                    R.drawable.download
                )
            )
            listReq.add(
                AppointmentReqModel(
                    "1-Hrishita Mavani",
                    "10-07-2020",
                    R.drawable.download
                )
            )
            listReq.add(
                AppointmentReqModel(
                    "1-Hrishita Mavani",
                    "10-07-2020",
                    R.drawable.download
                )
            )
            listReq.add(
                AppointmentReqModel(
                    "1-Hrishita Mavani",
                    "10-07-2020",
                    R.drawable.download
                )
            )
            listReq.add(
                AppointmentReqModel(
                    "1-Hrishita Mavani",
                    "10-07-2020",
                    R.drawable.download
                )
            )
            listReq.add(
                AppointmentReqModel(
                    "1-Hrishita Mavani",
                    "10-07-2020",
                    R.drawable.download
                )
            )
*/
            return listReq
        }


        fun createDataSetHistory(): ArrayList<HistoryModel>{
            val listHistory = ArrayList<HistoryModel>()
            listHistory.add(
                HistoryModel(
                    "Hrishita Mavani",
                    "orthopedic",
                    R.drawable.download,
                    "10-05-200"
                )

            )
            listHistory.add(
                HistoryModel(
                    "Ria",
                    "Gastroenterologist",
                    R.drawable.ellipsesampleimage2,
                    "10-05-200"
                )

            )
            /*listHistory.add(
                HistoryModel(
                    "3-Hrishita Mavani",
                    "orthopedic",
                    R.drawable.download,
                    "10-05-200"
                )

            )
            listHistory.add(
                HistoryModel(
                    "4-Hrishita Mavani",
                    "orthopedic",
                    R.drawable.download,
                    "10-05-200"
                )

            )
            listHistory.add(
                HistoryModel(
                    "5-Hrishita Mavani",
                    "orthopedic",
                    R.drawable.download,
                    "10-05-200"
                )

            )
            listHistory.add(
                HistoryModel(
                    "6-Hrishita Mavani",
                    "orthopedic",
                    R.drawable.download,
                    "10-05-200"
                )

            )
            listHistory.add(
                HistoryModel(
                    "7-Hrishita Mavani",
                    "orthopedic",
                    R.drawable.download,
                    "10-05-200"
                )

            )
            listHistory.add(
                HistoryModel(
                    "8-Hrishita Mavani",
                    "orthopedic",
                    R.drawable.download,
                    "10-05-200"
                )

            )
            listHistory.add(
                HistoryModel(
                    "9-Hrishita Mavani",
                    "orthopedic",
                    R.drawable.download,
                    "10-05-200"
                )

            )
            listHistory.add(
                HistoryModel(
                    "10-Hrishita Mavani",
                    "orthopedic",
                    R.drawable.download,
                    "10-05-200"
                )

            )
            listHistory.add(
                HistoryModel(
                    "11-Hrishita Mavani",
                    "orthopedic",
                    R.drawable.download,
                    "10-05-200"
                )

            )
            listHistory.add(
                HistoryModel(
                    "12-Hrishita Mavani",
                    "orthopedic",
                    R.drawable.download,
                    "10-05-200"
                )

            )
            listHistory.add(
                HistoryModel(
                    "13-Hrishita Mavani",
                    "orthopedic",
                    R.drawable.download,
                    "10-05-200"
                )

            )
            listHistory.add(
                HistoryModel(
                    "14-Hrishita Mavani",
                    "orthopedic",
                    R.drawable.download,
                    "10-05-200"
                )

            )
*/
            return listHistory
        }
        fun createDataSetFeedBack(): ArrayList<FeedbackModel> {
            val listFeedback = ArrayList<FeedbackModel>()
            listFeedback.add(
                FeedbackModel(
                    "Hrishita",
                    "Very Nice Treatment",
                    1.2f,
                )

            )
            listFeedback.add(
                FeedbackModel(
                    "Ria",
                    "good",
                    1.2f,
                )

            )
          /*  listFeedback.add(
                FeedbackModel(
                    "1-Hrishita Mavani",
                    "Very Nice Treatment",
                    1.2f,
                )

            )
            listFeedback.add(
                FeedbackModel(
                    "1-Hrishita Mavani",
                    "Very Nice Treatment",
                    1.2f,
                )

            )
            listFeedback.add(
                FeedbackModel(
                    "1-Hrishita Mavani",
                    "Very Nice Treatment",
                    1.2f,
                )

            )
            listFeedback.add(
                FeedbackModel(
                    "1-Hrishita Mavani",
                    "Very Nice Treatment",
                    1.2f,
                )

            )
            listFeedback.add(
                FeedbackModel(
                    "1-Hrishita Mavani",
                    "Very Nice Treatment",
                    1.2f,
                )

            )
            listFeedback.add(
                FeedbackModel(
                    "1-Hrishita Mavani",
                    "Very Nice Treatment",
                    1.2f,
                )

            )
            listFeedback.add(
                FeedbackModel(
                    "1-Hrishita Mavani",
                    "Very Nice Treatment",
                    1.2f,
                )

            )
            listFeedback.add(
                FeedbackModel(
                    "1-Hrishita Mavani",
                    "Very Nice Treatment",
                    1.2f,
                )

            )*/

            return listFeedback
        }
    }
}