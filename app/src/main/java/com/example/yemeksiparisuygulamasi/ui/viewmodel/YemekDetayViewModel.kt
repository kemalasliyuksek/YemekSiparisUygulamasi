package com.example.yemeksiparisuygulamasi.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yemeksiparisuygulamasi.data.model.Yemek
import com.example.yemeksiparisuygulamasi.data.repository.YemeklerRepository
import kotlinx.coroutines.launch

class YemekDetayViewModel : ViewModel() {
    private val repository = YemeklerRepository()

    private val _sepeteEklendiMi = MutableLiveData<Boolean>()
    val sepeteEklendiMi: LiveData<Boolean> = _sepeteEklendiMi

    private val _yukleniyor = MutableLiveData<Boolean>()
    val yukleniyor: LiveData<Boolean> = _yukleniyor

    private val _hata = MutableLiveData<String>()
    val hata: LiveData<String> = _hata

    fun sepeteYemekEkle(yemek: Yemek, adet: Int) {
        viewModelScope.launch {
            try {
                _yukleniyor.value = true
                val sonuc = repository.sepeteYemekEkle(yemek, adet)
                _sepeteEklendiMi.value = sonuc
                _yukleniyor.value = false
            } catch (e: Exception) {
                _hata.value = "Sepete eklenirken hata oluştu: ${e.message}"
                _yukleniyor.value = false
            }
        }
    }

    fun resetSepeteDurum() {
        _sepeteEklendiMi.value = false
    }
}