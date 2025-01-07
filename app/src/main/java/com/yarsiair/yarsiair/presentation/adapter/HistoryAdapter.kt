package com.yarsiair.yarsiair.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yarsiair.yarsiair.databinding.ItemListHistoryBinding
import com.yarsiair.yarsiair.models.HistoryEntity
import com.yarsiair.yarsiair.utils.toStatusAqiKecil
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    private var historyList = emptyList<HistoryEntity>()

    fun setData(data: List<HistoryEntity>) {
        historyList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemListHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = historyList[position]
        holder.bind(item)
    }

    override fun getItemCount() = historyList.size

    class HistoryViewHolder(private val binding: ItemListHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(history: HistoryEntity) {
            binding.tvDegree.text = history.suhu.toString()
            binding.tvPersentaseWater.text = history.kelembapan.toString()
            binding.tvDay.text = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date(history.timestamp))
            binding.tvDate.text = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(history.timestamp))

            // Set gambar AQI berdasarkan nilai AQI
            binding.ivCondition.text = "AQI: ${history.aqi}"

        }
    }
}
