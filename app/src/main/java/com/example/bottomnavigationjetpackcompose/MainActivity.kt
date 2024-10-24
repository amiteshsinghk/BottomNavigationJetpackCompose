package com.example.bottomnavigationjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigationjetpackcompose.ui.theme.BottomNavigationJetpackComposeTheme
import kotlinx.coroutines.selects.select

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BottomNavigationJetpackComposeTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavigationBar(
                            items = listOf(
                                BottomNavItem(
                                    name = "Home",
                                    route = "home",
                                    icon = Icons.Default.Home
                                ),
                                BottomNavItem(
                                    name = "Chat",
                                    route = "chat",
                                    icon = Icons.Default.Notifications,
                                    badgeCount = 23
                                ),
                                BottomNavItem(
                                    name = "Settings",
                                    route = "settings",
                                    icon = Icons.Default.Settings
                                )
                            ),
                            navController = navController,
                            onItemClick = {
                                navController.navigate(it.route)
                            }
                        )
                    }

                ) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
                    Navigation(navController = navController)
                }
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = "home"){
        composable("home"){
            HomeScreen()
        }
        composable("chat") {
            ChatScreen()
        }
        composable("settings") {
            SettingsScreen()
        }
    }
}

@Composable
fun BottomNavigationBar(items: List<BottomNavItem>,
                        navController: NavHostController,
                        modifier: Modifier = Modifier,
                        onItemClick: (BottomNavItem) -> Unit) {
    val backstackEntry = navController.currentBackStackEntryAsState()

    NavigationBar(
        modifier = modifier,
        containerColor = Color.White,
        contentColor = Color.Green

    ) {
        items.forEach { item->
            val selected = item.route == backstackEntry.value?.destination?.route
        NavigationBarItem(
            selected = selected,
            onClick = { onItemClick(item) },

            icon = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    if(item.badgeCount > 0){
                        BadgedBox(
                            badge = {
                                Text(text = item.badgeCount.toString())
                            }
                        ) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = null
                            )

                        }
                    } else{
                        Icon(
                            imageVector = item.icon,
                            contentDescription = null
                        )
                    }

                    if (selected){
                        Text(
                            text = item.name,
                            textAlign = TextAlign.Center,
                            fontSize = 10.sp
                        )
                    }

                }
            }
        )
    }
    }
}

@Composable
fun HomeScreen() {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(text = "Home screen")
    }
}

@Composable
fun ChatScreen() {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(text = "Chat screen")
    }
}

@Composable
fun SettingsScreen() {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(text = "Setting screen")
    }
}




@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BottomNavigationJetpackComposeTheme {
        Greeting("Android")
    }
}