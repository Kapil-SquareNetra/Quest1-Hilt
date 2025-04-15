package com.devgun.quest1_hilt.presentation.views.post.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

class PostOverviewFragment: Fragment() {

    private val navController: NavController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                PostOverviewScreen(
                    viewModel = hiltViewModel<PostOverviewViewModel>()
                ) {
                    navController.navigateUp()
                }
            }
        }
    }
}