package com.example.vendmap.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Sort
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vendmap.BottomNavigationBar
import com.example.vendmap.R


@Composable
fun MainScreen(
    state: MachineState,
    navController: NavController,
    onEvent: (MachinesEvent) -> Unit,
    selectedItem: MutableState<Int>
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Scaffold(
            topBar = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        modifier = Modifier.weight(1f),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                    IconButton(onClick = { onEvent(MachinesEvent.SortMachines) }) {
                        Icon(
                            imageVector = Icons.Rounded.Sort,
                            contentDescription = "Sort Notes",
                            modifier = Modifier.size(35.dp),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    state.name.value = ""
                    state.model.value = ""
                    state.location.value = ""
                    state.dateInstalled.value = null
                    navController.navigate("AddMachineScreen")
                }) {
                    Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add new note")
                }
            },
            bottomBar = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(65.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BottomNavigationBar(selectedItem,navController)
                }
            },
        ) { paddingValues ->

            LazyColumn(
                contentPadding = paddingValues,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                ,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                items(state.machines.size) { index ->
                    MachineItem(
                        state = state,
                        index = index,
                        onEvent = onEvent
                    )
                }

            }

        }



    }
}

@Composable
fun MachineItem(
    state: MachineState,
    index: Int,
    onEvent: (MachinesEvent) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {

            // Name Text
            Text(
                text = state.machines[index].name,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

            Spacer(modifier = Modifier.height(8.dp))
            // Name Text
            Text(
                text = "Model: " + state.machines[index].model,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Text(
                text = "Model: " + state.machines[index].model,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )



            // Text
        }

        //Delete Icon
        IconButton(
            onClick = {
                onEvent(MachinesEvent.DeleteMachine(state.machines[index]))
            }
        ) {

            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = "Delete Note",
                modifier = Modifier.size(35.dp),
                tint = MaterialTheme.colorScheme.onPrimaryContainer,

                )

        }

    }
}