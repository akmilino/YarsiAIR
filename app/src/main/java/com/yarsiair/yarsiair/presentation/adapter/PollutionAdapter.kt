package com.yarsiair.yarsiair.presentation.adapter;

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.yarsiair.yarsiair.databinding.ItemListPolutionBinding
import com.yarsiair.yarsiair.utils.formatRoundedDouble
import com.yarsiair.yarsiair.utils.toStatusColor


class PollutionAdapter(private val itemClick: (Pair<String, String>) -> Unit) :
    RecyclerView.Adapter<PollutionAdapter.PollutionViewHolder>() {

    private var items: MutableList<Pair<String, String>> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<Pair<String, String>>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PollutionViewHolder {
        val binding =
            ItemListPolutionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PollutionViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: PollutionViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int = items.size


    class PollutionViewHolder(
        private val binding: ItemListPolutionBinding,
        val itemClick: (Pair<String, String>) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(item: Pair<String, String>) {
            with(item) {
                itemView.setOnClickListener { itemClick(this) }
                binding.run {
                    tvNamePolution.text = item.first
                    tvPolution.text = item.second.toDouble().formatRoundedDouble()
                    constraintLayout.setBackgroundColor(ContextCompat.getColor(itemView.context, item.second.toDouble().toStatusColor()))
                }
            }

        }
    }

}