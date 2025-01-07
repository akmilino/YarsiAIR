package com.yarsiair.yarsiair.utils

import com.yarsiair.yarsiair.R
import com.yarsiair.yarsiair.models.Recommend

object DataDummyRecommend {
    private val recommend = arrayOf(
        "Nikmati aktivitas luar ruangan seperti berjalan-jalan, berolahraga, atau bersantai di luar.",
        "Nikmati aktivitas luar ruangan dan tetap jaga kesehatan.",
        "Kurangi aktivitas luar ruangan dan tetap waspada.",
        "Kurangi aktivitas luar ruangan dan menggunakan masker.",
        "Tetap terhidrasi.",
    )
    private val img = arrayOf(
        R.drawable.ic_person,
        R.drawable.ic_person,
        R.drawable.ic_person,
        R.drawable.ic_person,
        R.drawable.ic_person,
        R.drawable.ic_person
    )
    val listData: ArrayList<Recommend>
        get() {
            val list = arrayListOf<Recommend>()
            for (position in recommend.indices){
                list.add(
                    Recommend(
                        recommend[position],
                        img[position]
                    )
                )
            }
            return list
        }
}