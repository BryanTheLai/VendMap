package com.example.vendmap.presentation

import com.example.vendmap.data.VendingMachineDao
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vendmap.data.Machine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MachineViewModel(private val dao: VendingMachineDao) : ViewModel() {
    private val isSortedByDateAdded = MutableStateFlow(true)

    private var machines =
        isSortedByDateAdded.flatMapLatest { sort ->
            if (sort) {
                dao.getMachinesOrderedByDateInstalled()
            } else {
                dao.getMachinesOrderedByDateInstalled()
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val _state = MutableStateFlow(MachineState())
    val state =
        combine(_state, isSortedByDateAdded, machines) { state, isSortedByDateAdded, machines ->
            state.copy(
                machines = machines
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), MachineState())

    fun onEvent(event: MachinesEvent) {
        when (event) {
            is MachinesEvent.DeleteMachine -> {
                viewModelScope.launch {
                    dao.deleteMachine(event.machine)
                }
            }

            is MachinesEvent.SaveMachine -> {

                val name = state.value.name.value
                val model = state.value.model.value
                val dateInstalled = state.value.dateInstalled.value
                val location = state.value.location.value
                val cost = state.value.cost.value
                val receiptImage = state.value.receiptImage.value
                val machine = Machine(
                    // Use safe call or null coalescing
                    name = name ?: "",
                    model = model ?: "",
                    dateInstalled = dateInstalled,
                    location = location ?: "",
                    cost = cost ?: 0.0, // Default to 0.0 for cost
                    receiptImage = receiptImage ?: ""
                )

                viewModelScope.launch {
                    dao.upsertMachine(machine)
                }

                _state.update {
                    it.copy(
                    name = mutableStateOf(""),
                    model = mutableStateOf(""),
                    dateInstalled= mutableStateOf(null), // Allows null Date value
                    location = mutableStateOf(""),
                    cost = mutableStateOf(null), // Allows null Double value
                    receiptImage = mutableStateOf("")
                    )
                }
            }

            MachinesEvent.SortMachines -> {
                isSortedByDateAdded.value = !isSortedByDateAdded.value
            }
        }
    }
    
}