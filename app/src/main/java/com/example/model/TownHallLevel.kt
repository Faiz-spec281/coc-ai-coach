package com.example.model

enum class TownHallLevel(
    val level: Int,
    val title: String,
    val armySpace: Int,
    val spellSpace: Int,
    val siegeAvailable: Boolean,
    val heroes: List<String>,
    val description: String
) {
    TH9(
        level = 9,
        title = "Town Hall 9",
        armySpace = 220,
        spellSpace = 9,
        siegeAvailable = false,
        heroes = listOf("Barbarian King", "Archer Queen"),
        description = "Classic TH9 Meta. King & Queen focus, Lavaloon & Witch Slap dominance."
    ),
    TH10(
        level = 10,
        title = "Town Hall 10",
        armySpace = 240,
        spellSpace = 11,
        siegeAvailable = true,
        heroes = listOf("Barbarian King", "Archer Queen"),
        description = "Single & Multi Infernos introduction. Wall Wrecker & Log Launcher CC sieges."
    ),
    TH11(
        level = 11,
        title = "Town Hall 11",
        armySpace = 260,
        spellSpace = 11,
        siegeAvailable = true,
        heroes = listOf("Barbarian King", "Archer Queen", "Grand Warden"),
        description = "Eagle Artillery era. Grand Warden Eternal Tome invulnerability timing is critical."
    ),
    TH12(
        level = 12,
        title = "Town Hall 12",
        armySpace = 280,
        spellSpace = 11,
        siegeAvailable = true,
        heroes = listOf("Barbarian King", "Archer Queen", "Grand Warden"),
        description = "Giga Tesla Town Hall weapon & Death Bomb. Blizzard, Hybrid, and Yeti Smashes."
    ),
    TH13(
        level = 13,
        title = "Town Hall 13",
        armySpace = 300,
        spellSpace = 11,
        siegeAvailable = true,
        heroes = listOf("Barbarian King", "Archer Queen", "Grand Warden", "Royal Champion"),
        description = "Scattershots and Giga Inferno with Slow Bomb. Royal Champion flanking is key."
    ),
    TH14(
        level = 14,
        title = "Town Hall 14",
        armySpace = 300,
        spellSpace = 11,
        siegeAvailable = true,
        heroes = listOf("Barbarian King", "Archer Queen", "Grand Warden", "Royal Champion"),
        description = "Giga Inferno Poison Cloud & Hero Pets. Flame Flinger & Super Bowler mastery."
    ),
    TH15(
        level = 15,
        title = "Town Hall 15",
        armySpace = 320,
        spellSpace = 11,
        siegeAvailable = true,
        heroes = listOf("Barbarian King", "Archer Queen", "Grand Warden", "Royal Champion"),
        description = "Monolith & Spell Towers (Invisibility/Poison/Rage). Super Archer Blimp & Root Riders."
    ),
    TH16(
        level = 16,
        title = "Town Hall 16",
        armySpace = 320,
        spellSpace = 11,
        siegeAvailable = true,
        heroes = listOf("Barbarian King", "Archer Queen", "Grand Warden", "Royal Champion"),
        description = "Merged Defenses & Hero Equipment. Root Rider Valkyrie & Fireball Super Witches."
    ),
    TH17(
        level = 17,
        title = "Town Hall 17",
        armySpace = 340,
        spellSpace = 12,
        siegeAvailable = true,
        heroes = listOf("Barbarian King", "Archer Queen", "Grand Warden", "Royal Champion", "Minion Prince"),
        description = "Inferno Town Hall & Ultra Merged Towers. High-precision 3-star meta executions."
    );

    companion object {
        fun fromLevel(level: Int): TownHallLevel =
            entries.firstOrNull { it.level == level } ?: TH12
    }
}

data class ArmyTroop(
    val name: String,
    val count: Int,
    val spaceEach: Int,
    val iconEmoji: String,
    val role: String // e.g. "Main Tank", "Funnel", "DPS", "Cleanup", "Hero"
)

data class ArmySpell(
    val name: String,
    val count: Int,
    val spaceEach: Int,
    val iconEmoji: String,
    val purpose: String
)

data class HeroEquipmentConfig(
    val heroName: String,
    val primaryEquipment: String,
    val secondaryEquipment: String,
    val pet: String? = null
)

data class ClanCastleComp(
    val siegeMachine: String,
    val troops: String,
    val spells: String
)

data class FullArmyComposition(
    val troops: List<ArmyTroop>,
    val spells: List<ArmySpell>,
    val clanCastle: ClanCastleComp,
    val heroEquipments: List<HeroEquipmentConfig>,
    val cocArmyLink: String // Clash of Clans in-game import URL
)
