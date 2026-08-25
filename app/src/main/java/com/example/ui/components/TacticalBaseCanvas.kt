package com.example.ui.components

import android.graphics.Bitmap
import android.graphics.Paint as AndroidPaint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.*
import com.example.ui.theme.*
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun TacticalBaseCanvas(
    baseBitmap: Bitmap?,
    baseDrawableRes: Int,
    overlay: BaseTacticalOverlay,
    showZones: Boolean = true,
    showArrows: Boolean = true,
    showSteps: Boolean = true,
    simulationStep: AttackSimulationStep? = null,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(4f / 3f)
            .clip(RoundedCornerShape(24.dp))
            .background(GeoCanvasBg)
            .border(2.dp, GeoBorder, RoundedCornerShape(24.dp))
            .testTag("tactical_base_canvas_container"),
        contentAlignment = Alignment.Center
    ) {
        // Untouched Original Base Image
        if (baseBitmap != null) {
            Image(
                bitmap = baseBitmap.asImageBitmap(),
                contentDescription = "Clash of Clans Base Screenshot",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        } else {
            Image(
                painter = painterResource(id = baseDrawableRes),
                contentDescription = "Clash of Clans Base War Layout",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        // Tactical Overlay Drawing Layer (Circles, Arrows, Spell Rings, Step Numbers, Simulation Units)
        Canvas(modifier = Modifier.fillMaxSize().testTag("tactical_drawing_canvas")) {
            val width = size.width
            val height = size.height

            // 1. Draw Zones (Funnel, Entry, Spell Drop circles)
            if (showZones) {
                overlay.zones.forEach { zone ->
                    val centerOffset = Offset(zone.center.x * width, zone.center.y * height)
                    val radius = zone.radiusNormalized * width
                    val zoneColor = when (zone.type) {
                        MarkerType.FUNNEL_ZONE -> GeoPrimary
                        MarkerType.MAIN_ENTRY -> GeoGreen
                        MarkerType.SPELL_DROP -> GeoBlue
                        MarkerType.DEFENSE_TARGET -> GeoRed
                        MarkerType.HERO_DEPLOY -> GeoPurple
                        MarkerType.SIEGE_DEPLOY -> GeoAmber
                        MarkerType.CLEANUP_ZONE -> GeoAmberLight
                    }

                    // Outer glowing soft circle
                    drawCircle(
                        color = zoneColor.copy(alpha = 0.22f),
                        radius = radius,
                        center = centerOffset
                    )

                    // Sharp ring border
                    drawCircle(
                        color = zoneColor,
                        radius = radius,
                        center = centerOffset,
                        style = Stroke(
                            width = 5f,
                            pathEffect = if (zone.type == MarkerType.FUNNEL_ZONE) {
                                PathEffect.dashPathEffect(floatArrayOf(14f, 8f), 0f)
                            } else null
                        )
                    )

                    // Center target crosshair if Main Entry
                    if (zone.type == MarkerType.MAIN_ENTRY) {
                        drawLine(
                            color = zoneColor,
                            start = Offset(centerOffset.x - radius * 0.7f, centerOffset.y),
                            end = Offset(centerOffset.x + radius * 0.7f, centerOffset.y),
                            strokeWidth = 4f
                        )
                        drawLine(
                            color = zoneColor,
                            start = Offset(centerOffset.x, centerOffset.y - radius * 0.7f),
                            end = Offset(centerOffset.x, centerOffset.y + radius * 0.7f),
                            strokeWidth = 4f
                        )
                    }

                    // Step Number badge in center of zone
                    if (showSteps && zone.stepNumber != null) {
                        drawStepBadge(
                            center = centerOffset,
                            number = zone.stepNumber,
                            color = zoneColor
                        )
                    }
                }
            }

            // 2. Draw Tactical Pathing Arrows
            if (showArrows) {
                overlay.arrows.forEach { arrow ->
                    val start = Offset(arrow.start.x * width, arrow.start.y * height)
                    val end = Offset(arrow.end.x * width, arrow.end.y * height)
                    val color = if (arrow.isDashed) GeoPrimaryLight else GeoPrimary

                    if (arrow.controlPoint != null) {
                        // Curved quadratic path
                        val ctrl = Offset(arrow.controlPoint.x * width, arrow.controlPoint.y * height)
                        val path = Path().apply {
                            moveTo(start.x, start.y)
                            quadraticTo(ctrl.x, ctrl.y, end.x, end.y)
                        }
                        drawPath(
                            path = path,
                            color = color,
                            style = Stroke(
                                width = arrow.strokeWidth,
                                pathEffect = if (arrow.isDashed) PathEffect.dashPathEffect(floatArrayOf(16f, 10f), 0f) else null,
                                cap = StrokeCap.Round
                            )
                        )
                        // Arrowhead at endpoint using control vector
                        drawArrowHead(ctrl, end, color, arrow.strokeWidth * 2.5f)
                    } else {
                        // Straight arrow
                        drawLine(
                            color = color,
                            start = start,
                            end = end,
                            strokeWidth = arrow.strokeWidth,
                            pathEffect = if (arrow.isDashed) PathEffect.dashPathEffect(floatArrayOf(16f, 10f), 0f) else null,
                            cap = StrokeCap.Round
                        )
                        drawArrowHead(start, end, color, arrow.strokeWidth * 2.5f)
                    }
                }
            }

            // 3. Draw Active Simulation Step Effects (if in simulation mode)
            if (simulationStep != null) {
                // Spell effect rings
                simulationStep.activeSpellEffects.forEach { spell ->
                    val center = Offset(spell.center.x * width, spell.center.y * height)
                    val radius = spell.radiusNormalized * width
                    val spellColor = GeoBlue

                    drawCircle(
                        color = spellColor.copy(alpha = 0.35f),
                        radius = radius,
                        center = center
                    )
                    drawCircle(
                        color = spellColor,
                        radius = radius,
                        center = center,
                        style = Stroke(width = 6f)
                    )
                }

                // Troop markers
                simulationStep.activeTroopPositions.forEach { troop ->
                    val pos = Offset(troop.position.x * width, troop.position.y * height)
                    val troopColor = if (troop.isHero) GeoPrimary else if (troop.isSiege) GeoAmber else GeoGreen

                    drawCircle(
                        color = GeoCanvasBg.copy(alpha = 0.85f),
                        radius = 22f,
                        center = pos
                    )
                    drawCircle(
                        color = troopColor,
                        radius = 22f,
                        center = pos,
                        style = Stroke(width = 4f)
                    )
                }
            }
        }

        // Overlay Title Header Tag
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(10.dp)
                .background(GeoSurface.copy(alpha = 0.9f), RoundedCornerShape(12.dp))
                .border(1.dp, GeoBorder, RoundedCornerShape(12.dp))
                .padding(horizontal = 10.dp, vertical = 5.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "🎯 ", fontSize = 12.sp)
                Text(
                    text = overlay.title,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = GeoPrimary
                )
            }
        }
    }
}

