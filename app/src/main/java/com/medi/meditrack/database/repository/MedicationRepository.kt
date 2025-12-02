package com.medi.meditrack.database.repository

import com.medi.meditrack.database.local.Medication
import com.medi.meditrack.database.local.MedicationDao
import javax.inject.Inject

class MedicationRepository @Inject constructor(private val dao: MedicationDao) {

    suspend fun insertMedication(medication: Medication) {
        dao.insertMedication(medication)
    }

    suspend fun getAllMedications(): List<Medication> {
        return dao.getAllMedications()
    }
}