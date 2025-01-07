package com.yarsiair.yarsiair.repository

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters

import java.util.concurrent.TimeUnit

class SaveDataWorker(appContext: Context, workerParams: WorkerParameters) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        val repo = FirebaseRepository()
        val database = AppDatabase.getDatabase(applicationContext)
        val dao = database.historyDao()

        return try {
            // Fetch data dari Firebase
            val history = repo.fetchAirQualityData()
            if (history != null) {
                // Simpan data ke Room
                dao.insertHistory(history)
                Log.d("SaveDataWorker", "Data baru disimpan ke Room: $history")
            } else {
                Log.e("SaveDataWorker", "Gagal mendapatkan data dari Firebase")
                return Result.retry() // Jadwal ulang jika gagal
            }

            // Jadwalkan pekerjaan ulang
            scheduleNextWork(applicationContext)
            Result.success()
        } catch (e: Exception) {
            Log.e("SaveDataWorker", "Terjadi kesalahan: ${e.message}")
            Result.retry()
        }
    }

    private fun scheduleNextWork(context: Context) {
        val workRequest = OneTimeWorkRequestBuilder<SaveDataWorker>()
            .setInitialDelay(1, TimeUnit.HOURS) // Untuk setting waktu history
            .build()

        WorkManager.getInstance(context).enqueueUniqueWork(
            "FetchAndSaveData",
            ExistingWorkPolicy.REPLACE,
            workRequest
        )
    }
}





//class SaveDataWorker(appContext: Context, workerParams: WorkerParameters) : CoroutineWorker(appContext, workerParams) {
//
//    override suspend fun doWork(): Result {
//        Log.d("SaveDataWorker", "Pekerjaan dimulai pada: ${System.currentTimeMillis()}")
//
//        // Inisialisasi repository dan database
//        val repo = FirebaseRepository()
//        val database = AppDatabase.getDatabase(applicationContext)
//        val dao = database.historyDao()
//
//        try {
//            // Fetch dan simpan data
//            repo.fetchAirQualityData { history ->
//                runBlocking {
//                    dao.insertHistory(history)
//                    Log.d("SaveDataWorker", "Data baru disimpan ke Room: $history")
//                }
//            }
//
//            // Jadwalkan pekerjaan ulang setiap 5 menit
//            scheduleNextWork(applicationContext)
//
//        } catch (e: Exception) {
//            Log.e("SaveDataWorker", "Terjadi kesalahan: ${e.message}")
//            return Result.retry()
//        }
//
//        Log.d("SaveDataWorker", "Pekerjaan selesai pada: ${System.currentTimeMillis()}")
//        return Result.success()
//    }
//
//    private fun scheduleNextWork(context: Context) {
//        val workRequest = OneTimeWorkRequestBuilder<SaveDataWorker>()
//            .setInitialDelay(5, TimeUnit.MINUTES)
//            .build()
//
//        WorkManager.getInstance(context).enqueueUniqueWork(
//            "FetchAndSaveData",
//            ExistingWorkPolicy.REPLACE,
//            workRequest
//        )
//    }
//}





//class SaveDataWorker(appContext: Context, workerParams: WorkerParameters) : CoroutineWorker(appContext, workerParams) {
//    override suspend fun doWork(): Result {
//        val repo = FirebaseRepository()
//        val database = AppDatabase.getDatabase(applicationContext)
//        val dao = database.historyDao()
//
//        repo.fetchAirQualityData { history ->
//            GlobalScope.launch {
//                dao.insertHistory(history)
//                Log.d("SaveDataWorker", "Data baru disimpan ke Room: $history")
//            }
//        }
//
//        // Jadwalkan pekerjaan ulang setiap 10 menit
//        scheduleNextWork(applicationContext)
//
//        return Result.success()
//    }
//
//    private fun scheduleNextWork(context: Context) {
//        val workRequest = OneTimeWorkRequestBuilder<SaveDataWorker>()
//            .setInitialDelay(5, TimeUnit.MINUTES)
//            .build()
//
//        WorkManager.getInstance(context).enqueueUniqueWork(
//            "FetchAndSaveData",
//            ExistingWorkPolicy.REPLACE,
//            workRequest
//        )
//    }
//}
