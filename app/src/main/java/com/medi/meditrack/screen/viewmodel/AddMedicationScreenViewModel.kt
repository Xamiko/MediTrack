package com.medi.meditrack.screen.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medi.meditrack.database.local.Medication
import com.medi.meditrack.database.repository.MedicationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMedicationScreenViewModel @Inject constructor(
    private val repository: MedicationRepository
) : ViewModel() {

    val medicationName = mutableStateOf("")
    val dosage = mutableStateOf("")
    val selectedFrequency = mutableStateOf("")
    val selectedDaysOfWeek = mutableStateOf("")

    val frequencyOptions = listOf("Once a Day", "Twice a Day", "Three Times a Day", "As needed")
    val daysOfWeek = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")

    fun saveMedication() {
        viewModelScope.launch {
            try {
                repository.insertMedication(
                    Medication(
                        name = medicationName.value,
                        dosage = dosage.value,
                        frequency = selectedFrequency.value
                    )
                )

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
