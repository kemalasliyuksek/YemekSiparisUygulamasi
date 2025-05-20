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
            // Önce mevcut sepeti kontrol et
            val mevcutSepet = sepettekiYemekleriGetir()

            // Aynı yemek zaten sepette var mı diye kontrol et
            val ayniYemek = mevcutSepet.find { it.yemekAdi == yemek.ad }

            if (ayniYemek != null) {
                // Eğer aynı yemek varsa, önce onu sil
                sepettenYemekSil(ayniYemek.sepetYemekId)

                // Yeni adeti eski adetle birleştir
                val yeniAdet = ayniYemek.yemekSiparisAdet + adet

                // Güncellenmiş adet ile tekrar ekle
                val response = service.sepeteYemekEkle(
                    yemekAdi = yemek.ad,
                    yemekResimAdi = yemek.resimAdi,
                    yemekFiyat = yemek.fiyat,
                    yemekSiparisAdet = yeniAdet,
                    kullaniciAdi = KULLANICI_ADI
                )
                return response.isSuccessful && response.body()?.success == 1
            } else {
                // Yemek sepette yoksa normal ekleme işlemi yap
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
                // API boş sepet durumunda success=0 dönüyor olabilir, bu durumu kontrol et
                if (body != null && body.success == 1) {
                    body.sepetYemekler ?: emptyList()
                } else {
                    // Success 0 ise veya yanıt beklenen formatta değilse boş liste döndür
                    emptyList()
                }
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            // JSON parse hatası veya başka bir istisna durumunda
            println("Sepet getirme hatası: ${e.message}")
            emptyList() // Hata durumunda da boş liste döndür
        }
    }

    suspend fun sepettenYemekSil(sepetYemekId: Int): Boolean {
        val response = service.sepettenYemekSil(sepetYemekId, KULLANICI_ADI)
        return response.isSuccessful && response.body()?.success == 1
    }
}