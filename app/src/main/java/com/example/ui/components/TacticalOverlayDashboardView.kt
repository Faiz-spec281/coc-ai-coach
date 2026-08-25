package com.example.ui.components

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.*
import com.example.ui.theme.*

@Composable
fun TacticalOverlayDashboardView(
    selectedTh: TownHallLevel,
    baseBitmap: Bitmap?,
    baseDrawableRes: Int,
    strategy: AttackStrategy,
    analysisResult: BaseAnalysisResult,
    isAnalyzing: Boolean,
    showZones: Boolean,
    showArrows: Boolean,
    showSteps: Boolean,
    onToggleZones: () -> Unit,
    onToggleArrows: () -> Unit,
    onToggleSteps: () -> Unit,
    onImageSelected: (Bitmap) -> Unit,
    onNavigateToTimeline: () -> Unit,
    onNavigateToSimulator: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            try {
                val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    val source = ImageDecoder.createSource(context.contentResolver, uri)
                    ImageDecoder.decodeBitmap(source) { decoder, _, _ ->
                        decoder.isMutableRequired = true
                    }
                } else {
                    @Suppress("DEPRECATION")
                    MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
                }
                onImageSelected(bitmap)
            } catch (e: Exception) {
                // handle safely
            }
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(GeoDarkBg)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // 1. Upload & Status Bar Card
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = GeoSurface),
                shape = RoundedCornerShape(20.dp),
                border = androidx.compose.foundation.BorderStroke(1.5.dp, GeoBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "📸 SCREENSHOT BASE MUSUH",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Black,
                            color = GeoPrimary,
                            letterSpacing = 1.sp
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = if (baseBitmap != null) "Gambar Custom Terpasang" else "Base Contoh TH ${selectedTh.level}",
                            fontSize = 12.sp,
                            color = GeoTextSecondary
                        )
                    }

                    Button(
                        onClick = { imagePickerLauncher.launch("image/*") },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GeoPrimary,
                            contentColor = GeoOnPrimary
                        ),
                        shape = RoundedCornerShape(12.dp),
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 8.dp),
                        modifier = Modifier.testTag("upload_screenshot_button")
                    ) {
                        Icon(Icons.Default.AddPhotoAlternate, contentDescription = null, tint = GeoOnPrimary, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Upload SS", fontSize = 11.sp, fontWeight = FontWeight.Black)
                    }
                }
            }
        }

        // 2. Untouched Base Screenshot with Tactical Overlay & Geometric Controls
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                TacticalBaseCanvas(
                    baseBitmap = baseBitmap,
                    baseDrawableRes = baseDrawableRes,
                    overlay = strategy.tacticalOverlay,
                    showZones = showZones,
                    showArrows = showArrows,
                    showSteps = showSteps
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Overlay Controls (Toggles for Circles, Arrows, Numbers)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterChip(
                        selected = showZones,
                        onClick = onToggleZones,
                        label = { Text("⭕ Zona Bunder", fontSize = 10.sp, fontWeight = FontWeight.Bold) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = GeoPrimary,
                            selectedLabelColor = GeoOnPrimary,
                            containerColor = GeoSurfaceContainer,
                            labelColor = GeoTextSecondary
                        ),
                        border = FilterChipDefaults.filterChipBorder(
                            enabled = true,
                            selected = showZones,
                            borderColor = if (showZones) GeoPrimaryLight else GeoBorder
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.weight(1f)
                    )
                    FilterChip(
                        selected = showArrows,
                        onClick = onToggleArrows,
                        label = { Text("➡️ Arah Panah", fontSize = 10.sp, fontWeight = FontWeight.Bold) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = GeoPrimary,
                            selectedLabelColor = GeoOnPrimary,
                            containerColor = GeoSurfaceContainer,
                            labelColor = GeoTextSecondary
                        ),
                        border = FilterChipDefaults.filterChipBorder(
                            enabled = true,
                            selected = showArrows,
                            borderColor = if (showArrows) GeoPrimaryLight else GeoBorder
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.weight(1f)
                    )
                    FilterChip(
                        selected = showSteps,
                        onClick = onToggleSteps,
                        label = { Text("🔢 Nomor Urut", fontSize = 10.sp, fontWeight = FontWeight.Bold) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = GeoPrimary,
                            selectedLabelColor = GeoOnPrimary,
                            containerColor = GeoSurfaceContainer,
                            labelColor = GeoTextSecondary
                        ),
                        border = FilterChipDefaults.filterChipBorder(
                            enabled = true,
                            selected = showSteps,
                            borderColor = if (showSteps) GeoPrimaryLight else GeoBorder
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        // 3. AI Base Weakness & Tactical Analysis Card
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = GeoSurface),
                shape = RoundedCornerShape(24.dp),
                border = androidx.compose.foundation.BorderStroke(1.5.dp, GeoBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    // Header with 3-Star Potential pill
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "🔍 SCAN KELEMAHAN BASE",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Black,
                            color = GeoPrimary,
                            letterSpacing = 1.sp
                        )
                        Surface(
                            color = GeoGreenContainer,
                            shape = RoundedCornerShape(8.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, GeoGreen)
                        ) {
                            Text(
                                text = "⭐️⭐️⭐️ 98.4% POTENSI B3",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Black,
                                color = GeoGreen,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Tipe Base: ${analysisResult.baseStyle}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = GeoTextPrimary
                    )
                    Text(
                        text = "Rating 3-Star: ${analysisResult.threeStarDifficultyRating}",
                        fontSize = 12.sp,
                        color = GeoGreen
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Weakness Items with Geometric Accents
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        WeaknessItem(label = "Eagle Artillery:", value = analysisResult.weaknessScan.eagleArtilleryStatus, icon = "🦅", accentColor = GeoAmber)
                        WeaknessItem(label = "Mode Inferno:", value = analysisResult.weaknessScan.infernoModes, icon = "🔥", accentColor = GeoRed)
                        WeaknessItem(label = "Arah Air Sweeper:", value = analysisResult.weaknessScan.airSweeperFacing, icon = "💨", accentColor = GeoBlue)
                        WeaknessItem(label = "Arah Potong Funnel:", value = "${analysisResult.weaknessScan.primaryFunnelSides.first} & ${analysisResult.weaknessScan.primaryFunnelSides.second}", icon = "✂️", accentColor = GeoPrimary)
                        WeaknessItem(label = "Sudut Serangan Utama:", value = analysisResult.weaknessScan.recommendedEntryAngle, icon = "🎯", accentColor = GeoGreen)
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Pro Tips
                    analysisResult.customProTips.forEach { tip ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 3.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(GeoSurfaceContainer)
                                .border(1.dp, GeoBorderSubtle, RoundedCornerShape(12.dp))
                                .padding(10.dp)
                        ) {
                            Text(
                                text = "💡 $tip",
                                fontSize = 11.sp,
                                color = GeoPrimaryLight,
                                lineHeight = 16.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Action Navigation Buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            onClick = onNavigateToTimeline,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = GeoSurfaceContainer,
                                contentColor = GeoTextPrimary
                            ),
                            border = androidx.compose.foundation.BorderStroke(1.dp, GeoBorder),
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier.weight(1f).testTag("view_steps_button")
                        ) {
                            Icon(Icons.Default.AccessTime, contentDescription = null, tint = GeoPrimary, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Waktu Pasukan", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }

                        Button(
                            onClick = onNavigateToSimulator,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = GeoPrimary,
                                contentColor = GeoOnPrimary
                            ),
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier.weight(1f).testTag("view_simulator_button")
                        ) {
                            Icon(Icons.Default.PlayCircle, contentDescription = null, tint = GeoOnPrimary, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Simulasi 3-Star", fontSize = 11.sp, fontWeight = FontWeight.Black)
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun WeaknessItem(label: String, value: String, icon: String, accentColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(GeoSurfaceContainer)
            .border(
                width = 1.dp,
                color = GeoBorderSubtle,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 10.dp, vertical = 7.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(4.dp, 16.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(accentColor)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = icon, fontSize = 13.sp)
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = "$label ",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = GeoTextMuted
        )
        Text(
            text = value,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            color = GeoTextPrimary
        )
    }
}

