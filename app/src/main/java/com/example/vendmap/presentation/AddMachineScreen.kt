package com.example.vendmap.presentation

import android.widget.CalendarView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.vendmap.BottomNavigationBar

@Composable
fun AddMachineScreen(
    state: MachineState,
    navController: NavController,
    onEvent: (MachinesEvent) -> Unit,
    selectedItem: MutableState<Int>
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {

                onEvent(MachinesEvent.SaveMachine(
                    name = state.name.value,
                    model = state.model.value,
                    //dateInstalled = state.dateInstalled.value,
                    location = state.location.value,
                    //cost = state.cost.value ? state.cost.value : null,
                    receiptImage = state.receiptImage.value,

                ))
                navController.popBackStack()
            }) {

                Icon(
                    imageVector = Icons.Rounded.Check,
                    contentDescription = "Save Note"
                )

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

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {

            // NAME TEXT FIELD
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = state.name.value,
//                onValueChange = {
//                    state.name.value = it
//                },
                onValueChange = {
                    state.name.value = it.trimStart { it == '0' }
                },
                textStyle = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp
                ),
                label  = {
                    Text(text = "Name")
                }
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = state.model.value,
                onValueChange = {
                    state.model.value = it.trimStart { it == '0' }
                },
                textStyle = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp
                ),
                label  = {
                    Text(text = "Model")
                }
            )

            AndroidView(
                { CalendarView(it) },
                modifier = Modifier.wrapContentWidth(),
                update = { views ->
                    views.setOnDateChangeListener { calendarView, year, month, dayOfMonth ->
                    }
                }
            )







        }

    }

}
