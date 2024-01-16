package com.eugurguner.homelesspaws.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.eugurguner.homelesspaws.databinding.FragmentHomeBinding
import com.eugurguner.homelesspaws.presentation.adapter.DogAdapter
import com.eugurguner.homelesspaws.presentation.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentHome : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUI()

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            viewModel.refreshDogs()
        }

        binding.btnLogOut.setOnClickListener {
            viewModel.logOut(requireContext())
        }
    }

    private fun updateUI() {
        val adapter = DogAdapter()
        binding.rvDogs.adapter = adapter
        binding.rvDogs.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvDogs)

        viewModel.dogs.observe(viewLifecycleOwner) { dogs ->
            adapter.setDogs(dogs)
        }

        binding.btnLogOut.setOnClickListener { }
    }
}