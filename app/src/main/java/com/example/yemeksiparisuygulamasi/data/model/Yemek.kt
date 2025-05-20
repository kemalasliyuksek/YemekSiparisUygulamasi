package com.example.yemeksiparisuygulamasi.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Yemek(
    @SerializedName("yemek_id") val id: Int,
    @SerializedName("yemek_adi") val ad: String,
    @SerializedName("yemek_resim_adi") val resimAdi: String,
    @SerializedName("yemek_fiyat") val fiyat: Int
) : Parcelable