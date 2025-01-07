package com.yarsiair.yarsiair.utils

import com.yarsiair.yarsiair.models.Polution

object DataDummyPolution {
    private val namePolution = arrayOf(
        "PM2.5",
        "NOX",
        "PM10",
        "Ozone",
        "PM1"
    )
    private val polution = arrayOf(
        "26.8",
        "26.8",
        "26.8",
        "26.8",
        "26.8",
    )

    val listData: ArrayList<Polution>
        get() {
            val list = arrayListOf<Polution>()
            for (position in namePolution.indices){
                list.add(
                    Polution(
                        namePolution[position],
                        polution[position]
                    )
                )
            }
            return list
        }
}