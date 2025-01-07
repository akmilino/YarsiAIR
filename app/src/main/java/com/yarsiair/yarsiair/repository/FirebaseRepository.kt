package com.yarsiair.yarsiair.repository

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.yarsiair.yarsiair.models.HistoryEntity
import kotlinx.coroutines.tasks.await

class FirebaseRepository {
    private val databaseRef = FirebaseDatabase.getInstance().getReference("AirQuality")

    suspend fun fetchAirQualityData(): HistoryEntity? {
        return try {
            val snapshot = databaseRef.get().await() // Gunakan await dari kotlinx-coroutines-play-services
            val data = snapshot.getValue(AirQualityData::class.java)
            data?.let {
                HistoryEntity(
                    aqi = it.AQI,
                    kelembapan = it.Kelembapan,
                    no2 = it.NO2,
                    o3 = it.O3,
                    pm10 = it.PM10,
                    pm2_5 = it.PM2_5,
                    suhu = it.Suhu
                )
            }
        } catch (e: Exception) {
            Log.e("FirebaseRepository", "Error fetching data: ${e.message}")
            null
        }
    }
}


data class AirQualityData(
    val AQI: Int = 0,
    val Kelembapan: Double = 0.0,
    val NO2: Double = 0.0,
    val O3: Double = 0.0,
    val PM10: Int = 0,
    val PM2_5: Int = 0,
    val Suhu: Double = 0.0
)
