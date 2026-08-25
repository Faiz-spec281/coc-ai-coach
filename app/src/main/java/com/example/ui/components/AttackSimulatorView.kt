package com.example.ui.components

import android.graphics.Bitmap
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.AttackSimulationStep
import com.example.model.AttackStrategy
import com.example.ui.theme.*

@Composable
fun AttackSimulatorView(
    strategy: AttackStrategy,
    simulationSecond: Int,
    isPlaying: Boolean,
    speedMultiplier: Float,
    baseBitmap: Bitmap?,
    baseDrawableRes: Int,
    onTogglePlay: () -> Unit,
    onReset: () -> Unit,
    onSeekSecond: (Int) -> Unit,
    onSetSpeed: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    // Find active simulation step based on current second
    val currentStep = strategy.simulationSteps.findLast {
        it.second <= simulationSecond
    } ?: strategy.simulationSteps.firstOrNull() ?: AttackSimulationStep(
        second = 0,
        timeRemaining = "2:30",
        phase = "Setup Phase",
        activeAction = "Menurunkan pasukan awal",
        destructionPercent = 10,
        starsEarned = 0,
        townHallDestroyed = false,
        activeTroopPositions = emptyList(),
        activeSpellEffects = emptyList(),
        announcement = "Pertempuran Dimulai"
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(GeoDarkBg)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // 1. Simulator Header with 3-Star Live Ticker
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = GeoSurface),
                shape = RoundedCornerShape(20.dp),
                border = androidx.compose.foundation.BorderStroke(1.5.dp, GeoBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "🎮 SIMULATOR TAKTIK 3-STAR",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Black,
                                color = GeoPrimary,
                                letterSpacing = 1.sp
                            )
                            Text(
                                text = strategy.name,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = GeoTextPrimary
                            )
                        }

                        // Stars Earned Display
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            repeat(3) { index ->
                                val isLit = index < currentStep.starsEarned
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = null,
                                    tint = if (isLit) GeoGreen else GeoBorder,
                                    modifier = Modifier.size(26.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    // Destruction Progress Bar
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Kerusakan Base: ${currentStep.destructionPercent}%",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = GeoTextPrimary
                        )
                        Text(
                            text = if (currentStep.townHallDestroyed) "✅ Town Hall Hancur" else "⏳ Menuju TH",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (currentStep.townHallDestroyed) GeoGreen else GeoAmber
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))
                    LinearProgressIndicator(
                        progress = { currentStep.destructionPercent / 100f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = GeoGreen,
                        trackColor = GeoSurfaceContainer,
                    )
                }
            }
        }

        // 2. Base Canvas with Simulation Overlays
        item {
            TacticalBaseCanvas(
                baseBitmap = baseBitmap,
                baseDrawableRes = baseDrawableRes,
                overlay = strategy.tacticalOverlay,
                showZones = true,
                showArrows = true,
                showSteps = true,
                simulationStep = currentStep
            )
        }

        // 3. Interactive Controls (Timeline Scrubber & Play/Pause)
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = GeoSurface),
                shape = RoundedCornerShape(24.dp),
                border = androidx.compose.foundation.BorderStroke(1.5.dp, GeoBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    // Small sheet handle
                    Box(
                        modifier = Modifier
                            .width(48.dp)
                            .height(5.dp)
                            .clip(RoundedCornerShape(3.dp))
                            .background(GeoBorder)
                            .align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Time Scrubber Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val minutes = simulationSecond / 60
                        val seconds = simulationSecond % 60
                        val timeStr = String.format("%02d:%02d", minutes, seconds)

                        Text(
                            text = "⏱️ Waktu: $timeStr / 02:30",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = GeoPrimaryLight
                        )

                        // Speed selector
                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            listOf(1f, 2f, 4f).forEach { speed ->
                                val isSelected = speedMultiplier == speed
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(if (isSelected) GeoPrimary else GeoSurfaceContainer)
                                        .border(
                                            width = if (isSelected) 1.5.dp else 1.dp,
                                            color = if (isSelected) GeoPrimaryLight else GeoBorder,
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                        .clickable { onSetSpeed(speed) }
                                        .padding(horizontal = 8.dp, vertical = 3.dp)
                                ) {
                                    Text(
                                        text = "${speed.toInt()}x",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (isSelected) GeoOnPrimary else GeoTextPrimary
                                    )
                                }
                            }
                        }
                    }

                    // Slider
                    Slider(
                        value = simulationSecond.toFloat(),
                        onValueChange = { onSeekSecond(it.toInt()) },
                        valueRange = 0f..150f,
                        steps = 29,
                        colors = SliderDefaults.colors(
                            thumbColor = GeoPrimary,
                            activeTrackColor = GeoPrimary,
                            inactiveTrackColor = GeoSurfaceContainer
                        ),
                        modifier = Modifier.fillMaxWidth().testTag("simulation_slider")
                    )

                    // Control Buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = onReset,
                            modifier = Modifier
                                .size(44.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(GeoSurfaceContainer)
                                .border(1.dp, GeoBorder, RoundedCornerShape(12.dp))
                        ) {
                            Icon(Icons.Default.Replay, contentDescription = "Ulangi Simulasi", tint = GeoTextPrimary)
                        }

                        Button(
                            onClick = onTogglePlay,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isPlaying) GeoRed else GeoPrimary,
                                contentColor = if (isPlaying) Color.White else GeoOnPrimary
                            ),
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier.size(56.dp).testTag("play_pause_sim_button"),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Icon(
                                imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                                contentDescription = if (isPlaying) "Pause" else "Play",
                                tint = if (isPlaying) Color.White else GeoOnPrimary,
                                modifier = Modifier.size(30.dp)
                            )
                        }

                        IconButton(
                            onClick = { onSeekSecond((simulationSecond + 15).coerceAtMost(150)) },
                            modifier = Modifier
                                .size(44.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(GeoSurfaceContainer)
                                .border(1.dp, GeoBorder, RoundedCornerShape(12.dp))
                        ) {
                            Icon(Icons.Default.FastForward, contentDescription = "Maju 15s", tint = GeoTextPrimary)
                        }
                    }
                }
            }
        }

        // 4. Live Action Feed at This Second
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = GeoSurface),
                shape = RoundedCornerShape(20.dp),
                border = androidx.compose.foundation.BorderStroke(1.5.dp, GeoBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "📢 AKSI YANG SEDANG TERJADI:",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Black,
                        color = GeoPrimary,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = currentStep.phase,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Black,
                        color = GeoTextPrimary
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = currentStep.activeAction,
                        fontSize = 12.sp,
                        color = GeoTextSecondary,
                        lineHeight = 18.sp
                    )

                    if (currentStep.destructionPercent == 100) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Surface(
                            color = GeoGreenContainer,
                            shape = RoundedCornerShape(10.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, GeoGreen),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.padding(10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "🏆 ", fontSize = 18.sp)
                                Text(
                                    text = "HASIL SIMULASI: 100% 3-STAR VICTORY! Strategi terbukti sangat efektif.",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = GeoGreen
                                )
                            }
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

