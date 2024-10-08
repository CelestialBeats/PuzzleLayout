package com.hypersoft.puzzlelayouts.app.flow.entrance.ui

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.hypersoft.puzzlelayouts.R
import com.hypersoft.puzzlelayouts.databinding.ActivityMainBinding
import com.hypersoft.puzzlelayouts.utilities.base.activity.BaseActivity
import com.hypersoft.puzzlelayouts.utilities.extensions.onBackPressedDispatcher

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private val navController by lazy { (supportFragmentManager.findFragmentById(binding.fcvContainerMain.id) as NavHostFragment).navController }

    override fun onPreCreated() {
        installSplashTheme()
        enableMaterialDynamicTheme()
        hideStatusBar(1)
    }

    override fun onCreated() {
        registerBackPress()

        navController.addOnDestinationChangedListener(destinationChangeListener)
    }

    private fun registerBackPress() {
        onBackPressedDispatcher {
            when (navController.currentDestination?.id) {
                R.id.fragmentEntrance -> {
                }

                else -> navController.popBackStack()
            }
        }
    }

    private val destinationChangeListener = NavController.OnDestinationChangedListener { _, destination, _ ->
        when (destination.id) {
            R.id.fragmentEntrance -> {
                includeTopPadding = false
                includeBottomPadding = false
            }

            else -> {
                includeTopPadding = true
                includeBottomPadding = true
                hideStatusBar(0)
            }
        }
    }
}