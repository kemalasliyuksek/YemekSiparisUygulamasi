package com.example.yemeksiparisuygulamasi.data.model

import com.google.gson.annotations.SerializedName

data class SepetYemeklerResponse(
    @SerializedName("sepet_yemekler") val sepetYemekler: List<SepetYemek>,
    @SerializedName("success") val success: Int
)