package com.medi.meditrack.database.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "medications")
data class Medication(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    //TODO: Add more fields as necessary (period, start date, end date)
    val name: String,
    val dosage: String,
    val frequency: String
)