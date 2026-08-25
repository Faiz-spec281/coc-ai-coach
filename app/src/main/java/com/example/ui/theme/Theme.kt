package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val GeometricBalanceColorScheme = darkColorScheme(
    primary = GeoPrimary,
    onPrimary = GeoOnPrimary,
    primaryContainer = GeoPrimaryContainer,
    onPrimaryContainer = GeoOnPrimaryContainer,
    secondary = GeoPrimaryLight,
    onSecondary = GeoOnPrimary,
    secondaryContainer = GeoSurfaceContainer,
    onSecondaryContainer = GeoTextPrimary,
    tertiary = GeoGreen,
    onTertiary = Color.Black,
    background = GeoDarkBg,
    onBackground = GeoTextPrimary,
    surface = GeoSurface,
    onSurface = GeoTextPrimary,
    surfaceVariant = GeoSurfaceContainer,
    onSurfaceVariant = GeoTextSecondary,
    outline = GeoBorder,
    outlineVariant = GeoBorderSubtle,
    error = GeoRed,
    onError = Color.Black
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = true,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = GeometricBalanceColorScheme,
        typography = Typography,
        content = content
    )
}

