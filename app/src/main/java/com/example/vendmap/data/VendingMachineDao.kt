package com.example.vendmap.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface VendingMachineDao {
    @Upsert
    suspend fun upsertMachine(note: Machine)

    @Delete
    suspend fun deleteMachine(note: Machine)

    @Query("SELECT * FROM machines ORDER BY location")
    fun getMachinesOrderedByLocation(): Flow<List<Machine>>

    @Query("SELECT * FROM machines ORDER BY dateInstalled ASC")
    fun getMachinesOrderedByDateInstalled(): Flow<List<Machine>>
}
