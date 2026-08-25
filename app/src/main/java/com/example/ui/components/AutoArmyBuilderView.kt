package com.example.ui.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.ContentCopy
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
import com.example.model.FullArmyComposition
import com.example.model.TownHallLevel
import com.example.ui.theme.*

@Composable
fun AutoArmyBuilderView(
    selectedTh: TownHallLevel,
    selectedCoreTroop: String,
    autoArmy: FullArmyComposition,
    onSelectCoreTroop: (String) -> Unit,
    onCopyArmyLink: (Context, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val popularTroops = listOf(
        "Root Rider" to "🪵",
        "Super Archer" to "🏹",
        "Miner" to "⛏️",
        "Electro Dragon" to "⚡",
        "Witch" to "🧙‍♀️",
        "Super Bowler" to "🎳",
        "Dragon" to "🐉",
        "Electro Titan" to "⚡"
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(GeoDarkBg)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Header
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = GeoSurface),
                shape = RoundedCornerShape(20.dp),
                border = androidx.compose.foundation.BorderStroke(1.5.dp, GeoBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = GeoPrimary, modifier = Modifier.size(22.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "AUTO ARMY GENERATOR META 3-STAR",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Black,
                            color = GeoPrimary,
                            letterSpacing = 0.5.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Pilih pasukan favorit kamu di bawah, AI akan otomatis menghitung komposisi pasukan, spell, siege machine, dan hero equipment yang PALING KUAT untuk TH ${selectedTh.level}!",
                        fontSize = 12.sp,
                        color = GeoTextSecondary,
                        lineHeight = 17.sp
                    )
                }
            }
        }

        // Troop Selector Row
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "1. PILIH PASUKAN INTI FAVORIT:",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Black,
                    color = GeoPrimary,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth().testTag("troop_preference_selector")
                ) {
                    items(popularTroops) { (troopName, emoji) ->
                        val isSelected = selectedCoreTroop == troopName
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(if (isSelected) GeoPrimary else GeoSurfaceContainer)
                                .border(
                                    width = if (isSelected) 1.5.dp else 1.dp,
                                    color = if (isSelected) GeoPrimaryLight else GeoBorder,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .clickable { onSelectCoreTroop(troopName) }
                                .padding(horizontal = 14.dp, vertical = 8.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = emoji, fontSize = 15.sp)
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = troopName,
                                    fontSize = 12.sp,
                                    fontWeight = if (isSelected) FontWeight.Black else FontWeight.Bold,
                                    color = if (isSelected) GeoOnPrimary else GeoTextPrimary
                                )
                            }
                        }
                    }
                }
            }
        }

        // Generated Army Card
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = GeoSurface),
                shape = RoundedCornerShape(24.dp),
                border = androidx.compose.foundation.BorderStroke(1.5.dp, GeoBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Meta $selectedCoreTroop Smash 3-Star",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Black,
                                color = GeoPrimaryLight
                            )
                            Text(
                                text = "Kapasitas Army Camp: ${selectedTh.armySpace} Space",
                                fontSize = 11.sp,
                                color = GeoTextMuted
                            )
                        }

                        Button(
                            onClick = { onCopyArmyLink(context, autoArmy.cocArmyLink) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = GeoPrimary,
                                contentColor = GeoOnPrimary
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.testTag("copy_auto_army_button")
                        ) {
                            Icon(Icons.Default.ContentCopy, contentDescription = null, tint = GeoOnPrimary, modifier = Modifier.size(15.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Salin Army", fontSize = 11.sp, fontWeight = FontWeight.Black)
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Troops List
                    Text(text = "Daftar Pasukan (Army Camp):", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = GeoTextMuted)
                    Spacer(modifier = Modifier.height(6.dp))
                    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                        autoArmy.troops.forEach { troop ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(GeoSurfaceContainer)
                                    .border(1.dp, GeoBorderSubtle, RoundedCornerShape(10.dp))
                                    .padding(horizontal = 12.dp, vertical = 7.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(text = troop.iconEmoji, fontSize = 14.sp)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(text = troop.name, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = GeoTextPrimary)
                                }
                                Text(
                                    text = "${troop.count}x (${troop.spaceEach * troop.count} sp)",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Black,
                                    color = GeoPrimary
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Spells List
                    Text(text = "Daftar Spell (Spell Factory):", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = GeoPurple)
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        autoArmy.spells.forEach { spell ->
                            Surface(
                                color = GeoSurfaceContainer,
                                shape = RoundedCornerShape(10.dp),
                                border = androidx.compose.foundation.BorderStroke(1.dp, GeoPurpleContainer),
                                modifier = Modifier.weight(1f)
                            ) {
                                Column(
                                    modifier = Modifier.padding(8.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(text = spell.iconEmoji, fontSize = 16.sp)
                                    Text(text = "${spell.count}x ${spell.name}", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = GeoTextPrimary)
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Clan Castle & Siege
                    Surface(
                        color = GeoSurfaceContainer,
                        shape = RoundedCornerShape(12.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, GeoAmberContainer),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "🏰 REKOMENDASI CLAN CASTLE (CC):",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Black,
                                color = GeoAmber
                            )
                            Spacer(modifier = Modifier.height(3.dp))
                            Text(
                                text = "Siege: ${autoArmy.clanCastle.siegeMachine}",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = GeoTextPrimary
                            )
                            Text(
                                text = "Pasukan CC: ${autoArmy.clanCastle.troops}",
                                fontSize = 11.sp,
                                color = GeoTextSecondary
                            )
                            Text(
                                text = "Spell CC: ${autoArmy.clanCastle.spells}",
                                fontSize = 11.sp,
                                color = GeoTextSecondary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Hero Equipment Recommendation
                    if (autoArmy.heroEquipments.isNotEmpty()) {
                        Surface(
                            color = GeoSurfaceContainer,
                            shape = RoundedCornerShape(12.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, GeoGreenContainer),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    text = "👑 REKOMENDASI HERO EQUIPMENT (BINTANG 3):",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Black,
                                    color = GeoGreen
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                autoArmy.heroEquipments.forEach { eq ->
                                    Text(
                                        text = "• ${eq.heroName}: ${eq.primaryEquipment} + ${eq.secondaryEquipment}",
                                        fontSize = 11.sp,
                                        color = GeoTextPrimary,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
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

