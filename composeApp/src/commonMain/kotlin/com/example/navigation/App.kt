package com.example.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import cafe.adriel.voyager.navigator.tab.TabOptions

@Composable
fun App() {
    MaterialTheme {

        Navigator(MainScreen)
    }

}


object MainScreen : Screen {
    @Composable
    override fun Content() {
        val name = remember { mutableStateOf("") }
        val navigator = LocalNavigator.currentOrThrow
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(value = name.value,
                onValueChange = { name.value = it },
                placeholder = { Text("Please enter your name") })
            Spacer(Modifier.height(16.dp))
            Button(onClick = {
                navigator.push(SecondScreen(name.value))
            }) {
                Text("Send Name")
            }
            Spacer(Modifier.height(16.dp))
            Button(onClick = {
                navigator.push(ThirdScreen)
            }) {
                Text("Go to Third Screen")
            }

        }
    }

    data class SecondScreen(val name: String) : Screen {
        @Composable
        override fun Content() {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(name)
            }
        }
    }


    object ThirdScreen : Screen {
        @Composable
        override fun Content() {
            TabNavigator(HomeScreen) { tabNavigator ->
                Scaffold(bottomBar = {
                    BottomNavigation(backgroundColor = Color(0xffb4befe), elevation = 16.dp) {
                        BottomNavigationItem(selected = tabNavigator.current == HomeScreen,
                            onClick = { tabNavigator.current = HomeScreen },
                            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                            label = { Text("Home") })
                        BottomNavigationItem(selected = tabNavigator.current == ProfileScreen,
                            onClick = { tabNavigator.current = ProfileScreen },
                            icon = {
                                Icon(
                                    Icons.Default.Person, contentDescription = "Profile"
                                )
                            },
                            label = { Text("Profile") })
                        BottomNavigationItem(selected = tabNavigator.current == SettingsScreen,
                            onClick = { tabNavigator.current = SettingsScreen },
                            icon = {
                                Icon(
                                    Icons.Default.Settings, contentDescription = "Settings"
                                )
                            },
                            label = { Text("Settings") })
                    }
                }) {
                    tabNavigator.current.Content()
                }
            }
        }
    }

    object HomeScreen : Tab {

        @Composable
        override fun Content() {
            val navigator = LocalNavigator.currentOrThrow
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(onClick = { navigator.push(ProfileScreen) }) {
                    Text("Go To Profile Screen")
                }
            }

        }

        override val options: TabOptions
            @Composable get() = TabOptions(
                title = "Home", index = 0u, icon = rememberVectorPainter(Icons.Default.Home)
            )
    }

    object ProfileScreen : Tab {
        @Composable
        override fun Content() {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("ProfileScreen")
            }
        }

        override val options: TabOptions
            @Composable get() = TabOptions(
                title = "Profile", index = 0u, icon = rememberVectorPainter(Icons.Default.Person)
            )
    }


    object SettingsScreen : Tab {
        @Composable
        override fun Content() {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("SettingsScreen")
            }
        }

        override val options: TabOptions
            @Composable get() = TabOptions(
                title = "Settings", index = 0u, icon = rememberVectorPainter(Icons.Default.Settings)
            )
    }


}