package com.medi.meditrack.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.foundation.lazy.items
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel

import com.medi.meditrack.screen.viewmodel.AddPillViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMedicationScreen(onBack: () -> Unit) {
    val viewModel: AddPillViewModel = hiltViewModel()


    val medicationName = viewModel.medicationName
    val dosage = viewModel.dosage
    val selectedFrequency = viewModel.selectedFrequency



    //val selectedDaysOfWeek = viewModel.selectedDaysOfWeek
    //val frequencyOptions = viewModel.frequencyOptions
    //val daysOfWeek = viewModel.daysOfWeek

    val frequencyOptions = listOf("Once a Day", "Twice a Day", "Three Times a Day", "As needed")


    val daysOfWeek = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
   val selectedDaysOfWeek = remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Medication Tracker") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background),
            )
        },

        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(20.dp)
                    .fillMaxSize()
            ) {
                //icon selection
                Button(onClick = {}) {
                    Text(text = "Select icon", color = MaterialTheme.colorScheme.primary)
                }
                // medication name
                TextField(
                    textState = medicationName,
                    label = "Type of medication",
                    placeholder = "e.g, Lisinopril",
                    helperText = "Please specify the name of the medication you are tracking." ,
                )
                // dosage
                TextField(
                    textState = dosage,
                    label =  "Dosage",
                    placeholder = "e.g, 10mg",
                    helperText = "Enter the dosage for the medication."
                )
                FrequentLazyColumn(option = frequencyOptions, selectedItem = selectedFrequency)
                FrequentLazyColumn(option = daysOfWeek, selectedItem = selectedDaysOfWeek)



            }
        }
    )
}



@Composable
fun TextField(
    textState: MutableState<String>,
    label: String,
    placeholder: String,
    helperText: String
) {
    Column(modifier = Modifier.padding(4.dp)) {

        Text(
            text = label,
            modifier = Modifier,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp)
                .padding(0.dp, 5.dp, 0.dp, 5.dp)
                .background(
                    color = MaterialTheme.colorScheme.onPrimary,
                    shape = RoundedCornerShape(4.dp)
                )
                .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(8.dp))
        ) {
            BasicTextField(
                value = textState.value,
                onValueChange = { textState.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 18.sp
                ),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary)
            )
            if (textState.value.isEmpty()) {
                Text(
                    text = placeholder,
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                        fontSize = 18.sp
                    ),
                    modifier = Modifier
                        .padding(start = 16.dp, top = 16.dp)
                )
            }
        }
        Text(modifier = Modifier, style = MaterialTheme.typography.bodyLarge, text = helperText, color = MaterialTheme.colorScheme.onSurface)
    }
}
@Composable
fun BottomButton(
    onBack: () -> Unit
) {

    Row(modifier = Modifier.fillMaxWidth()) {
        Button(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp, 0.dp, 4.dp, 0.dp),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.onBackground),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground
            ),
            onClick = { /**/ }) {
            Text(text = "Cancel")
        }

        Button(
            modifier = Modifier
                .weight(1f)
                .padding(4.dp, 0.dp, 12.dp, 0.dp),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.onBackground),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onBackground,
                contentColor = MaterialTheme.colorScheme.background
            ),
            onClick = { /**/ }) {
            Text("Save")
        }
    }
}



@Composable
fun FrequentLazyColumn(
    option: List<String>,
    selectedItem: MutableState<String>
) {
    val listState = rememberLazyListState()

    Column(modifier = Modifier.padding(12.dp)) {
        Text(
            text = "Period",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        LazyRow(
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(option) { item ->
                Card(
                    modifier = Modifier
                        .height(50.dp)
                        .width(140.dp)
                        .clickable { selectedItem.value = item },
                    colors = CardDefaults.cardColors(
                        containerColor = if (selectedItem.value == item)
                            MaterialTheme.colorScheme.tertiary // selected
                        else
                            MaterialTheme.colorScheme.primary  // default state
                    )
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = item,
                            color = if (selectedItem.value == item)
                                MaterialTheme.colorScheme.onPrimary
                            else MaterialTheme.colorScheme.background
                        )
                    }
                }
            }
        }
    }
}






