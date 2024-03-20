package com.example.vendmap.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.vendmap.data.Machine
import java.time.LocalDate
import java.util.Date

data class MachineState(
    val machines: List<Machine> = emptyList(),
    val name: MutableState<String> = mutableStateOf(""),
    val model: MutableState<String> = mutableStateOf(""),
    val dateInstalled: MutableState<Long?> = mutableStateOf(null), // Use LocalDate
    val location: MutableState<String> = mutableStateOf(""),
    val cost: MutableState<Double?> = mutableStateOf(null), // Allows null Double value
    val receiptImage: MutableState<String> = mutableStateOf(""),

    )
