package com.example.spaceabuser.ui.play

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.spaceabuser.R
import com.example.spaceabuser.databinding.FragmentPlayBinding
import com.example.spaceabuser.ui.ScreensViewModel
import com.example.spaceabuser.ui.game_over.GameOverFragment
import com.example.spaceabuser.ui.landed.LandedFragment
import com.example.spaceabuser.util.Constants
import com.example.spaceabuser.util.Screens
import kotlinx.coroutines.launch
import kotlin.properties.Delegates
import kotlin.random.Random

class PlayFragment : Fragment() {

    companion object {
        fun newInstance() = PlayFragment()
    }

    private val viewModel: PlayViewModel by activityViewModels()
    private lateinit var binding: FragmentPlayBinding
    private val screensViewModel: ScreensViewModel by activityViewModels()
    private var clickItem: View? = null
    private var countLanded by Delegates.notNull<Int>()
    private var coordinate = -1 to -1
    var flag = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayBinding.inflate(inflater, container, false)

        generateFirstColumn()
        generateSecondColumn()
        generateThirdColumn()
        generateFourthColumn()
        generateFifthColumn()

        lifecycleScope.launch {
            viewModel.stateScore.collect {
                if (it != null) {
                    countLanded = it
                }
                binding.tvLanded.text = "$it: landed"
            }
        }
        binding.iV00.setOnClickListener {
            checkClick(it, 0, 0)
        }
        binding.iV01.setOnClickListener {
            checkClick(it, 0, 1)
        }
        binding.iV02.setOnClickListener {
            checkClick(it, 0, 2)
        }
        binding.iV03.setOnClickListener {
            checkClick(it, 0, 3)
        }
        binding.iV04.setOnClickListener {
            checkClick(it, 0, 4)
        }

        binding.iV10.setOnClickListener {
            checkClick(it, 1, 0)
        }
        binding.iV11.setOnClickListener {
            checkClick(it, 1, 1)
        }
        binding.iV12.setOnClickListener {
            checkClick(it, 1, 2)
        }
        binding.iV13.setOnClickListener {
            checkClick(it, 1, 3)
        }
        binding.iV14.setOnClickListener {
            checkClick(it, 1, 4)
        }
        binding.iV20.setOnClickListener {
            checkClick(it, 2, 0)
        }
        binding.iV21.setOnClickListener {
            checkClick(it, 2, 1)
        }
        binding.iV22.setOnClickListener {
            checkClick(it, 2, 2)
        }
        binding.iV23.setOnClickListener {
            checkClick(it, 2, 3)
        }
        binding.iV24.setOnClickListener {
            checkClick(it, 2, 4)
        }
        binding.btnRate.setOnClickListener {
            screensViewModel.loadState(Screens.RATE)
        }
        binding.btnBackPlay.setOnClickListener {
            screensViewModel.loadState(Screens.MAIN)
        }
        return binding.root
    }

    private fun checkClick(view: View, i: Int, j: Int) {
        if (clickItem != null) {
            swapImage(view as ImageView, clickItem as ImageView)
            swapListOfImageView(coordinate, i to j)
            checkListOfImageView()
            checkLoseOrWin()

            clickItem = null
            coordinate = -1 to -1
        } else {
            clickItem = view
            coordinate = i to j
        }
    }

    private fun checkLoseOrWin() {
        var count = 0
        for (i in Constants.listOfImageView)
            for (j in i)
                if (j == 1)
                    count++
        if (count in 1..2) {

            replaceFragment(GameOverFragment())
        }
        if (count < 1) {
            viewModel.loadState(0)
            replaceFragment(LandedFragment())
        }
    }

    private fun checkListOfImageView() {

        val list = Constants.listOfImageView
        for (i in 0..4) {
            if (list[0][i] == 1 && list[1][i] == 1 && list[2][i] == 1) {
                when (i) {
                    0 -> generateFirstColumn()
                    1 -> generateSecondColumn()
                    2 -> generateThirdColumn()
                    3 -> generateFourthColumn()
                    4 -> generateFifthColumn()
                }
                countLanded += 3
                viewModel.loadState(countLanded)


                flag = 1
            }
        }
    }

    fun swapImage(imageView1: ImageView, imageView2: ImageView) {
        val buffer = imageView1.drawable
        imageView1.setImageDrawable(imageView2.drawable)
        imageView2.setImageDrawable(buffer)
    }

    fun swapListOfImageView(coordinate1: Pair<Int, Int>, coordinate2: Pair<Int, Int>) {
        val bufferCoordinate = Constants.listOfImageView[coordinate1.first][coordinate1.second]
        Constants.listOfImageView[coordinate1.first][coordinate1.second] =
            Constants.listOfImageView[coordinate2.first][coordinate2.second]
        Constants.listOfImageView[coordinate2.first][coordinate2.second] = bufferCoordinate
    }

    private fun generateFifthColumn() {
        binding.iV04.setImageResource(getItem(0, 4))
        binding.iV14.setImageResource(getItem(1, 4))
        binding.iV24.setImageResource(getItem(2, 4))
    }

    private fun generateFourthColumn() {
        binding.iV03.setImageResource(getItem(0, 3))
        binding.iV13.setImageResource(getItem(1, 3))
        binding.iV23.setImageResource(getItem(2, 3))
    }

    private fun generateThirdColumn() {
        binding.iV02.setImageResource(getItem(0, 2))
        binding.iV12.setImageResource(getItem(1, 2))
        binding.iV22.setImageResource(getItem(2, 2))
    }

    private fun generateSecondColumn() {
        binding.iV01.setImageResource(getItem(0, 1))
        binding.iV11.setImageResource(getItem(1, 1))
        binding.iV21.setImageResource(getItem(2, 1))
    }


    fun getItem(i: Int, j: Int): Int {
        val random = Random.nextInt(Constants.mapOfItems.size)
        if (random in 8..11)
            Constants.listOfImageView[i][j] = 1
        else
            Constants.listOfImageView[i][j] = 0
        return Constants.mapOfItems[random]
    }

    private fun generateFirstColumn() {
        binding.iV00.setImageResource(getItem(0, 0))
        binding.iV10.setImageResource(getItem(1, 0))
        binding.iV20.setImageResource(getItem(2, 0))
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment)
        fragmentTransaction.commit()
    }
}