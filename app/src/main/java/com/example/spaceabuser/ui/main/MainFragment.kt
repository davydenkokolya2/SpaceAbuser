package com.example.spaceabuser.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.spaceabuser.databinding.FragmentMainBinding
import com.example.spaceabuser.ui.ScreensViewModel
import com.example.spaceabuser.util.Screens

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private val screensViewModel: ScreensViewModel by activityViewModels()
    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.btnPlay.setOnClickListener {
            screensViewModel.loadState(Screens.PLAY)
        }
        binding.btnSetting.setOnClickListener {
            screensViewModel.loadState(Screens.SETTING)
        }
        binding.btnRules.setOnClickListener {
            screensViewModel.loadState(Screens.RULES)
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}