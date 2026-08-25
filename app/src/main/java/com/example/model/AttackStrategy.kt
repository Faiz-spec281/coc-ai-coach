package com.example.model

data class AttackStepTiming(
    val stepNumber: Int,
    val timeFormatted: String, // e.g. "00:00 - 00:20"
    val phaseName: String,     // "Phase 1: Funnel & Setup", "Phase 2: Siege & Main Entry", etc.
    val actionTitle: String,   // "Drop Baby Dragon & Sneaky Goblins to cut 3 o'clock trash"
    val detailedInstructions: String, // "Let the Baby Dragon clear outer Elixir Collector and Army Camp so your Queen cannot wander south."
    val unitsToDeploy: List<String>,  // ["1x Baby Dragon", "2x Sneaky Gobs", "1x Cocoon"]
    val spellsToUse: List<String> = emptyList(), // ["1x Poison on defending Clan Castle"]
    val heroAbilities: List<String> = emptyList(), // ["Save Warden ability!"]
    val tacticalTip: String,
    val targetZoneId: String? = null // Links to zone in TacticalOverlay
)

data class AttackStrategy(
    val id: String,
    val name: String,
    val thLevel: TownHallLevel,
    val category: String, // "Air Smash", "Ground Hybrid", "Queen Charge", "Hero Smash", "Root Rider Meta"
    val difficulty: String, // "Beginner Friendly", "Intermediate", "Pro Meta"
    val threeStarPotential: String = "100% Guaranteed 3-Star (Bintang 3)",
    val winRatePercent: Int = 98,
    val summary: String,
    val whyItWorks: String,
    val army: FullArmyComposition,
    val tacticalOverlay: BaseTacticalOverlay,
    val steps: List<AttackStepTiming>,
    val simulationSteps: List<AttackSimulationStep>,
    val tags: List<String> = listOf("3-Star Meta", "War Attack", "CWL Proven")
)

data class AttackSimulationStep(
    val second: Int, // 0..180 (3 min battle clock)
    val timeRemaining: String, // "2:55", "2:30", "1:45", etc.
    val phase: String,
    val activeAction: String,
    val destructionPercent: Int, // 0..100%
    val starsEarned: Int,        // 1, 2, or 3
    val townHallDestroyed: Boolean,
    val activeTroopPositions: List<SimulatedTroopUnit>,
    val activeSpellEffects: List<SimulatedSpellEffect>,
    val announcement: String
)

data class SimulatedTroopUnit(
    val name: String,
    val emoji: String,
    val position: PointF,
    val count: Int,
    val isHero: Boolean = false,
    val isSiege: Boolean = false
)

data class SimulatedSpellEffect(
    val spellName: String,
    val center: PointF,
    val radiusNormalized: Float,
    val colorHex: Long,
    val pulseProgress: Float = 0.5f
)
