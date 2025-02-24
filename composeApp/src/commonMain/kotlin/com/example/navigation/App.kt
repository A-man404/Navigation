package com.example.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
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
import androidx.compose.ui.unit.sp
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
        Box(
            modifier = Modifier.fillMaxSize().background(Color.White)
                .padding(WindowInsets.systemBars.asPaddingValues())
        ) {
            Navigator(MainScreen)
        }
    }

}


object MainScreen : Screen {
    @Composable
    override fun Content() {
        val name = remember { mutableStateOf("") }
        val navigator = LocalNavigator.currentOrThrow
        Scaffold(topBar = {
            TopAppBar(
                backgroundColor = Color(0xff89dceb),
                elevation = 1.dp,
                title = { Text("Navigation With Voyager", color = Color(0xff1e1e2e)) },
            )
        }) {
            Column(
                modifier = Modifier.fillMaxSize().background(Color(0xff89dceb)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "Navigation with Voyager",
                    fontSize = 32.sp,
                    color = Color(0xff1e1e2e),

                    )
                Spacer(Modifier.height(40.dp))
                TextField(modifier = Modifier.fillMaxWidth().padding(16.dp),
                    value = name.value,
                    onValueChange = { name.value = it },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(16.dp),
                    placeholder = { Text("Please enter your name", color = Color.Gray) })
                Spacer(Modifier.height(16.dp))
                Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffa6e3a1)),
                    onClick = {
                        navigator.push(SecondScreen(name.value))

                    }) {
                    Text("Send Name")
                }
                Spacer(Modifier.height(16.dp))
                Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xfffab387)),
                    onClick = {
                        navigator.push(ThirdScreen)
                    }) {
                    Text("Go to Third Screen")
                }

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
                    BottomNavigation(
                        backgroundColor = Color(0xff1e1e2e),
                        elevation = 16.dp,
                        contentColor = Color(0xffcdd6f4)
                    ) {
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
                modifier = Modifier.fillMaxSize().background(Color(0xffb4befe)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("HomeScreen")
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
                modifier = Modifier.fillMaxSize().background(Color(0xfff38ba8)),
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
                modifier = Modifier.fillMaxSize().background(Color(0xfff9e2af)),
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