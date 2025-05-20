package com.example.yemeksiparisuygulamasi.data.repository

import com.example.yemeksiparisuygulamasi.data.api.RetrofitClient
import com.example.yemeksiparisuygulamasi.data.model.BaseResponse
import com.example.yemeksiparisuygulamasi.data.model.SepetYemek
import com.example.yemeksiparisuygulamasi.data.model.Yemek

class YemeklerRepository {
    private val service = RetrofitClient.yemeklerService

    companion object {
        const val KULLANICI_ADI = "nurkumbasar"
    }

    suspend fun tumYemekleriGetir(): List<Yemek> {
        val response = service.tumYemekleriGetir()
        if (response.isSuccessful) {
            return response.body()?.yemekler ?: emptyList()
        }
        return emptyList()
    }

    suspend fun sepeteYemekEkle(yemek: Yemek, adet: Int): Boolean {
        try {
            val mevcutSepet = sepettekiYemekleriGetir()

            val ayniYemek = mevcutSepet.find { it.yemekAdi == yemek.ad }

            if (ayniYemek != null) {
                sepettenYemekSil(ayniYemek.sepetYemekId)

                val yeniAdet = ayniYemek.yemekSiparisAdet + adet

                val response = service.sepeteYemekEkle(
                    yemekAdi = yemek.ad,
                    yemekResimAdi = yemek.resimAdi,
                    yemekFiyat = yemek.fiyat,
                    yemekSiparisAdet = yeniAdet,
                    kullaniciAdi = KULLANICI_ADI
                )
                return response.isSuccessful && response.body()?.success == 1
            } else {
                val response = service.sepeteYemekEkle(
                    yemekAdi = yemek.ad,
                    yemekResimAdi = yemek.resimAdi,
                    yemekFiyat = yemek.fiyat,
                    yemekSiparisAdet = adet,
                    kullaniciAdi = KULLANICI_ADI
                )
                return response.isSuccessful && response.body()?.success == 1
            }
        } catch (e: Exception) {
            return false
        }
    }

    suspend fun sepettekiYemekleriGetir(): List<SepetYemek> {
        return try {
            val response = service.sepettekiYemekleriGetir(KULLANICI_ADI)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null && body.success == 1) {
                    body.sepetYemekler ?: emptyList()
                } else {
                    emptyList()
                }
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            println("Sepet getirme hatası: ${e.message}")
            emptyList()
        }
    }

    suspend fun sepettenYemekSil(sepetYemekId: Int): Boolean {
        val response = service.sepettenYemekSil(sepetYemekId, KULLANICI_ADI)
        return response.isSuccessful && response.body()?.success == 1
    }
}