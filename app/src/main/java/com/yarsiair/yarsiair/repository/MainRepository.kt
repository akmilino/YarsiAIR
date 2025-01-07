package com.yarsiair.yarsiair.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

interface MainRepository {
    fun getAllSensorData(callback: (Map<String, Any>) -> Unit)
}

class MainRepositoryImpl(private val context: Context) : MainRepository {

    private val database = FirebaseDatabase.getInstance()

    override fun getAllSensorData(callback: (Map<String, Any>) -> Unit) {
        database.getReference("AirQuality").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Ambil data sebagai Map<String, Any>
                    val sensorData = dataSnapshot.value as? Map<String, Any> ?: return

                    // Catat data untuk debugging
                    Log.i("DATA SNAPSHOT", sensorData.toString())

                    // Kirim data ke callback
                    callback(sensorData)
                } else {
                    Log.e("DashboardActivity", "Data does not exist.")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("TAG", "Failed to read value.", error.toException())
                Toast.makeText(context, "$error occurred", Toast.LENGTH_SHORT).show()
            }
        })
    }


}