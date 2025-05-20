package com.example.yemeksiparisuygulamasi.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.yemeksiparisuygulamasi.R
import com.example.yemeksiparisuygulamasi.databinding.FragmentYemekDetayBinding
import com.example.yemeksiparisuygulamasi.ui.viewmodel.YemekDetayViewModel

class YemekDetayFragment : Fragment() {
    private var _binding: FragmentYemekDetayBinding? = null
    private val binding get() = _binding!!

    private val viewModel: YemekDetayViewModel by viewModels()
    private val args: YemekDetayFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentYemekDetayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        setupObservers()
    }

    private fun setupUI() {
        val yemek = args.yemek

        binding.textViewYemekAdi.text = yemek.ad
        binding.textViewYemekFiyat.text = "₺${yemek.fiyat}"

        val resimUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.resimAdi}"
        Glide.with(this)
            .load(resimUrl)
            .into(binding.imageViewYemek)

        binding.numberPicker.minValue = 1
        binding.numberPicker.maxValue = 10
        binding.numberPicker.value = 1

        binding.buttonSepeteEkle.setOnClickListener {
            val adet = binding.numberPicker.value
            viewModel.sepeteYemekEkle(yemek, adet)
        }
    }

    private fun setupObservers() {
        viewModel.sepeteEklendiMi.observe(viewLifecycleOwner) { sepeteEklendi ->
            if (sepeteEklendi) {
                viewModel.resetSepeteDurum()

                val dialog = AlertDialog.Builder(requireContext())
                    .setTitle("Ürün Sepete Eklendi")
                    .setMessage("${args.yemek.ad} sepetinize eklendi. Ne yapmak istersiniz?")
                    .setPositiveButton("Sepete Git") { dialog, _ ->
                        dialog.dismiss()
                        if (isAdded && !isDetached && !requireActivity().isFinishing) {
                            findNavController().navigate(R.id.action_yemekDetayFragment_to_sepetFragment)
                        }
                    }
                    .setNegativeButton("Alışverişe Devam Et") { dialog, _ ->
                        dialog.dismiss()
                        if (isAdded && !isDetached && !requireActivity().isFinishing) {
                            findNavController().popBackStack()
                        }
                    }
                    .setCancelable(false)
                    .create()

                dialog.show()
            }
        }

        viewModel.yukleniyor.observe(viewLifecycleOwner) { yukleniyor ->
            binding.progressBar.visibility = if (yukleniyor) View.VISIBLE else View.GONE
            binding.buttonSepeteEkle.isEnabled = !yukleniyor
        }

        viewModel.hata.observe(viewLifecycleOwner) { hata ->
            if (hata.isNotEmpty()) {
                Toast.makeText(requireContext(), hata, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}