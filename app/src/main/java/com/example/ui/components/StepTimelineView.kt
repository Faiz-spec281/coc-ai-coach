package com.example.ui.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
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
import com.example.model.AttackStepTiming
import com.example.model.AttackStrategy
import com.example.ui.theme.*

@Composable
fun StepTimelineView(
    strategy: AttackStrategy,
    allStrategies: List<AttackStrategy>,
    onSelectStrategy: (AttackStrategy) -> Unit,
    onStartSimulation: () -> Unit,
    onCopyArmyLink: (Context, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(GeoDarkBg)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. Multi-Strategy Switcher Row
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "📚 PILIHAN STRATEGI 3-STAR (TH ${strategy.thLevel.level})",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Black,
                    color = GeoPrimary,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(allStrategies) { strat ->
                        val isSelected = strat.id == strategy.id
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(if (isSelected) GeoPrimary else GeoSurfaceContainer)
                                .border(
                                    width = if (isSelected) 1.5.dp else 1.dp,
                                    color = if (isSelected) GeoPrimaryLight else GeoBorder,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .clickable { onSelectStrategy(strat) }
                                .padding(horizontal = 14.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = strat.name,
                                fontSize = 12.sp,
                                fontWeight = if (isSelected) FontWeight.Black else FontWeight.Bold,
                                color = if (isSelected) GeoOnPrimary else GeoTextPrimary
                            )
                        }
                    }
                }
            }
        }

        // 2. Active Strategy Hero Header Card (Geometric Sheet Style)
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = GeoSurface),
                shape = RoundedCornerShape(24.dp),
                border = androidx.compose.foundation.BorderStroke(1.5.dp, GeoBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    // Small sheet handle indicator
                    Box(
                        modifier = Modifier
                            .width(48.dp)
                            .height(5.dp)
                            .clip(RoundedCornerShape(3.dp))
                            .background(GeoBorder)
                            .align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = strategy.name,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Black,
                                color = GeoPrimaryLight
                            )
                            Text(
                                text = "${strategy.category} • ${strategy.difficulty}",
                                fontSize = 12.sp,
                                color = GeoTextMuted
                            )
                        }
                        // 100% 3-Star Badge
                        Surface(
                            color = GeoGreenContainer,
                            shape = RoundedCornerShape(8.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, GeoGreen)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    Icons.Default.Star,
                                    contentDescription = null,
                                    tint = GeoGreen,
                                    modifier = Modifier.size(15.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "100% B3",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Black,
                                    color = GeoGreen
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = strategy.summary,
                        fontSize = 13.sp,
                        color = GeoTextSecondary,
                        lineHeight = 18.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                    // Why it works highlight with geometric left border
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(GeoSurfaceContainer)
                            .border(1.dp, GeoBorderSubtle, RoundedCornerShape(12.dp))
                            .padding(12.dp)
                    ) {
                        Row(verticalAlignment = Alignment.Top) {
                            Box(
                                modifier = Modifier
                                    .width(4.dp)
                                    .height(36.dp)
                                    .clip(RoundedCornerShape(2.dp))
                                    .background(GeoPrimary)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "💡 KUNCI SUKSES 3 BINTANG:",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Black,
                                    color = GeoPrimary
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = strategy.whyItWorks,
                                    fontSize = 12.sp,
                                    color = GeoTextPrimary
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    // Action Buttons (Copy Army Link & Test Simulation)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            onClick = { onCopyArmyLink(context, strategy.army.cocArmyLink) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = GeoSurfaceContainer,
                                contentColor = GeoTextPrimary
                            ),
                            border = androidx.compose.foundation.BorderStroke(1.dp, GeoBorder),
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier.weight(1f).testTag("copy_army_button")
                        ) {
                            Icon(Icons.Default.ContentCopy, contentDescription = null, tint = GeoPrimary, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Salin Army CoC", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }

                        Button(
                            onClick = onStartSimulation,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = GeoPrimary,
                                contentColor = GeoOnPrimary
                            ),
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier.weight(1f).testTag("start_sim_button")
                        ) {
                            Icon(Icons.Default.PlayArrow, contentDescription = null, tint = GeoOnPrimary, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Simulasi Serang", fontSize = 11.sp, fontWeight = FontWeight.Black)
                        }
                    }
                }
            }
        }

        // 3. Army Composition Roster Preview
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = GeoSurface),
                shape = RoundedCornerShape(20.dp),
                border = androidx.compose.foundation.BorderStroke(1.5.dp, GeoBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "⚔️ KOMPOSISI PASUKAN & SPELL (TH ${strategy.thLevel.level})",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Black,
                        color = GeoPrimary,
                        letterSpacing = 0.5.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    // Troops Row
                    Text(text = "Pasukan:", fontSize = 11.sp, color = GeoTextMuted, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        items(strategy.army.troops) { troop ->
                            Surface(
                                color = GeoSurfaceContainer,
                                shape = RoundedCornerShape(10.dp),
                                border = androidx.compose.foundation.BorderStroke(1.dp, GeoBorderSubtle)
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(text = troop.iconEmoji, fontSize = 13.sp)
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "${troop.count}x ${troop.name}",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = GeoTextPrimary
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    // Spells Row
                    Text(text = "Spell:", fontSize = 11.sp, color = GeoTextMuted, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        items(strategy.army.spells) { spell ->
                            Surface(
                                color = GeoSurfaceContainer,
                                shape = RoundedCornerShape(10.dp),
                                border = androidx.compose.foundation.BorderStroke(1.dp, GeoPurpleContainer)
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(text = spell.iconEmoji, fontSize = 13.sp)
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "${spell.count}x ${spell.name}",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = GeoPurple
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    // Clan Castle & Siege
                    Surface(
                        color = GeoSurfaceContainer,
                        shape = RoundedCornerShape(12.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, GeoAmberContainer),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(
                                text = "🏰 Clan Castle & Siege: ${strategy.army.clanCastle.siegeMachine}",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = GeoAmber
                            )
                            Text(
                                text = "Isi CC: ${strategy.army.clanCastle.troops} | Spell: ${strategy.army.clanCastle.spells}",
                                fontSize = 11.sp,
                                color = GeoTextSecondary
                            )
                        }
                    }
                }
            }
        }

        // 4. STEP-BY-STEP DETAILED ATTACK TIMELINE CARDS
        item {
            Text(
                text = "⏱️ PANDUAN JADWAL WAKTU PENGELUARAN PASUKAN & SPELL",
                fontSize = 12.sp,
                fontWeight = FontWeight.Black,
                color = GeoPrimary,
                letterSpacing = 1.sp
            )
        }

        items(strategy.steps) { step ->
            StepTimingCard(step = step)
        }

        item {
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun StepTimingCard(step: AttackStepTiming) {
    Card(
        colors = CardDefaults.cardColors(containerColor = GeoSurface),
        shape = RoundedCornerShape(20.dp),
        border = androidx.compose.foundation.BorderStroke(1.5.dp, GeoBorder),
        modifier = Modifier.fillMaxWidth().testTag("step_card_${step.stepNumber}")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header Row: Step Number & Time Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(GeoPrimary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${step.stepNumber}",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Black,
                            color = GeoOnPrimary
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = step.phaseName,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Black,
                        color = GeoTextPrimary
                    )
                }

                // Time Clock Pill
                Surface(
                    color = GeoSurfaceContainer,
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, GeoBorder)
                ) {
                    Text(
                        text = "⏱️ ${step.timeFormatted}",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Black,
                        color = GeoPrimaryLight,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            // Action Title
            Text(
                text = step.actionTitle,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = GeoPrimary,
                lineHeight = 19.sp
            )

            Spacer(modifier = Modifier.height(6.dp))
            // Detailed Instructions
            Text(
                text = step.detailedInstructions,
                fontSize = 12.sp,
                color = GeoTextSecondary,
                lineHeight = 18.sp
            )

            // Units To Deploy Chips
            if (step.unitsToDeploy.isNotEmpty()) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Pasukan yang diturunkan sekarang:",
                    fontSize = 11.sp,
                    color = GeoTextMuted,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    items(step.unitsToDeploy) { unit ->
                        Surface(
                            color = GeoSurfaceContainer,
                            shape = RoundedCornerShape(8.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, GeoBlueContainer)
                        ) {
                            Text(
                                text = unit,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = GeoBlue,
                                modifier = Modifier.padding(horizontal = 7.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
            }

            // Spells to Use Chips
            if (step.spellsToUse.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "🧪 Spell yang harus dijatuhkan:",
                    fontSize = 11.sp,
                    color = GeoPurple,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    items(step.spellsToUse) { spell ->
                        Surface(
                            color = GeoSurfaceContainer,
                            shape = RoundedCornerShape(8.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, GeoPurpleContainer)
                        ) {
                            Text(
                                text = spell,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = GeoPurple,
                                modifier = Modifier.padding(horizontal = 7.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
            }

            // Hero Abilities to Trigger
            if (step.heroAbilities.isNotEmpty()) {
                Spacer(modifier = Modifier.height(10.dp))
                Surface(
                    color = GeoSurfaceContainer,
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, GeoPurpleContainer),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(10.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Box(
                            modifier = Modifier
                                .width(4.dp)
                                .height(28.dp)
                                .clip(RoundedCornerShape(2.dp))
                                .background(GeoPurple)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "👑 AKTIVASI ABILITY HERO:",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Black,
                                color = GeoPurple
                            )
                            step.heroAbilities.forEach { ability ->
                                Text(text = "• $ability", fontSize = 11.sp, color = GeoTextPrimary, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }

            // Tactical Pro Tip
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(GeoSurfaceContainer)
                    .border(1.dp, GeoBorderSubtle, RoundedCornerShape(12.dp))
                    .padding(10.dp)
            ) {
                Row(verticalAlignment = Alignment.Top) {
                    Box(
                        modifier = Modifier
                            .width(4.dp)
                            .height(28.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(GeoAmber)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = "🎯 Catatan Taktis:",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = GeoAmber
                        )
                        Text(
                            text = step.tacticalTip,
                            fontSize = 11.sp,
                            color = GeoTextSecondary
                        )
                    }
                }
            }
        }
    }
}

