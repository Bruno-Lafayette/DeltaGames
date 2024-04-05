package com.example.deltagames.view.ProfileScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LeadingIconTab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.deltagames.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState){

    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .height(140.dp)
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.CenterStart,

    ){
       Column {
           Text(
               text = tabs[pagerState.currentPage].title,
               fontSize = 24.sp,
               fontWeight = FontWeight.Black,
               color = Color.White
           )
           Text(
               text = tabs[pagerState.currentPage].subTitle,
               fontSize = 12.sp,
               fontWeight = FontWeight.Medium,
               color = Color.White
           )
       }
    }
    Column (
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
            .background(colorResource(id = R.color.white))
            .padding(16.dp)

    ) {
        TabRow(
            divider = {},
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(colorResource(id = R.color.cloud_white))
                .padding(4.dp)
            ,
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                Box{}
            }
        ) {
            tabs.forEachIndexed{ index, tabItem ->

                LeadingIconTab(

                    selected = pagerState.currentPage == index,
                    modifier = if (pagerState.currentPage == index)
                        Modifier
                            .background(colorResource(id = R.color.cloud_white))
                            .clip(RoundedCornerShape(10.dp))
                            .background(colorResource(id = R.color.white))
                    else
                        Modifier
                            .background(colorResource(id = R.color.cloud_white))
                            .clip(RoundedCornerShape(10.dp))
                            .background(colorResource(id = R.color.cloud_white))
                    ,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            text = tabItem.labelButton,
                            color = colorResource(id = R.color.black)
                        )
                    },
                    icon = { /*TODO*/ })
            }
        }


    }


}



