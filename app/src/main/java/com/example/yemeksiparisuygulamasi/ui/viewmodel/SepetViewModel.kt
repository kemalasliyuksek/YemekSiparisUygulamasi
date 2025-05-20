package com.example.yemeksiparisuygulamasi.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yemeksiparisuygulamasi.data.model.SepetYemek
import com.example.yemeksiparisuygulamasi.data.repository.YemeklerRepository
import kotlinx.coroutines.launch

class SepetViewModel : ViewModel() {
    private val repository = YemeklerRepository()

    private val _sepetYemekler = MutableLiveData<List<SepetYemek>>()
    val sepetYemekler: LiveData<List<SepetYemek>> = _sepetYemekler

    private val _sepetToplamTutar = MutableLiveData<Int>()
    val sepetToplamTutar: LiveData<Int> = _sepetToplamTutar

    private val _yukleniyor = MutableLiveData<Boolean>()
    val yukleniyor: LiveData<Boolean> = _yukleniyor

    private val _hata = MutableLiveData<String>()
    val hata: LiveData<String> = _hata

    init {
        sepettekiYemekleriGetir()
    }

    fun sepettekiYemekleriGetir() {
        viewModelScope.launch {
            try {
                _yukleniyor.value = true
                val sepetYemekler = repository.sepettekiYemekleriGetir()
                _sepetYemekler.value = sepetYemekler
                hesaplaToplamTutar(sepetYemekler)
                _yukleniyor.value = false
            } catch (e: Exception) {
                _hata.value = "Sepet yüklenirken hata oluştu: ${e.message}"
                _yukleniyor.value = false
            }
        }
    }

    fun sepettenYemekSil(sepetYemekId: Int) {
        viewModelScope.launch {
            try {
                _yukleniyor.value = true
                val sonuc = repository.sepettenYemekSil(sepetYemekId)
                if (sonuc) {
                    sepettekiYemekleriGetir()
                } else {
                    _hata.value = "Yemek sepetten silinemedi"
                }
                _yukleniyor.value = false
            } catch (e: Exception) {
                _hata.value = "Sepetten silme işleminde hata oluştu: ${e.message}"
                _yukleniyor.value = false
            }
        }
    }

    private fun hesaplaToplamTutar(sepetYemekler: List<SepetYemek>) {
        var toplam = 0
        sepetYemekler.forEach { sepetYemek ->
            toplam += sepetYemek.yemekFiyat * sepetYemek.yemekSiparisAdet
        }
        _sepetToplamTutar.value = toplam
    }
}