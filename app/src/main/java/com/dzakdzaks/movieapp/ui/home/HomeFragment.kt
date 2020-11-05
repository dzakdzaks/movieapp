package com.dzakdzaks.movieapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.dzakdzaks.movieapp.R
import com.dzakdzaks.movieapp.databinding.FragmentHomeBinding
import com.dzakdzaks.movieapp.ui.MainActivity
import com.dzakdzaks.movieapp.ui.MainViewModel
import com.dzakdzaks.movieapp.util.extension.viewBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.takeIf { it.containsKey(TAB) }?.apply {
            binding.textView.text = getString(TAB)
        }
    }

    companion object {
        const val TAB = "tab"

        fun newInstance(
            title: String
        ) = HomeFragment().apply {
            arguments = Bundle().apply {
                putString(TAB, title)
            }
        }

    }
}