package com.devgun.quest1_hilt.presentation.views.post.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostListingFragment: Fragment() {

    private val navController: NavController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                PostListingScreen(
                    viewModel = hiltViewModel<PostListingViewModel>()
                ) {
                    navController.navigate(getDeepLink(it))
                }
            }
        }
    }

    private fun getDeepLink(id: Int) = "android-app://quest1/overview/$id".toUri()
}