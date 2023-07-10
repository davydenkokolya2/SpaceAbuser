package com.example.spaceabuser.ui

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.spaceabuser.R
import com.example.spaceabuser.databinding.ActivityMainBinding
import com.example.spaceabuser.ui.main.MainFragment
import com.example.spaceabuser.ui.play.PlayFragment
import com.example.spaceabuser.ui.rate.RateFragment
import com.example.spaceabuser.ui.rules.RulesFragment
import com.example.spaceabuser.ui.setting.SettingFragment
import com.example.spaceabuser.ui.setting.SettingViewModel
import com.example.spaceabuser.util.Screens
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val screensViewModel: ScreensViewModel by viewModels()
    private val languageViewModel: LanguageViewModel by viewModels()
    private val settingViewModel: SettingViewModel by viewModels()
    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        mediaPlayer = MediaPlayer.create(this, R.raw.music)
        mediaPlayer.isLooping = true
        lifecycleScope.launch {
            screensViewModel.stateScreen.collect {
                when (it) {
                    Screens.PLAY -> replaceFragment(PlayFragment())
                    Screens.SETTING -> replaceFragment(SettingFragment())
                    Screens.MAIN -> replaceFragment(MainFragment())
                    Screens.RATE -> replaceFragment(RateFragment())
                    Screens.RULES -> replaceFragment(RulesFragment())
                    else -> {}
                }
            }
        }
        lifecycleScope.launch {
            languageViewModel.stateLanguage.collect {
                setAppLocale(it)
            }
        }

        lifecycleScope.launch {
            settingViewModel.stateMusic.collect {
                when (it) {
                    "on" -> {
                        if (settingViewModel.stateSound.value == "on")
                            mediaPlayer.start()
                    }
                    "off" -> mediaPlayer.pause()
                }
            }
        }
        lifecycleScope.launch {
            settingViewModel.stateSound.collect {
                when (it) {
                    "on" -> {
                        if (settingViewModel.stateMusic.value == "on")
                            mediaPlayer.start()
                    }
                    "off" -> mediaPlayer.pause()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        mediaPlayer.start()
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.pause()
    }

    private fun setAppLocale(language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = this.resources.configuration
        config.setLocale(locale)
        this.createConfigurationContext(config)
        this.resources.updateConfiguration(config, this.resources.displayMetrics)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment)
        fragmentTransaction.commit()
    }
}