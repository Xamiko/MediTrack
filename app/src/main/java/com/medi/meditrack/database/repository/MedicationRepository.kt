package com.medi.meditrack.database.repository

import com.medi.meditrack.database.local.Medication
import com.medi.meditrack.database.local.MedicationDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MedicationRepository @Inject constructor(
    private val dao: MedicationDao
) {
    //Auto-updated Flow of medications for any changes
    fun getAllMedicationsFlow(): Flow<List<Medication>> = dao.getAllMedicationsFlow()

    suspend fun insertMedication(medication: Medication) {
        dao.insertMedication(medication)
    }

    suspend fun deleteMedication(medication: Medication) {
        dao.deleteMedication(medication)
    }

    //suspend fun getAllMedications(): List<Medication> {
    //    return dao.getAllMedications()
    //}
  }