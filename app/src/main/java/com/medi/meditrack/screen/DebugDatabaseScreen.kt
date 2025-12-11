package com.medi.meditrack.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.medi.meditrack.screen.viewmodel.MedicationListViewModel

@Composable
fun DebugDatabaseScreen(
    viewModel: MedicationListViewModel = hiltViewModel()
) {
    val meds = viewModel.medications.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn {
            items(meds.value) { item ->
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = "ID: ${item.id} | Name: ${item.name} | Dose: ${item.dosage}",
                        modifier = Modifier.padding(vertical = 6.dp)

                    )
                    Button(onClick = { viewModel.deleteMedication(item) }) {
                        Text("Delete")
                    }

                }

            }
        }
    }
}