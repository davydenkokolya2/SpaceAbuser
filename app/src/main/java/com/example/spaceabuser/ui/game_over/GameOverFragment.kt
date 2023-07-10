package com.example.spaceabuser.ui.game_over

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.spaceabuser.databinding.FragmentGameOverBinding
import com.example.spaceabuser.ui.ScreensViewModel
import com.example.spaceabuser.ui.play.PlayViewModel
import com.example.spaceabuser.util.Screens
import kotlinx.coroutines.launch

class GameOverFragment : Fragment() {

    private lateinit var viewModel: GameOverViewModel
    private lateinit var binding: FragmentGameOverBinding
    private val screensViewModel: ScreensViewModel by activityViewModels()
    private val playViewModel: PlayViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameOverBinding.inflate(inflater, container, false)
        binding.btnBackGameOver.setOnClickListener {
            playViewModel.loadState(0)
            screensViewModel.loadState(Screens.MAIN)
        }
        lifecycleScope.launch {
            playViewModel.stateScore.collect {
                binding.tvPoints.text = it.toString()
                if (it != null) {
                    binding.tvFrags.text = (it/2).toString()
                }

            }
        }
        return binding.root
    }
}