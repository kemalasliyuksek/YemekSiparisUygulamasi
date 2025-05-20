package com.example.yemeksiparisuygulamasi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yemeksiparisuygulamasi.data.model.Yemek
import com.example.yemeksiparisuygulamasi.databinding.ItemYemekBinding

class YemeklerAdapter(private val onItemClick: (Yemek) -> Unit) :
    ListAdapter<Yemek, YemeklerAdapter.YemekViewHolder>(YemekDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YemekViewHolder {
        val binding = ItemYemekBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return YemekViewHolder(binding)
    }

    override fun onBindViewHolder(holder: YemekViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class YemekViewHolder(private val binding: ItemYemekBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(getItem(position))
                }
            }
        }

        fun bind(yemek: Yemek) {
            binding.textViewYemekAdi.text = yemek.ad
            binding.textViewYemekFiyat.text = "₺${yemek.fiyat}"

            val resimUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.resimAdi}"
            Glide.with(binding.root)
                .load(resimUrl)
                .into(binding.imageViewYemek)
        }
    }

    class YemekDiffCallback : DiffUtil.ItemCallback<Yemek>() {
        override fun areItemsTheSame(oldItem: Yemek, newItem: Yemek): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Yemek, newItem: Yemek): Boolean {
            return oldItem == newItem
        }
    }
}