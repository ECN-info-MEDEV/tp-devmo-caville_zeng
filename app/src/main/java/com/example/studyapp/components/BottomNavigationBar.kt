package com.example.studyapp.ui.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.RowScope
import androidx.compose.ui.res.painterResource
import com.example.studyapp.R

@Composable
fun BottomNavigationBar(
    currentTab: Int,
    onTabSelected: (Int) -> Unit
) {
    val tabs = listOf(
        "Home" to R.drawable.ic_baseline_home_24,
        "Statistics" to R.drawable.ic_baseline_query_stats_24,
        "Creation" to R.drawable.ic_outline_add_box_24,
        "Settings" to R.drawable.ic_baseline_person_24
    )

    BottomNavigation(contentColor = Color.Black, backgroundColor = Color.White) {
        tabs.forEachIndexed { index, item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.second), contentDescription = "") },
                label = { Text(text = item.first) },
                selected = currentTab == index,
                onClick = { onTabSelected(index) }
            )
        }
    }
}
