package com.example.spaceabuser.ui.rules

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.spaceabuser.databinding.FragmentRulesBinding
import com.example.spaceabuser.ui.ScreensViewModel
import com.example.spaceabuser.util.Screens

class RulesFragment : Fragment() {

    private lateinit var binding: FragmentRulesBinding
    private lateinit var viewModel: RulesViewModel
    private val screensViewModel: ScreensViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRulesBinding.inflate(inflater, container, false)
        binding.btnBackRules.setOnClickListener {
            screensViewModel.loadState(Screens.MAIN)
        }
        return binding.root
    }
}