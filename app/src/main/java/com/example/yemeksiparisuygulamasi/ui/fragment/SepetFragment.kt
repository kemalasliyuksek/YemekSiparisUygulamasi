package com.example.yemeksiparisuygulamasi.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.yemeksiparisuygulamasi.databinding.FragmentSepetBinding
import com.example.yemeksiparisuygulamasi.ui.adapter.SepetAdapter
import com.example.yemeksiparisuygulamasi.ui.viewmodel.SepetViewModel

class SepetFragment : Fragment() {
    private var _binding: FragmentSepetBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SepetViewModel by viewModels()
    private lateinit var adapter: SepetAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSepetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = SepetAdapter { sepetYemekId ->
            viewModel.sepettenYemekSil(sepetYemekId)
        }
        binding.recyclerViewSepet.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.sepetYemekler.observe(viewLifecycleOwner) { sepetYemekler ->
            adapter.submitList(sepetYemekler)

            if (sepetYemekler.isEmpty()) {
                binding.textViewBosUyari.visibility = View.VISIBLE
                binding.recyclerViewSepet.visibility = View.GONE
                binding.cardViewToplam.visibility = View.GONE
            } else {
                binding.textViewBosUyari.visibility = View.GONE
                binding.recyclerViewSepet.visibility = View.VISIBLE
                binding.cardViewToplam.visibility = View.VISIBLE
            }
        }

        viewModel.sepetToplamTutar.observe(viewLifecycleOwner) { toplamTutar ->
            binding.textViewToplamTutar.text = "₺$toplamTutar"
        }

        viewModel.yukleniyor.observe(viewLifecycleOwner) { yukleniyor ->
            binding.progressBar.visibility = if (yukleniyor) View.VISIBLE else View.GONE
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