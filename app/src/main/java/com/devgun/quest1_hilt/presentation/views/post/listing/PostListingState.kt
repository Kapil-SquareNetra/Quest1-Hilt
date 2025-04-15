package com.devgun.quest1_hilt.presentation.views.post.listing

import com.devgun.quest1_hilt.presentation.ui.models.LoaderDisplayInfo
import com.devgun.quest1_hilt.presentation.ui.models.PostItemModel

data class PostListingState(
    val posts: List<PostItemModel> = emptyList(),
    val showLoader: LoaderDisplayInfo = LoaderDisplayInfo()
)
