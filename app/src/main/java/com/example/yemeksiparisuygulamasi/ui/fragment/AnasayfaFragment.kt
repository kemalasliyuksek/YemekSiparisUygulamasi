package com.example.yemeksiparisuygulamasi.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.yemeksiparisuygulamasi.R
import com.example.yemeksiparisuygulamasi.databinding.FragmentAnasayfaBinding
import com.example.yemeksiparisuygulamasi.ui.adapter.YemeklerAdapter
import com.example.yemeksiparisuygulamasi.ui.viewmodel.YemeklerViewModel

class AnasayfaFragment : Fragment() {
    private var _binding: FragmentAnasayfaBinding? = null
    private val binding get() = _binding!!

    private val viewModel: YemeklerViewModel by viewModels()
    private lateinit var adapter: YemeklerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnasayfaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()

        binding.fabSepet.setOnClickListener {
            findNavController().navigate(R.id.action_anasayfaFragment_to_sepetFragment)
        }

        setHasOptionsMenu(true)
    }

    private fun setupRecyclerView() {
        adapter = YemeklerAdapter { yemek ->
            val action = AnasayfaFragmentDirections.actionAnasayfaFragmentToYemekDetayFragment(yemek)
            findNavController().navigate(action)
        }
        binding.recyclerViewYemekler.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.yemeklerListesi.observe(viewLifecycleOwner) { yemekler ->
            adapter.submitList(yemekler)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_anasayfa, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_sepet -> {
                findNavController().navigate(R.id.action_anasayfaFragment_to_sepetFragment)
                true
            }
            R.id.action_refresh -> {
                viewModel.tumYemekleriGetir()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}