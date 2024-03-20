package com.example.vendmap

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.vendmap.R

@Composable
fun BottomNavigationBar(
    selectedItem: MutableState<Int>,
    navController: NavController
) {
    val items = listOf("Home", "Dashboard", "CSV")
    val homeIcon = painterResource(id = R.drawable.baseline_home_24) // Replace with your own SVG file
    val dashboardIcon = painterResource(id = R.drawable.baseline_insert_chart_24) // Replace with your own SVG file
    val csvIcon = painterResource(id = R.drawable.baseline_insert_drive_file_24) // Replace with your own SVG file



    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    when (index) {
                        0 -> Icon(painter = homeIcon, contentDescription = item)
                        1 -> Icon(painter = dashboardIcon, contentDescription = item)
                        2 -> Icon(painter = csvIcon, contentDescription = item)
                        else -> Icon(painter = homeIcon, contentDescription = item)
                    }
                },
                label = { Text(item) },
                selected = selectedItem.value == index,
                onClick = {
                    selectedItem.value = index
                    when (index) {
                        0 -> navController.navigate("MainScreen") // Assuming "home" is the route for mainscreen.kt
                        1 -> navController.navigate("DashboardScreen") // Assuming "dashboard" is the route for dashboardscreen.kt
                        2 -> navController.navigate("CsvScreen")// Handle CSV selection (optional, based on your navigation setup)
                    }
                          },
            )
        }
    }
}
