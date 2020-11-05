package com.dzakdzaks.movieapp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.dzakdzaks.movieapp.R
import com.dzakdzaks.movieapp.data.remote.entity.ResponsePopularMovie
import com.dzakdzaks.movieapp.databinding.ActivityMainBinding
import com.dzakdzaks.movieapp.ui.home.HomeFragment
import com.dzakdzaks.movieapp.util.base.Error
import com.dzakdzaks.movieapp.util.base.Progress
import com.dzakdzaks.movieapp.util.base.Success
import com.dzakdzaks.movieapp.util.extension.getStatusBarHeight
import com.dzakdzaks.movieapp.util.extension.makeStatusBarTransparent
import com.dzakdzaks.movieapp.util.extension.setMargins
import com.dzakdzaks.movieapp.util.extension.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::inflate)

    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        makeStatusBarTransparent(false)
        setupTabs()
    }

    private fun setupTabs() {

        binding.tabLayout.setMargins(30, getStatusBarHeight() + 20, 30, 0)

        val titles = resources.getStringArray(R.array.dashboardMenuTitle)
        val icons = listOf(
            R.drawable.ic_explore,
            R.drawable.ic_home,
            R.drawable.ic_person,
        )

        val adapter = TabViewPagerAdapter(this)
        adapter.addFragment(HomeFragment.newInstance(titles[0]))
        adapter.addFragment(HomeFragment.newInstance(titles[1]))
        adapter.addFragment(HomeFragment.newInstance(titles[2]))
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager, false, true) { tab, position ->
            tab.text = titles[position]
            tab.icon = ContextCompat.getDrawable(this, icons[position])
        }.attach()
    }

    private fun getPopularMovies() {
        viewModel.getPopularMovies(1).observe(this, {
            when (it) {
                is Success<ResponsePopularMovie> -> {
                    lifecycleScope.launch {
                        delay(1000L)
                        Snackbar.make(
                            findViewById(android.R.id.content),
                            "Success Fetch Data.",
                            Snackbar.LENGTH_LONG
                        ).apply {
                            show()
                        }
                    }
                }
                is Error -> {
                    Snackbar.make(
                        findViewById(android.R.id.content),
                        it.message,
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                is Progress -> {
                    if (it.isLoading) {
                        Snackbar.make(
                            findViewById(android.R.id.content),
                            "Fetching Data...",
                            Snackbar.LENGTH_INDEFINITE
                        ).apply {
                            show()
                        }
                    }

                }
            }
        })
    }
}