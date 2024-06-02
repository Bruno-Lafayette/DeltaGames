package com.example.deltagames.view.profileScreen.components

import androidx.compose.runtime.Composable
import com.example.deltagames.util.ContextProvider
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabContent(tabs:List<TabItem>, pagerState: PagerState, contextProvider: ContextProvider){
    HorizontalPager(
        count = tabs.size,
        state = pagerState,
    ) {page ->
        tabs[page].screens(contextProvider)
    }
}
