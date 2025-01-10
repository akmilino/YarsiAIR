package com.yarsiair.yarsiair.models


enum class Status(val value: String, val min: Int, val max: Int) {
    GOOD("Bagus", 0, 50),
    MEDIUM("Sedang", 51, 100),
    UNHEALTHY_FOR_SENSITIVE_GROUPS("Tidak Sehat Untuk Kelompok Sensitive", 101, 150),
    UNHEALTHY("Tidak Sehat", 151, 200),
    VERY_UNHEALTHY("Sangat Tidak Sehat", 201, 300),
    VERY_UNHEALTHY_SEVERE("Hazardous", 301, 500)
}