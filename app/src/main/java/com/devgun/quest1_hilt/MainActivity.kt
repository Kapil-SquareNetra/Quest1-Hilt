package com.devgun.quest1_hilt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devgun.quest1_hilt.presentation.PostListing
import com.devgun.quest1_hilt.presentation.PostOverview
import com.devgun.quest1_hilt.presentation.views.post.listing.PostListingScreen
import com.devgun.quest1_hilt.presentation.views.post.listing.PostListingViewModel
import com.devgun.quest1_hilt.presentation.views.post.overview.PostOverviewScreen
import com.devgun.quest1_hilt.presentation.views.post.overview.PostOverviewViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController,
                startDestination = PostListing
            ) {
                composable<PostListing>{
                    PostListingScreen(
                        viewModel = hiltViewModel<PostListingViewModel>()
                    ) {
                        navController.navigate(PostOverview(postId = it))
                    }
                }
                composable<PostOverview> {
                    PostOverviewScreen(viewModel = hiltViewModel<PostOverviewViewModel>()) {
                        navController.navigateUp()
                    }
                }
            }

        }
    }
}


