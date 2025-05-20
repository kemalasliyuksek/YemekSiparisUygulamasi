package com.example.yemeksiparisuygulamasi.data.api

import com.example.yemeksiparisuygulamasi.data.model.BaseResponse
import com.example.yemeksiparisuygulamasi.data.model.SepetYemeklerResponse
import com.example.yemeksiparisuygulamasi.data.model.YemeklerResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface YemeklerService {

    @GET("tumYemekleriGetir.php")
    suspend fun tumYemekleriGetir(): Response<YemeklerResponse>

    @POST("sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun sepeteYemekEkle(
        @Field("yemek_adi") yemekAdi: String,
        @Field("yemek_resim_adi") yemekResimAdi: String,
        @Field("yemek_fiyat") yemekFiyat: Int,
        @Field("yemek_siparis_adet") yemekSiparisAdet: Int,
        @Field("kullanici_adi") kullaniciAdi: String
    ): Response<BaseResponse>

    @POST("sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun sepettekiYemekleriGetir(
        @Field("kullanici_adi") kullaniciAdi: String
    ): Response<SepetYemeklerResponse>

    @POST("sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun sepettenYemekSil(
        @Field("sepet_yemek_id") sepetYemekId: Int,
        @Field("kullanici_adi") kullaniciAdi: String
    ): Response<BaseResponse>
}