package com.medi.meditrack.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.medi.meditrack.R

val RobotoMedium = FontFamily(
    Font(R.font.roboto_medium, weight = FontWeight.Medium)
)

val AppTypography = Typography(
    bodyLarge = androidx.compose.ui.text.TextStyle(
        fontFamily = RobotoMedium,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    titleLarge = androidx.compose.ui.text.TextStyle(
        fontFamily = RobotoMedium,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp
    ),
)