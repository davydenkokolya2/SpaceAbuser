package com.example.spaceabuser.ui.rate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.spaceabuser.databinding.FragmentRateBinding
import com.example.spaceabuser.ui.ScreensViewModel
import com.example.spaceabuser.ui.play.PlayViewModel
import com.example.spaceabuser.util.Screens
import kotlinx.coroutines.launch

class RateFragment : Fragment() {

    private lateinit var binding: FragmentRateBinding
    private lateinit var viewModel: RateViewModel
    private val screensViewModel: ScreensViewModel by activityViewModels()
    private val playViewModel: PlayViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRateBinding.inflate(inflater, container, false)
        lifecycleScope.launch {
            playViewModel.stateScore.collect {
                binding.tvShotDownShips.text = it.toString()
                if (it != null) {
                    binding.tvBosses.text = (it/2).toString()
                    binding.tvBonusPoint.text = (it/3).toString()
                }
            }
        }
        binding.btnBack.setOnClickListener {
            screensViewModel.loadState(Screens.PLAY)
        }
        return binding.root
    }
}