package com.example.navigation

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun App() {
    val snackBarHostState = remember { SnackbarHostState() }
    val selectedItem = remember { mutableStateOf(0) }


    MaterialTheme {
        Scaffold(snackbarHost = { SnackbarHost(hostState = snackBarHostState) }, bottomBar = {
            BottomNavigationBar(selectedItem)
        }) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                when (selectedItem.value) {
                    0 -> ScreenA()
                    1 -> ScreenB()
                    2 -> ScreenC()
                }
            }
        }
    }
}

@Composable
fun ScreenA() {
    Text("This is Home Screen")
}

@Composable
fun ScreenB() {
    Text("This is Profile Screen")
}

@Composable
fun ScreenC() {
    Text("This is Settings Screen")
}

@Composable
fun BottomNavigationBar(selectedItem: MutableState<Int>) {
    val items = listOf(
        NavigationItem("Home", Icons.Default.Home),
        NavigationItem("Profile", Icons.Default.Face),
        NavigationItem("Settings", Icons.Default.Settings)
    )

    BottomNavigation {
        items.forEachIndexed { index, item ->
            val isSelected = selectedItem.value == index
            val iconSize by animateDpAsState(targetValue = if (isSelected) 30.dp else 24.dp)

            BottomNavigationItem(selected = isSelected,
                onClick = { selectedItem.value = index },
                icon = {
                    Icon(
                        item.icon,
                        contentDescription = item.label,
                        modifier = Modifier.size(iconSize)
                    )
                },
                label = { Text(item.label) },
                alwaysShowLabel = isSelected
            )
        }
    }
}

data class NavigationItem(
    val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector
)
