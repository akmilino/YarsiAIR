package com.yarsiair.yarsiair.models

data class SensorData(
    val AQI: Int = 0,    // Default ke 0
    val Kelembapan: Double = 0.0, // Default ke 0.0
    val NO2: Double = 0.0,
    val O3: Double = 0.0,
    val PM10: Int = 0,
    val PM2_5: Int = 0,
    val Suhu: Double = 0.0
)

