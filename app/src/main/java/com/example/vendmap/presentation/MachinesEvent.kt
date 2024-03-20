package com.example.vendmap.presentation

import com.example.vendmap.data.Machine
import java.time.LocalDate
import java.util.Date

sealed interface MachinesEvent {

    object SortMachines: MachinesEvent

    data class DeleteMachine(val machine: Machine): MachinesEvent
    data class SaveMachine(
        val name: String, // name
        val model: String, // model
        //val dateInstalled: Long, // date installed
        val location: String, // location
       // val cost: Double,
        val receiptImage: String? // Optional field
    ): MachinesEvent

}