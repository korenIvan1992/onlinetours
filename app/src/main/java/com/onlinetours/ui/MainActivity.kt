package com.onlinetours.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.onlinetours.MyApp
import com.onlinetours.R
import com.onlinetours.base.extensions.makeVisibleOrGone
import com.onlinetours.base.extensions.onClick
import com.onlinetours.databinding.ActivityMainBinding
import com.onlinetours.ui.favorite.FavoritesFragment
import com.onlinetours.ui.help.HelpFragment
import com.onlinetours.ui.search.CallBackData
import com.onlinetours.ui.search.SearchFragment
import com.onlinetours.ui.settings.SettingsFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainActivity : AppCompatActivity(), CallBackData {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private lateinit var binding: ActivityMainBinding

    companion object {
        const val SEARCH = "SearchFragment"
        const val HELP = "HelpFragment"
        const val FAVORITE = "FavoritesFragment"
        const val SETTINGS = "SettingsFragment"
    }

    private val searchFragment by lazy { SearchFragment() }
    private val helpFragment by lazy { HelpFragment() }
    private val favoritesFragment by lazy { FavoritesFragment() }
    private val settingsFragment by lazy { SettingsFragment() }

    private var activeFragment: Fragment = searchFragment

    private val navigator = object : AppNavigator(this, R.id.container_ciceron) {
        override fun setupFragmentTransaction(
            screen: FragmentScreen,
            fragmentTransaction: FragmentTransaction,
            currentFragment: Fragment?,
            nextFragment: Fragment
        ) {
        }

        override fun applyCommands(commands: Array<out Command>) {
            super.applyCommands(commands)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeDagger()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().apply {
            add(R.id.container_fragment, settingsFragment, SETTINGS).hide(
                settingsFragment
            )
            add(R.id.container_fragment, favoritesFragment, FAVORITE).hide(favoritesFragment)
            add(R.id.container_fragment, helpFragment, HELP).hide(helpFragment)
            add(R.id.container_fragment, searchFragment, SEARCH)
        }.commitAllowingStateLoss()

        binding.navView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.search -> {
                    swapFragment(searchFragment)
                    true
                }

                R.id.help -> {
                    swapFragment(helpFragment)
                    true
                }

                R.id.favorites -> {
                    swapFragment(favoritesFragment)
                    true
                }

                R.id.settings -> {
                    swapFragment(settingsFragment)
                    true
                }

                else -> true
            }
        }
        binding.progress.onClick {}
    }

    override fun onStart() {
        super.onStart()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onStop() {
        super.onStop()
        navigatorHolder.removeNavigator()
    }

    @SuppressLint("CommitTransaction")
    private fun swapFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .hide(activeFragment)
            .show(fragment)
            .commit()
        activeFragment = fragment
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach { fragment ->
            if (fragment != null && fragment.isVisible) {
                with(fragment.childFragmentManager) {
                    if (backStackEntryCount > 0) {
                        popBackStack()
                        return
                    }
                }
            }
        }
        super.onBackPressed()
    }

    fun showProgress(boolean: Boolean) {
        CoroutineScope(Dispatchers.Main).launch {
            binding.progress.makeVisibleOrGone(boolean)
        }
    }

    private fun initializeDagger() {
        (applicationContext as MyApp)
            .mainComponent
            .inject(this)
    }

    override fun setData(any: Any) {
        (activeFragment as CallBackData).setData(any)
    }
}