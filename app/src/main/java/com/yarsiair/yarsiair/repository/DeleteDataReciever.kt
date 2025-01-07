package com.yarsiair.yarsiair.repository

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DeleteDataReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        GlobalScope.launch {
            val database = AppDatabase.getDatabase(context!!)
            val dao = database.historyDao()

            // Hapus semua data di database
            dao.clearAllHistory()
            Log.d("DeleteDataReceiver", "Semua data history dihapus.")
        }

        // Jadwalkan ulang penghapusan untuk besok
        if (context != null) {
            MyApplication().scheduleDataDeletion(context)
        }
    }
}
