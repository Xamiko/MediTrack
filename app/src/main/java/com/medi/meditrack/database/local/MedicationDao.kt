package com.medi.meditrack.database.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicationDao {
    //flow is cold, no started  until collected
    @Query("SELECT * FROM medications")
    fun getAllMedicationsFlow(): Flow<List<Medication>>

    @Insert
    suspend fun insertMedication(medication: Medication)

    @Delete
    suspend fun deleteMedication(medication: Medication)

   // @Query("SELECT * FROM medications")
   // suspend fun getAllMedications(): List<Medication>

}