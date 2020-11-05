package com.dzakdzaks.movieapp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.dzakdzaks.movieapp.R
import com.dzakdzaks.movieapp.data.remote.entity.ResponsePopularMovie
import com.dzakdzaks.movieapp.databinding.ActivityMainBinding
import com.dzakdzaks.movieapp.util.base.Error
import com.dzakdzaks.movieapp.util.base.Progress
import com.dzakdzaks.movieapp.util.base.Success
import com.dzakdzaks.movieapp.util.extension.animateLayoutChanges
import com.dzakdzaks.movieapp.util.extension.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       /* lifecycleScope.launch {
            delay(300L)
            getPopularMovies()
        }*/
        binding.parentLayout.animateLayoutChanges()
        setupTabs()
    }

    private fun setupTabs() {
        binding.viewpager.adapter = MainViewPagerAdapter(supportFragmentManager, lifecycle)

        binding.tabs.addTab(
            binding.tabs.newTab().setText(getString(R.string.explore))
                .setIcon(ContextCompat.getDrawable(this, R.drawable.ic_explore))
        )
        binding.tabs.addTab(
            binding.tabs.newTab().setText(getString(R.string.home))
                .setIcon(ContextCompat.getDrawable(this, R.drawable.ic_home))
        )
        binding.tabs.addTab(
            binding.tabs.newTab().setText(getString(R.string.account))
                .setIcon(ContextCompat.getDrawable(this, R.drawable.ic_person))
        )

        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewpager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        binding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.tabs.selectTab(binding.tabs.getTabAt(position))
            }
        })
    }

    private fun getPopularMovies() {
        viewModel.getPopularMovies(1).observe(this, {
            when (it) {
                is Success<ResponsePopularMovie> -> {
                    Timber.tag("wakwaw").d(Gson().toJson(it.data))
                }
                is Error -> {
                    Snackbar.make(
                        findViewById(android.R.id.content),
                        it.message,
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                is Progress -> {
                    if (it.isLoading)
                        Snackbar.make(
                            findViewById(android.R.id.content),
                            "Fetching Data...",
                            Snackbar.LENGTH_INDEFINITE
                        ).apply {
                            show()
                        }
                    else {
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

                }
            }
        })
    }
}