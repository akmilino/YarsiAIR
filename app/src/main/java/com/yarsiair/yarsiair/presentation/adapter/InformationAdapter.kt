package com.yarsiair.yarsiair.presentation.adapter

import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yarsiair.yarsiair.databinding.ItemListInformationBinding.inflate
import com.yarsiair.yarsiair.databinding.ItemListInformationBinding
import com.yarsiair.yarsiair.models.Information

class InformationAdapter(private val information: ArrayList<Information>): RecyclerView.Adapter<InformationAdapter.ListViewHolder>() {
    inner class ListViewHolder(private val binding: ItemListInformationBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(information: Information){
            binding.run {
                ivScale.setImageResource(information.condition)
                tvCondition.text = SpannableString(information.nameCondition).apply {
                    setSpan(UnderlineSpan(),0,length,0)
                }
                tvConditionDescription.text = information.conditionDescription
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InformationAdapter.ListViewHolder = ListViewHolder(inflate(LayoutInflater.from(parent.context),parent,false))

    override fun getItemCount(): Int = information.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) = holder.bind(information[position])
}