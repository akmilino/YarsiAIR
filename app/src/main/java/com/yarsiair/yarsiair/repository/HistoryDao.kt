package com.yarsiair.yarsiair.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yarsiair.yarsiair.models.HistoryEntity

@Dao // data acces obejct
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(history: HistoryEntity)

    @Query("SELECT * FROM history ORDER BY timestamp DESC")
    fun getAllHistory(): LiveData<List<HistoryEntity>>

    @Query("DELETE FROM history")
    suspend fun clearAllHistory()
}

