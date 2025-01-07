package com.yarsiair.yarsiair.presentation

import DashboardViewModel
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.google.android.material.button.MaterialButton
import com.yarsiair.yarsiair.R
import com.yarsiair.yarsiair.databinding.ActivityDashboardBinding
import com.yarsiair.yarsiair.presentation.adapter.InformationAdapter
import com.yarsiair.yarsiair.presentation.adapter.PollutionAdapter
import com.yarsiair.yarsiair.presentation.adapter.RecommendAdapter
import com.yarsiair.yarsiair.repository.MainRepository
import com.yarsiair.yarsiair.repository.MainRepositoryImpl
import com.yarsiair.yarsiair.repository.SaveDataWorker
import com.yarsiair.yarsiair.utils.DataDummy
import com.yarsiair.yarsiair.utils.DataDummyRecommend
import com.yarsiair.yarsiair.utils.formatRoundedDouble
import com.yarsiair.yarsiair.utils.toStatus
import com.yarsiair.yarsiair.utils.toStatusName
import java.util.concurrent.TimeUnit

class DashboardActivity : AppCompatActivity() {
    private var _binding: ActivityDashboardBinding? = null
    private val binding get() = _binding!!
    private val mainRepository: MainRepository by lazy {
        MainRepositoryImpl(this@DashboardActivity)
    }
    private val pollutionAdapter: PollutionAdapter by lazy {
        PollutionAdapter {}
    }
    private val viewModel: DashboardViewModel by viewModels()


    private val pairingList = mutableListOf<Pair<String, String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fetchData()
        setupUi()
        informationOnClick()
        historyOnClick()
        observeViewModel()

        val workRequest = PeriodicWorkRequestBuilder<SaveDataWorker>(1, TimeUnit.HOURS)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "FetchAndSaveData",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }


    private fun historyOnClick() {
        binding.btnHistory.setOnClickListener {
            startActivity(
                Intent(this@DashboardActivity, HistoryActivity::class.java)
            )
        }
    }

    private fun setupUi() {
        binding.rvRecommend.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvPolution.layoutManager = GridLayoutManager(this, 2)
        binding.rvPolution.adapter = pollutionAdapter
    }

    private fun fetchData() {
        mainRepository.getAllSensorData { data ->
            if (data.isNotEmpty()) { // Periksa apakah data tidak kosong
                Log.i("DATA LIST -> ", data.toString())
                pairingList.clear()

                // Ambil data sensor dengan key yang sesuai dari repository
                val aqi = data["AQI"]?.toString()?.toIntOrNull() ?: 0
                val kelembapan = data["Kelembapan"]?.toString()?.toDoubleOrNull() ?: 0.0
                val no2 = data["NO2"]?.toString()?.toDoubleOrNull() ?: 0.0
                val o3 = data["O3"]?.toString()?.toDoubleOrNull() ?: 0.0
                val pm10 = data["PM10"]?.toString()?.toDoubleOrNull() ?: 0.0
                val pm2_5 = data["PM2_5"]?.toString()?.toDoubleOrNull() ?: 0.0
                val suhu = data["Suhu"]?.toString()?.toDoubleOrNull() ?: 0.0

                // Tambahkan data ke pairingList
                pairingList.add(Pair("NO2", no2.formatRoundedDouble()))
                pairingList.add(Pair("O3", o3.formatRoundedDouble()))
                pairingList.add(Pair("PM10", pm10.formatRoundedDouble()))
                pairingList.add(Pair("PM2.5", pm2_5.formatRoundedDouble()))

                viewModel.fetchRecommendations(aqi)

                // Set nilai ke UI
                binding.tvDegree.text = aqi.toString()
                binding.tvTemperature.text = getString(R.string.degree_template, suhu.toString())
                binding.tvHumidity.text = getString(R.string.degree_template, kelembapan.toString())

                // Set gambar dan teks status berdasarkan AQI
                binding.shapeableImageView.setImageResource(aqi.toStatus())
                binding.tvCondition.text = aqi.toStatusName()


            } else {
                Log.e("DashboardActivity", "Data is null or empty")
            }

            // Log pairingList dan update adapter
            Log.i("PAIRING LIST -> ", pairingList.toString())
            pollutionAdapter.setItems(pairingList)
        }
    }


    private fun informationOnClick() {
        binding.btnInformation.setOnClickListener {
            val dialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null)
            val dialog = AlertDialog.Builder(this)
                .setView(dialogView)
                .create()

            val recyclerView = dialogView.findViewById<RecyclerView>(R.id.rv_information)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = InformationAdapter(DataDummy.listData)

            val btnOk = dialogView.findViewById<MaterialButton>(R.id.btn_ok)
            btnOk.setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }
    private fun observeViewModel() {
        viewModel.recommendations.observe(this, Observer { recommendations ->
            binding.rvRecommend.adapter = RecommendAdapter(recommendations)
        })
    }





    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}