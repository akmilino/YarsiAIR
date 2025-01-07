package com.yarsiair.yarsiair.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.yarsiair.yarsiair.models.HistoryEntity
import com.yarsiair.yarsiair.repository.AppDatabase
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit


class HistoryViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.getDatabase(application).historyDao()

    // LiveData untuk mengamati perubahan data di Room Database
    val historyData: LiveData<List<HistoryEntity>> = dao.getAllHistory()

    init { Log.d("HistoryViewModel", "Mengamati data dari Room Database.") }


    // Fungsi untuk menghapus semua data
    fun clearHistory() {
        viewModelScope.launch {
            dao.clearAllHistory()
            Log.d("HistoryViewModel", "Semua data history telah dihapus.")
        }
    }

}




//class HistoryViewModel(application: Application) : AndroidViewModel(application) {
//    private val dao = AppDatabase.getDatabase(application).historyDao()
//
//    val historyData: LiveData<List<HistoryEntity>> = dao.getHistoryAfter(
//        System.currentTimeMillis() - TimeUnit.HOURS.toMillis(1)
//    )
//
//    init {
//        Log.d("HistoryViewModel", "Mengamati data dari Room Database.")
//    }
//}

