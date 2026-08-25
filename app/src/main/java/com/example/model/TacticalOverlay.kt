package com.example.model

data class PointF(
    val x: Float, // Normalized 0.0 to 1.0 (relative to base map width)
    val y: Float  // Normalized 0.0 to 1.0 (relative to base map height)
)

enum class MarkerType {
    FUNNEL_ZONE,       // Circle marker for funnel cuts
    MAIN_ENTRY,        // Target crosshair / big circle for primary army entry
    SPELL_DROP,        // Circular area showing where spell needs to be dropped (Rage, Heal, Freeze, etc.)
    SIEGE_DEPLOY,      // Siege launcher spawn point
    HERO_DEPLOY,       // King/Queen/Warden/RC spawn point
    DEFENSE_TARGET,    // Eagle Artillery / Town Hall / Monolith / Scattershot priority target
    CLEANUP_ZONE       // Outer zone for cleanup troops
}

data class TacticalZone(
    val id: String,
    val center: PointF,
    val radiusNormalized: Float, // e.g. 0.08f
    val type: MarkerType,
    val label: String,           // e.g. "Funnel 1 (Baby Drag)", "Rage + Eternal Tome Zone"
    val colorHex: Long,          // ARGB color for glowing circle/border
    val stepNumber: Int? = null  // Chronological step number 1..5
)

data class TacticalArrow(
    val id: String,
    val start: PointF,
    val controlPoint: PointF? = null, // For curved tactical pathing
    val end: PointF,
    val label: String,               // e.g. "Main Push (Hybrid)", "Queen Walk Path", "Siege Trajectory"
    val colorHex: Long,
    val strokeWidth: Float = 6f,
    val isDashed: Boolean = false
)

data class BaseTacticalOverlay(
    val title: String,
    val summary: String,
    val zones: List<TacticalZone>,
    val arrows: List<TacticalArrow>,
    val entryAngleDegree: Float = 45f // e.g. 45 deg (Top-Right / 2 o'clock)
)
