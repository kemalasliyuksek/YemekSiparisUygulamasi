package com.example.yemeksiparisuygulamasi.data.model

import com.google.gson.annotations.SerializedName

data class YemeklerResponse(
    @SerializedName("yemekler") val yemekler: List<Yemek>,
    @SerializedName("success") val success: Int
)