private fun DrawScope.drawStepBadge(center: Offset, number: Int, color: Color) {
    // Dark background circle
    drawCircle(
        color = Color(0xFF1C1B1F),
        radius = 20f,
        center = center
    )
    // Colored border
    drawCircle(
        color = color,
        radius = 20f,
        center = center,
        style = Stroke(width = 3.5f)
    )

    // Draw text number via Android Canvas
    val paint = AndroidPaint().apply {
        this.color = android.graphics.Color.WHITE
        this.textSize = 26f
        this.textAlign = AndroidPaint.Align.CENTER
        this.isFakeBoldText = true
    }
    drawContext.canvas.nativeCanvas.drawText(
        number.toString(),
        center.x,
        center.y + 9f,
        paint
    )
}

private fun DrawScope.drawArrowHead(from: Offset, to: Offset, color: Color, headSize: Float) {
    val angle = atan2((to.y - from.y).toDouble(), (to.x - from.x).toDouble())
    val arrowAngle = Math.toRadians(28.0)

    val p1 = Offset(
        (to.x - headSize * cos(angle - arrowAngle)).toFloat(),
        (to.y - headSize * sin(angle - arrowAngle)).toFloat()
    )
    val p2 = Offset(
        (to.x - headSize * cos(angle + arrowAngle)).toFloat(),
        (to.y - headSize * sin(angle + arrowAngle)).toFloat()
    )

    val path = Path().apply {
        moveTo(to.x, to.y)
        lineTo(p1.x, p1.y)
        lineTo(p2.x, p2.y)
        close()
    }

    drawPath(path = path, color = color)
}

