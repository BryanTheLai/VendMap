package com.example.vendmap.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "machines")
data class Machine(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String, // name
    val model: String, // model
    val dateInstalled: Long?, // date installed
    val location: String, // location
    val cost: Double,
    val receiptImage: String? // Optional field
)
