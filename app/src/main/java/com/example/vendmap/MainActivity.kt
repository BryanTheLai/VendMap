package com.example.vendmap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.vendmap.data.MachinesDatabase
import com.example.vendmap.presentation.AddMachineScreen
import com.example.vendmap.presentation.MachineViewModel
import com.example.vendmap.presentation.MainScreen
import com.example.vendmap.ui.theme.VendMapTheme

class MainActivity : ComponentActivity() {
    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            MachinesDatabase::class.java,
            "machines.db"
        ).build()
    }

    private val viewModel by viewModels<MachineViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return MachineViewModel(database.dao) as T
                }
            }
        }
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VendMapTheme {
                val state by viewModel.state.collectAsState()
                val navController = rememberNavController()
                val selectedItem = remember { mutableStateOf(0) }
                NavHost(navController = navController, startDestination = "MainScreen") {
                    composable("MainScreen") {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            MainScreen(
                                state = state,
                                navController = navController,
                                onEvent = viewModel::onEvent,
                                selectedItem = selectedItem
                            )

                        }
                    }
                    composable("AddMachineScreen") {
                        AddMachineScreen(
                            state = state,
                            navController = navController,
                            onEvent = viewModel::onEvent,
                            selectedItem = selectedItem
                        )
                    }
                }

            }
        }
    }
}