package com.yarsiair.yarsiair.utils

import com.yarsiair.yarsiair.R
import com.yarsiair.yarsiair.models.Information

object DataDummy {
    private val condition = arrayOf(
        R.drawable.bg_bagus,
        R.drawable.bg_sedang,
        R.drawable.bg_untuk_kelompok_sensitif,
        R.drawable.bg_tidak_sehat
    )
    private val nameCondition = arrayOf(
        "Bagus",
        "Sedang",
        "Tidak sehat untuk kelompok sensitif",
        "Tidak sehat"
    )
    private val conditionDescription = arrayOf(
        "Kualitas udara memuaskan dan tidak menimbulkan risiko atau hanya sedikit. Disarankan untuk menyediakan ventilasi di rumah Anda " +
                "\n \nSaran: Nikmati aktivitas luar ruangan, Berolahraga di luar sangat dianjurkan",
        "Orang yang sensitif sebaiknya menghindari aktivitas di luar ruangan karena mereka mungkin mengalami gejala pernapasan" +
                "\n \nSaran: Perhatikan kondisi kesehatan Anda, Lakukan aktivitas luar ruangan dengan ringan. ",
        "Masyarakat umum dan khususnya individu yang sensitif berisiko mengalami iritasi dan masalah pernapasan." +
                "\n \nSaran: Kurangi aktivitas berat di luar ruangan, Kelompok sensitif sebaiknya tetap di dalam ruangan.",
        "Meningkatnya kemungkinan terjadinya efek samping dan memperburuk kondisi jantung dan paru-paru di kalangan masyarakat umum - terutama kelompok yang sensitif" +
                "\n \nSaran: Hindari aktivitas berat di luar ruangan, Gunakan masker jika harus keluar rumah.",
    )

    val listData: ArrayList<Information>
        get() {
            val list = arrayListOf<Information>()
            for (position in nameCondition.indices){
                list.add(
                    Information(
                        nameCondition[position],
                        conditionDescription[position],
                        condition[position]
                    )
                )
            }
            return list
        }
}