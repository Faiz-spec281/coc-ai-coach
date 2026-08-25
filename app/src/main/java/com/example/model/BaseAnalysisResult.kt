package com.example.model

data class DetectedDefense(
    val name: String,
    val levelDetected: String,
    val positionOnMap: String, // e.g. "Core / Center", "Top Compartment", "North-East"
    val dangerLevel: String,   // "HIGH", "CRITICAL", "MEDIUM"
    val counterTactic: String  // e.g. "Trigger Grand Warden Eternal Tome when crossing this zone"
)

data class BaseWeaknessScan(
    val eagleArtilleryStatus: String,
    val infernoModes: String,        // "2x Single, 1x Multi"
    val airSweeperFacing: String,    // "Facing South-West (Attack from North-East!)"
    val clanCastleLure: String,      // "Hard to lure - use Poison on main push"
    val flingerValueZone: String,    // "6 o'clock corner has zero X-Bow/Mortar coverage -> Free value!"
    val blizzardLandingSpot: String, // "Between Town Hall and Core Multi-Infernos"
    val primaryFunnelSides: Pair<String, String>, // "9 o'clock" and "12 o'clock"
    val recommendedEntryAngle: String // "10:30 (North-West) towards Eagle Artillery"
)

data class BaseAnalysisResult(
    val detectedTownHall: TownHallLevel,
    val baseStyle: String, // "Anti-3 Star Competitive War Base", "Box Island Base", "Ring Base", "Teardrop Core"
    val threeStarDifficultyRating: String, // "Medium (High 3-Star Potential with Hybrid)"
    val weaknessScan: BaseWeaknessScan,
    val keyDefenses: List<DetectedDefense>,
    val bestStrategyId: String,
    val secondaryStrategyId: String,
    val customProTips: List<String>
)

data class VideoAnalysisResult(
    val videoTitle: String,
    val detectedStrategy: String,
    val attackDuration: String,
    val armyUsed: String,
    val keyTechniqueLearned: String,
    val funnelCadenceTiming: String,
    val spellEfficiencyRating: String,
    val howToApplyToOtherBases: List<String>,
    val mistakeToAvoid: String
)
