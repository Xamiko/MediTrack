package com.medi.meditrack.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GreetingPillScreen(onBack: () -> Unit) {

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
                PickTime()

                BasicTextFieldWithBackground(
                    "Type of medication",
                    "e.g, Lisinopril",
                    "Please specify the name of the medication you are tracking."
                )
                BasicTextFieldWithBackground(
                    "Dosage",
                    "e.g, 10mg",
                    "Enter the dosage for the medication."
                )
                FrequenseLasyColumn()
                PeriodLasyColumn()
                BottomButton("Save Medication", "Cancel", onBack)
            }
        }
    )
}



@Composable
fun PickTime(){
    Column(modifier = Modifier
        .padding(0.dp, 0.dp, 0.dp, 0.dp)) {
        Text(text = "Add medication")

        val selectedHour = remember { mutableStateOf(12) }
        val selectedMinute = remember { mutableStateOf(30) }
        var text by remember { mutableStateOf("ваау") }
        var pills: String = ""
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            TimeScrollPicker(
                range = 0..23,
                selectedValue = selectedHour.value,
                onValueSelected = { selectedHour.value = it }
            )
            Spacer(modifier = Modifier.width(16.dp))
            TimeScrollPicker(
                range = 0..59,
                selectedValue = selectedMinute.value,
                onValueSelected = { selectedMinute.value = it }
            )

        }
    }
}


@Composable
fun BottomButton(
textSaveMedication : String,
textCancel : String,
onBack: () -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Button(
            modifier = Modifier
                .weight(1f).padding(12.dp,0.dp,4.dp,0.dp,),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.onBackground, ),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.background, contentColor = MaterialTheme.colorScheme.onBackground),
            onClick = {}) { Text(text = "Cancel") }
        Button(
            modifier = Modifier
                .weight(1f).padding(4.dp,0.dp,12.dp,0.dp,),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.onBackground, ),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onBackground, contentColor = MaterialTheme.colorScheme.background),
            onClick = {onBack()}) {Text(text = "Save Medication") }
    }

}






@Composable
fun BasicTextFieldWithBackground(
    label: String,
    placeholder: String,
    helperText: String
) {
    var text by remember { mutableStateOf("") }
    Column(modifier = Modifier.padding(12.dp)) {

        Text(modifier = Modifier, style = MaterialTheme.typography.bodyLarge, text = label, color = MaterialTheme.colorScheme.onBackground)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp)
                .padding(0.dp, 10.dp, 0.dp, 10.dp)
                .background(
                    color = MaterialTheme.colorScheme.onPrimary,
                    shape = RoundedCornerShape(4.dp)
                )
                .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(8.dp)) // Рамка
        ) {
            BasicTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 18.sp
                ),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary)
            )
            if (text.isEmpty()) {
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
        Text(modifier = Modifier, style = MaterialTheme.typography.bodyLarge, text = helperText, color = MaterialTheme.colorScheme.outline)
    }
}

@Composable
fun FrequenseLasyColumn() {
    val listFrequency = listOf("Mon", "Tue", "Wed", "Thu", "Fri","Sat","Sun")
    val listState = rememberLazyListState()

    Column(modifier = Modifier.padding(12.dp)) {
        Text(modifier = Modifier, style = MaterialTheme.typography.bodyLarge, text = "Pepiod" ,color = MaterialTheme.colorScheme.onBackground)
        LazyRow(state = listState) {

            itemsIndexed(listFrequency) { index, item ->
                Card(
                    modifier = Modifier
                        .padding(0.dp,4.dp,4.dp,0.dp )
                        .height(50.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onBackground, contentColor = MaterialTheme.colorScheme.background)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = item, textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}

@Composable
fun PeriodLasyColumn() {
    val listFrequency = listOf("Once a Day", "Twice a Day", "Three Times a Day", "As needed")
    val listState = rememberLazyListState()

    Column(modifier = Modifier.padding(12.dp)) {
        Text(modifier = Modifier, style = MaterialTheme.typography.bodyLarge, text = "Frequense" ,color = MaterialTheme.colorScheme.onBackground)
        LazyRow(state = listState) {

            itemsIndexed(listFrequency) { index, item ->
                Card(
                    modifier = Modifier
                        .padding(0.dp,4.dp,4.dp,0.dp )
                        .height(50.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onBackground, contentColor = MaterialTheme.colorScheme.background)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = item, textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}



@Composable
fun TimeScrollPicker(
    range: IntRange,
    selectedValue: Int,
    onValueSelected: (Int) -> Unit
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val itemHeightDp = 40.dp
    val itemHeightPx = with(LocalDensity.current) { itemHeightDp.toPx() } // Переводим dp в пиксели


    LaunchedEffect(Unit) {
        coroutineScope.launch {
            val indexToScroll = selectedValue - range.first
            listState.scrollToItem(indexToScroll)
        }
    }


    LaunchedEffect(listState.firstVisibleItemIndex, listState.firstVisibleItemScrollOffset) {
        val offset = listState.firstVisibleItemScrollOffset
        val index = listState.firstVisibleItemIndex


        val centerIndex = if (offset >= itemHeightPx / 2) index + 1 else index

        val valueInCenter = range.first + centerIndex

        if (valueInCenter in range && valueInCenter != selectedValue) {
            onValueSelected(valueInCenter)
        }
    }

    LazyColumn(
        state = listState,
        modifier = Modifier
            .width(60.dp)
            .height(120.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 40.dp)
    ) {
        items(range.count()) { index ->
            val value = range.first + index
            val isSelected = value == selectedValue

            Text(
                text = value.toString().padStart(2, '0'),
                fontSize = if (isSelected) 24.sp else 18.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                color = if (isSelected) Color.Black else Color.Gray,
                modifier = Modifier
                    .height(itemHeightDp)
                    .fillMaxWidth()
            )
        }
    }
}


