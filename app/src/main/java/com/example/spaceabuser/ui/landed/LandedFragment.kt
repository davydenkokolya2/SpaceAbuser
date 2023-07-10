package com.example.spaceabuser.ui.landed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.spaceabuser.databinding.FragmentLandedBinding
import com.example.spaceabuser.ui.ScreensViewModel
import com.example.spaceabuser.util.Screens

class LandedFragment : Fragment() {


    private lateinit var viewModel: LandedViewModel
    private lateinit var binding: FragmentLandedBinding
    private val screensViewModel: ScreensViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLandedBinding.inflate(inflater, container, false)
        binding.btnLandedBack.setOnClickListener {
            screensViewModel.loadState(Screens.MAIN)
        }
        return binding.root
    }
}