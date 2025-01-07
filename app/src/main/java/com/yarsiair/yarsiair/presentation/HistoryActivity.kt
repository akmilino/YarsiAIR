package com.yarsiair.yarsiair.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.firebase.database.*
import com.yarsiair.yarsiair.databinding.ActivityHistoryBinding
import com.yarsiair.yarsiair.presentation.adapter.HistoryAdapter
import com.yarsiair.yarsiair.repository.SaveDataWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import androidx.work.PeriodicWorkRequestBuilder


class HistoryActivity : AppCompatActivity() {

    private var _binding: ActivityHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var databaseReference: DatabaseReference
    private lateinit var viewModel: HistoryViewModel
    private lateinit var adapter: HistoryAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi binding
        _binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference("AirQuality")

        // Setup UI
        setupUi()

        // Tombol Clear History
        binding.btnRestartHistory.setOnClickListener {
            viewModel.clearHistory()
            Toast.makeText(this, "History cleared.", Toast.LENGTH_SHORT).show()
        }


    }

    private fun setupUi() {
        binding.btnBack.setOnClickListener {
            startActivity(Intent(this@HistoryActivity, DashboardActivity::class.java))
        }


        adapter = HistoryAdapter()
        binding.rvHistory.adapter = adapter
        binding.rvHistory.layoutManager = LinearLayoutManager(this)

        // Set up ViewModel
        viewModel = ViewModelProvider(this)[HistoryViewModel::class.java]

        // Observe LiveData and update UI
        viewModel.historyData.observe(this) { history ->
            Log.d("HistoryActivity", "Data history diperbarui: $history")
            adapter.setData(history)
        }


    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}