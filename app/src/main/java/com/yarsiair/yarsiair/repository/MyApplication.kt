package com.yarsiair.yarsiair.repository

import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import java.util.Calendar
import java.util.concurrent.TimeUnit

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Inisialisasi WorkManager untuk menyimpan data setiap 1 jam
//        scheduleDataFetch()



    }

//    private fun scheduleDataFetch() {
//        // Buat WorkRequest untuk mengambil data setiap 1 jam
//        val workRequest = PeriodicWorkRequestBuilder<SaveDataWorker>(1, TimeUnit.HOURS)
//            .build()
//
//        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
//            "FetchAndSaveData",
//            ExistingPeriodicWorkPolicy.KEEP,
//            workRequest
//        )
//
//    }
//    private fun scheduleSaveDataWorker() {
//        val workRequest = OneTimeWorkRequestBuilder<SaveDataWorker>()
//        .setInitialDelay(1, TimeUnit.MINUTES)
//        .build()
//
//         WorkManager.getInstance(this).enqueueUniqueWork(
//        "FetchAndSaveData",
//        ExistingWorkPolicy.REPLACE,
//        workRequest
//        )
//    }


    fun scheduleDataDeletion(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, DeleteDataReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Set waktu eksekusi pada jam 12 malam
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            add(Calendar.DAY_OF_MONTH, 1) // Pastikan penghapusan terjadi besok
        }

        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
    }


}
