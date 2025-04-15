package com.devgun.quest1_hilt.presentation.views.post.listing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.devgun.quest1_hilt.R
import com.devgun.quest1_hilt.presentation.ui.composables.CommonHeader
import com.devgun.quest1_hilt.presentation.ui.composables.CommonLoader
import com.devgun.quest1_hilt.presentation.ui.composables.RowItem

@Composable
fun PostListingScreen(
    viewModel: PostListingViewModel,
    onPostClicked: (Int) -> Unit
){
    val state by viewModel.uiState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CommonHeader(
            headerText = "Posts",
            leadingIcon = R.drawable.close
        )
        if (state.showLoader.showLoader){
            Box(modifier = Modifier.fillMaxSize()){
                CommonLoader(loadingText = "Fetching posts, please wait...")
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                itemsIndexed(items = state.posts,
                    key = { _, post -> post.id }
                ) { _, post ->
                    RowItem(
                        post = post,
                        clickEvent = {
                            onPostClicked(post.id)
                        }
                    )
                }
            }
        }
    }
}