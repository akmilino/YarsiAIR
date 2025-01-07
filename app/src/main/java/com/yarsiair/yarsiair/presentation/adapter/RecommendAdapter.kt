package com.yarsiair.yarsiair.presentation.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yarsiair.yarsiair.databinding.ItemListRecommendBinding

class RecommendAdapter(private val recommendations: List<String>) :
    RecyclerView.Adapter<RecommendAdapter.RecommendViewHolder>() {

    inner class RecommendViewHolder(private val binding: ItemListRecommendBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recommendation: String) {
            binding.tvRecommend.text = recommendation
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendViewHolder {
        val binding = ItemListRecommendBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecommendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendViewHolder, position: Int) {
        holder.bind(recommendations[position])
    }

    override fun getItemCount(): Int = recommendations.size
}
