//package com.yarsiair.yarsiair.utils
//
//import com.yarsiair.yarsiair.R
//import com.yarsiair.yarsiair.models.History
//
//object DataDummyHistory {
//    private val day = arrayOf(
//        "Tue",
//        "Wed",
//        "Thu",
//        "Fri",
//        "Sat",
//        "Sun",
//        "Mon"
//    )
//    private val date = arrayOf(
//        "21",
//        "22",
//        "23",
//        "24",
//        "25",
//        "26",
//        "27"
//    )
//    private val degree = arrayOf(
//        "29° - 31°",
//        "29° - 31°",
//        "29° - 31°",
//        "29° - 31°",
//        "29° - 31°",
//        "29° - 31°",
//        "29° - 31°",
//    )
//    private val persentaseWater = arrayOf(
//        "72%",
//        "72%",
//        "72%",
//        "72%",
//        "72%",
//        "72%",
//        "72%",
//    )
//
//    private val image = arrayOf(
//        R.drawable.ic_small_bagus, // Pastikan jumlah gambar sama dengan jumlah data lainnya
//        R.drawable.ic_small_sedang,
//        R.drawable.ic_small_kelompok_sensitif,
//        R.drawable.ic_small_tidak_sehat
//    )
//    val listData: ArrayList<History>
//        get() {
//            val list = arrayListOf<History>()
//            for (position in day.indices){
//                list.add(
//                    History(
//                        day[position],
//                        date[position],
//                        degree[position],
//                        persentaseWater[position],
//
//                    )
//                )
//            }
//            return list
//        }
//}