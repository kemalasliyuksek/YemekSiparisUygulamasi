package com.example.yemeksiparisuygulamasi.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yemeksiparisuygulamasi.data.model.Yemek
import com.example.yemeksiparisuygulamasi.data.repository.YemeklerRepository
import kotlinx.coroutines.launch

class YemeklerViewModel : ViewModel() {
    private val repository = YemeklerRepository()

    private val _yemeklerListesi = MutableLiveData<List<Yemek>>()
    val yemeklerListesi: LiveData<List<Yemek>> = _yemeklerListesi

    private val _yukleniyor = MutableLiveData<Boolean>()
    val yukleniyor: LiveData<Boolean> = _yukleniyor

    private val _hata = MutableLiveData<String>()
    val hata: LiveData<String> = _hata

    init {
        tumYemekleriGetir()
    }

    fun tumYemekleriGetir() {
        viewModelScope.launch {
            try {
                _yukleniyor.value = true
                val yemekler = repository.tumYemekleriGetir()
                _yemeklerListesi.value = yemekler
                _yukleniyor.value = false
            } catch (e: Exception) {
                _hata.value = "Yemekler yüklenirken hata oluştu: ${e.message}"
                _yukleniyor.value = false
            }
        }
    }
}