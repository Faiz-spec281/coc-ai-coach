package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.TownHallLevel
import com.example.ui.theme.*

@Composable
fun TownHallSelector(
    selectedTh: TownHallLevel,
    onSelectTh: (TownHallLevel) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(GeoSurface)
            .border(width = 1.dp, color = GeoBorder, shape = RoundedCornerShape(0.dp))
            .padding(vertical = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 2.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "PILIH LEVEL TOWN HALL",
                fontSize = 11.sp,
                fontWeight = FontWeight.Black,
                color = GeoPrimary,
                letterSpacing = 1.2.sp
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(GeoGreen)
                )
                Text(
                    text = "B3 Meta Active",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = GeoTextMuted
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 14.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("town_hall_selector_row")
        ) {
            items(TownHallLevel.entries) { th ->
                val isSelected = th == selectedTh
                TownHallChip(
                    th = th,
                    isSelected = isSelected,
                    onClick = { onSelectTh(th) }
                )
            }
        }
    }
}

@Composable
private fun TownHallChip(
    th: TownHallLevel,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val bgColor = if (isSelected) GeoPrimary else GeoSurfaceContainer
    val borderColor = if (isSelected) GeoPrimaryLight else GeoBorder
    val textColor = if (isSelected) GeoOnPrimary else GeoTextPrimary
    val subtextColor = if (isSelected) GeoOnPrimary.copy(alpha = 0.8f) else GeoTextMuted

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(14.dp))
            .background(bgColor)
            .border(
                width = if (isSelected) 1.5.dp else 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(14.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 7.dp)
            .testTag("th_chip_${th.level}"),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = "🏰",
                fontSize = 15.sp
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "TH ${th.level}",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Black,
                    color = textColor
                )
                Text(
                    text = if (th.siegeAvailable) "Siege CC" else "No Siege",
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Medium,
                    color = subtextColor
                )
            }
        }
    }
}

