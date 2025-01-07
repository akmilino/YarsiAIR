package com.yarsiair.yarsiair.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val aqi: Int,
    val kelembapan: Double,
    val no2: Double,
    val o3: Double,
    val pm10: Int,
    val pm2_5: Int,
    val suhu: Double,
    val timestamp: Long = System.currentTimeMillis()
)
