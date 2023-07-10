package com.example.spaceabuser.ui.setting

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.spaceabuser.databinding.FragmentSettingBinding
import com.example.spaceabuser.ui.LanguageViewModel
import com.example.spaceabuser.ui.ScreensViewModel
import com.example.spaceabuser.util.Screens
import kotlinx.coroutines.launch

class SettingFragment : Fragment() {
    private val viewModel: SettingViewModel by activityViewModels()
    private val languageViewModel: LanguageViewModel by activityViewModels()
    private lateinit var binding: FragmentSettingBinding
    private val screensViewModel: ScreensViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        binding.btnEng.setOnClickListener {
            languageViewModel.loadState("en")
        }
        binding.btnRus.setOnClickListener {
            languageViewModel.loadState("ru")
        }
        binding.btnPort.setOnClickListener {
            languageViewModel.loadState("pt")
        }
        binding.btnSettingBack.setOnClickListener {
            screensViewModel.loadState(Screens.MAIN)
        }
        binding.btnOffMusic.setOnClickListener {
            viewModel.loadStateMusic("off")
        }
        binding.btnOnMusic.setOnClickListener {
            viewModel.loadStateMusic("on")
        }
        binding.btnOffSound.setOnClickListener {
            viewModel.loadStateSound("off")
        }
        binding.btnOnSound.setOnClickListener {
            viewModel.loadStateSound("on")
        }
        lifecycleScope.launch {
            languageViewModel.stateLanguage.collect {
                Log.d("test", it)
                when (it) {
                    "en" -> binding.imageView16.rotation = 0F
                    "ru" -> binding.imageView16.rotation = 90F
                    "pt" -> binding.imageView16.rotation = 180f
                }
            }
        }
        lifecycleScope.launch {
            viewModel.stateSound.collect {
                when (it) {
                    "on" -> binding.imageView14.rotation = 0F
                    "off" -> binding.imageView14.rotation = 180F
                }
            }
        }
        lifecycleScope.launch {
            viewModel.stateMusic.collect {
                when (it) {
                    "on" -> binding.imageView18.rotation = 0F
                    "off" -> binding.imageView18.rotation = 180F
                }
            }
        }
        return binding.root
    }
}