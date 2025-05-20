package com.example.yemeksiparisuygulamasi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yemeksiparisuygulamasi.data.model.SepetYemek
import com.example.yemeksiparisuygulamasi.databinding.ItemSepetBinding

class SepetAdapter(private val onDeleteClick: (Int) -> Unit) :
    ListAdapter<SepetYemek, SepetAdapter.SepetViewHolder>(SepetDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SepetViewHolder {
        val binding = ItemSepetBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SepetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SepetViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SepetViewHolder(private val binding: ItemSepetBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.imageViewSil.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onDeleteClick(getItem(position).sepetYemekId)
                }
            }
        }

        fun bind(sepetYemek: SepetYemek) {
            binding.textViewSepetYemekAdi.text = sepetYemek.yemekAdi
            binding.textViewSepetYemekFiyat.text = "₺${sepetYemek.yemekFiyat}"
            binding.textViewSepetYemekAdet.text = "Adet: ${sepetYemek.yemekSiparisAdet}"

            val resimUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${sepetYemek.yemekResimAdi}"
            Glide.with(binding.root)
                .load(resimUrl)
                .into(binding.imageViewSepetYemek)
        }
    }

    class SepetDiffCallback : DiffUtil.ItemCallback<SepetYemek>() {
        override fun areItemsTheSame(oldItem: SepetYemek, newItem: SepetYemek): Boolean {
            return oldItem.sepetYemekId == newItem.sepetYemekId
        }

        override fun areContentsTheSame(oldItem: SepetYemek, newItem: SepetYemek): Boolean {
            return oldItem == newItem
        }
    }
}