package com.medi.meditrack.screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medi.meditrack.database.local.Medication
import com.medi.meditrack.database.repository.MedicationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicationListViewModel @Inject constructor(
    private val repo: MedicationRepository
) : ViewModel() {

   // private val _medications = MutableStateFlow<List<Medication>>(emptyList())
   // val medications = _medications


    val medications: StateFlow<List<Medication>> = repo.getAllMedicationsFlow()
        .stateIn( // collects
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun deleteMedication(medication: Medication) {
        viewModelScope.launch {
            repo.deleteMedication(medication)
            //_medications.value = repo.getAllMedications()  // update the list after deletion
        }
    }
}
