package com.example.vendmap.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [Machine::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MachinesDatabase: RoomDatabase(){
    abstract val dao: VendingMachineDao
}