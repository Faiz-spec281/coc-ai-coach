package com.example.data

import com.example.model.*

object MetaStrategyRepository {

    fun getStrategiesForTH(th: TownHallLevel): List<AttackStrategy> {
        return allStrategies.filter { it.thLevel == th }
    }

    fun getStrategyById(id: String): AttackStrategy {
        return allStrategies.firstOrNull { it.id == id } ?: allStrategies.first()
    }

    fun generateAutoArmy(th: TownHallLevel, coreTroopPreference: String): FullArmyComposition {
        return when (th) {
            TownHallLevel.TH9 -> FullArmyComposition(
                troops = listOf(
                    ArmyTroop("Witch", 10, 12, "🧙‍♀️", "Main Swarm DPS"),
                    ArmyTroop("Golem", 3, 30, "🗿", "Main Tank"),
                    ArmyTroop("Wizard", 4, 4, "🧙‍♂️", "Funnel & Cleanup"),
                    ArmyTroop("Wall Breaker", 4, 2, "💣", "Entry Breaker"),
                    ArmyTroop("Archer", 4, 1, "🏹", "Corner Cleanup")
                ),
                spells = listOf(
                    ArmySpell("Lightning Spell", 8, 1, "⚡", "Zap Air Defenses / Inferno"),
                    ArmySpell("Earthquake Spell", 2, 1, "🌋", "Finish Core & Open Walls"),
                    ArmySpell("Poison Spell", 1, 1, "🧪", "Enemy Clan Castle")
                ),
                clanCastle = ClanCastleComp("None (TH9)", "5x Bowlers + 1x Giant", "1x Freeze Spell"),
                heroEquipments = listOf(
                    HeroEquipmentConfig("Barbarian King", "Giant Gauntlet", "Rage Vial"),
                    HeroEquipmentConfig("Archer Queen", "Frozen Arrow", "Invisibility Vial")
                ),
                cocArmyLink = "https://link.clashofclans.com/en?action=CopyArmy&army=u10x15-3x13-4x6-4x4-4x0s8x0-2x9-1x1"
            )
            TownHallLevel.TH10 -> FullArmyComposition(
                troops = listOf(
                    ArmyTroop("Witch", 12, 12, "🧙‍♀️", "Main Swarm DPS"),
                    ArmyTroop("Golem", 2, 30, "🗿", "Main Tank"),
                    ArmyTroop("Bowler", 4, 6, "🎳", "Bouncing DPS"),
                    ArmyTroop("Wall Breaker", 4, 2, "💣", "Wall Breakers"),
                    ArmyTroop("Wizard", 2, 4, "🧙‍♂️", "Funnel")
                ),
                spells = listOf(
                    ArmySpell("Lightning Spell", 8, 1, "⚡", "Zap 2x Single Infernos"),
                    ArmySpell("Earthquake Spell", 2, 1, "🌋", "Open Core Compartments"),
                    ArmySpell("Rage Spell", 1, 2, "🔥", "Core Hero / Bowler Boost")
                ),
                clanCastle = ClanCastleComp("Log Launcher / Wall Wrecker", "5x Bowlers + 1x Giant", "1x Freeze + 1x Poison"),
                heroEquipments = listOf(
                    HeroEquipmentConfig("Barbarian King", "Giant Gauntlet", "Rage Vial"),
                    HeroEquipmentConfig("Archer Queen", "Frozen Arrow", "Invisibility Vial")
                ),
                cocArmyLink = "https://link.clashofclans.com/en?action=CopyArmy&army=u12x15-2x13-4x22-4x4-2x6s8x0-2x9-1x2"
            )
            TownHallLevel.TH11 -> FullArmyComposition(
                troops = listOf(
                    ArmyTroop("Miner", 16, 6, "⛏️", "Hybrid Core Surge"),
                    ArmyTroop("Hog Rider", 12, 5, "🐗", "Defense Crusher"),
                    ArmyTroop("Healer", 5, 14, "🧚‍♀️", "Queen Charge"),
                    ArmyTroop("Baby Dragon", 2, 10, "🐉", "Funnel Cuts"),
                    ArmyTroop("Super Wall Breaker", 2, 8, "🧨", "Queen Compartment Entry"),
                    ArmyTroop("Balloon", 2, 5, "🎈", "Seeking Air Mine Cocoon")
                ),
                spells = listOf(
                    ArmySpell("Heal Spell", 3, 2, "💚", "Keep Hybrid Alive in Core"),
                    ArmySpell("Rage Spell", 2, 2, "🔥", "Queen Charge Burst"),
                    ArmySpell("Poison Spell", 1, 1, "🧪", "Enemy CC Kill")
                ),
                clanCastle = ClanCastleComp("Siege Barracks", "Hog Riders + Valkyrie", "1x Heal + 1x Freeze"),
                heroEquipments = listOf(
                    HeroEquipmentConfig("Barbarian King", "Giant Gauntlet", "Rage Vial"),
                    HeroEquipmentConfig("Archer Queen", "Frozen Arrow", "Invisibility Vial"),
                    HeroEquipmentConfig("Grand Warden", "Eternal Tome", "Healing Tome")
                ),
                cocArmyLink = "https://link.clashofclans.com/en?action=CopyArmy&army=u16x24-12x11-5x7-2x23-2x28-2x5s3x1-2x2-1x9"
            )
            TownHallLevel.TH12 -> FullArmyComposition(
                troops = listOf(
                    ArmyTroop("Miner", 18, 6, "⛏️", "Underground Core Push"),
                    ArmyTroop("Hog Rider", 14, 5, "🐗", "Defense Targeting Flank"),
                    ArmyTroop("Healer", 5, 14, "🧚‍♀️", "Queen Charge Sustain"),
                    ArmyTroop("Baby Dragon", 2, 10, "🐉", "Quick Funnel Cut"),
                    ArmyTroop("Super Wall Breaker", 2, 8, "🧨", "Queen Penetration"),
                    ArmyTroop("Headhunter", 2, 6, "🎯", "Enemy Hero Sniper"),
                    ArmyTroop("Wizard", 2, 4, "🧙‍♂️", "Cleanup")
                ),
                spells = listOf(
                    ArmySpell("Heal Spell", 3, 2, "💚", "Through Bomb Towers & Giga Tesla"),
                    ArmySpell("Rage Spell", 2, 2, "🔥", "Queen Charge & Hybrid Eagle Peak"),
                    ArmySpell("Freeze Spell", 2, 1, "❄️", "Single Inferno / Giga Tesla Freeze"),
                    ArmySpell("Poison Spell", 1, 1, "🧪", "Defending CC")
                ),
                clanCastle = ClanCastleComp("Siege Barracks or Flame Flinger", "Hog Riders / Yetis", "1x Rage + 1x Freeze"),
                heroEquipments = listOf(
                    HeroEquipmentConfig("Barbarian King", "Giant Gauntlet", "Rage Vial"),
                    HeroEquipmentConfig("Archer Queen", "Frozen Arrow", "Invisibility Vial"),
                    HeroEquipmentConfig("Grand Warden", "Eternal Tome", "Healing Tome")
                ),
                cocArmyLink = "https://link.clashofclans.com/en?action=CopyArmy&army=u18x24-14x11-5x7-2x23-2x28-2x53-2x6s3x1-2x2-2x5-1x9"
            )
            TownHallLevel.TH13 -> FullArmyComposition(
                troops = listOf(
                    ArmyTroop("Dragon", 8, 20, "🐲", "Main Aerial Destroyer"),
                    ArmyTroop("Dragon Rider", 4, 25, "🚀", "Target Defenses"),
                    ArmyTroop("Balloon", 8, 5, "🎈", "Trap Test & Sniping"),
                    ArmyTroop("Super Wizard", 4, 10, "⚡", "Blizzard Bomb CC"),
                    ArmyTroop("Baby Dragon", 1, 10, "🐉", "Funnel"),
                    ArmyTroop("Archer", 4, 1, "🏹", "Cleanup")
                ),
                spells = listOf(
                    ArmySpell("Invisibility Spell", 5, 1, "👻", "Blizzard Wizard Protection"),
                    ArmySpell("Freeze Spell", 3, 1, "❄️", "Scattershots & Single Infernos"),
                    ArmySpell("Rage Spell", 1, 2, "🔥", "Blizzard Core Destruction"),
                    ArmySpell("Poison Spell", 1, 1, "🧪", "Enemy CC Troops")
                ),
                clanCastle = ClanCastleComp("Battle Blimp", "4x Super Wizards + 1x Super Goblin", "1x Rage + 1x Freeze"),
                heroEquipments = listOf(
                    HeroEquipmentConfig("Barbarian King", "Giant Gauntlet", "Spiky Ball"),
                    HeroEquipmentConfig("Archer Queen", "Frozen Arrow", "Invisibility Vial"),
                    HeroEquipmentConfig("Grand Warden", "Eternal Tome", "Healing Tome"),
                    HeroEquipmentConfig("Royal Champion", "Haste Vial", "Seeking Shield")
                ),
                cocArmyLink = "https://link.clashofclans.com/en?action=CopyArmy&army=u8x8-4x65-8x5-1x23-4x0s5x35-3x5-1x2-1x9"
            )
            TownHallLevel.TH14 -> FullArmyComposition(
                troops = listOf(
                    ArmyTroop("Super Bowler", 4, 30, "🟣", "Massive Core Bouncing DPS"),
                    ArmyTroop("Healer", 5, 14, "🧚‍♀️", "Warden Walk & Smash Sustain"),
                    ArmyTroop("Ice Golem", 2, 15, "🧊", "Entry Tank & Freeze"),
                    ArmyTroop("Yeti", 2, 18, "🦣", "Funnel & Compartment Break"),
                    ArmyTroop("Super Wall Breaker", 3, 8, "🧨", "Triple Wall Opening"),
                    ArmyTroop("Headhunter", 2, 6, "🎯", "Hero Take Down"),
                    ArmyTroop("Balloon", 2, 5, "🎈", "Mine Cocoon")
                ),
                spells = listOf(
                    ArmySpell("Rage Spell", 3, 2, "🔥", "Power Super Bowlers through Core"),
                    ArmySpell("Jump Spell", 1, 2, "🪜", "Connect Center Compartments"),
                    ArmySpell("Freeze Spell", 2, 1, "❄️", "Poison Town Hall / Monolith"),
                    ArmySpell("Poison Spell", 1, 1, "🧪", "Enemy CC")
                ),
                clanCastle = ClanCastleComp("Flame Flinger", "2x Yetis + 1x Valkyrie", "1x Rage + 1x Poison"),
                heroEquipments = listOf(
                    HeroEquipmentConfig("Barbarian King", "Giant Gauntlet", "Rage Vial", "Phoenix"),
                    HeroEquipmentConfig("Archer Queen", "Frozen Arrow", "Invisibility Vial", "Unicorn"),
                    HeroEquipmentConfig("Grand Warden", "Eternal Tome", "Healing Tome", "Electro Owl"),
                    HeroEquipmentConfig("Royal Champion", "Haste Vial", "Seeking Shield", "Diggy")
                ),
                cocArmyLink = "https://link.clashofclans.com/en?action=CopyArmy&army=u4x80-5x7-2x58-2x53-3x28-2x53-2x5s3x2-1x3-2x5-1x9"
            )
            TownHallLevel.TH15 -> FullArmyComposition(
                troops = listOf(
                    ArmyTroop("Root Rider", 6, 20, "🪵", "Wall Breaker & Front Tank"),
                    ArmyTroop("Valkyrie", 8, 8, "🪓", "Rage Shredder"),
                    ArmyTroop("Titan", 1, 32, "⚡", "Anti-Skeleton & CC Aura"),
                    ArmyTroop("Super Barbarian", 4, 5, "⚔️", "Funnel"),
                    ArmyTroop("Headhunter", 3, 6, "🎯", "Enemy Hero Eliminator"),
                    ArmyTroop("Minion", 4, 2, "🦇", "Cleanup"),
                    ArmyTroop("Druid / Healer", 2, 16, "🌿", "Healing Surge")
                ),
                spells = listOf(
                    ArmySpell("Overgrowth Spell", 2, 2, "🌳", "Freeze Monolith / Scattershot cluster"),
                    ArmySpell("Rage Spell", 2, 2, "🔥", "Main Push Speed & Damage"),
                    ArmySpell("Freeze Spell", 2, 1, "❄️", "Town Hall Giga Poison"),
                    ArmySpell("Poison Spell", 1, 1, "🧪", "Enemy Clan Castle")
                ),
                clanCastle = ClanCastleComp("Battle Drill / Siege Barracks", "2x Root Riders + Super Barb", "1x Rage + 1x Freeze"),
                heroEquipments = listOf(
                    HeroEquipmentConfig("Barbarian King", "Giant Gauntlet", "Spiky Ball", "Phoenix"),
                    HeroEquipmentConfig("Archer Queen", "Frozen Arrow", "Invisibility Vial", "Unicorn"),
                    HeroEquipmentConfig("Grand Warden", "Eternal Tome", "Healing Tome", "Electro Owl"),
                    HeroEquipmentConfig("Royal Champion", "Haste Vial", "Rocket Spear", "Diggy")
                ),
                cocArmyLink = "https://link.clashofclans.com/en?action=CopyArmy&army=u6x110-8x12-1x95-4x26-3x53-4x10-2x115s2x70-2x2-2x5-1x9"
            )
            TownHallLevel.TH16 -> FullArmyComposition(
                troops = listOf(
                    ArmyTroop("Root Rider", 7, 20, "🪵", "Core Wall Smashing"),
                    ArmyTroop("Valkyrie", 10, 8, "🪓", "High Burst Cleave"),
                    ArmyTroop("Super Wall Breaker", 1, 8, "🧨", "Outer Guide"),
                    ArmyTroop("Headhunter", 3, 6, "🎯", "Hero Sniping"),
                    ArmyTroop("Druid", 3, 16, "🌿", "Bear Tank + HoT Regen"),
                    ArmyTroop("Minion", 4, 2, "🦇", "Cleanup"),
                    ArmyTroop("Apprentice Warden", 1, 20, "🪄", "HP Aura Boost")
                ),
                spells = listOf(
                    ArmySpell("Overgrowth Spell", 2, 2, "🌳", "Lockdown Half the Base"),
                    ArmySpell("Rage Spell", 2, 2, "🔥", "Root Rider Valk Surge"),
                    ArmySpell("Freeze Spell", 2, 1, "❄️", "Merged Defenses / TH Giga"),
                    ArmySpell("Poison Spell", 1, 1, "🧪", "Defending Heroes / CC")
                ),
                clanCastle = ClanCastleComp("Siege Barracks", "Root Rider + Super Valks", "1x Rage + 1x Freeze"),
                heroEquipments = listOf(
                    HeroEquipmentConfig("Barbarian King", "Giant Gauntlet", "Spiky Ball", "Phoenix"),
                    HeroEquipmentConfig("Archer Queen", "Frozen Arrow", "Healer Puppet", "Unicorn"),
                    HeroEquipmentConfig("Grand Warden", "Eternal Tome", "Healing Tome", "Electro Owl"),
                    HeroEquipmentConfig("Royal Champion", "Haste Vial", "Rocket Spear", "Fox")
                ),
                cocArmyLink = "https://link.clashofclans.com/en?action=CopyArmy&army=u7x110-10x12-1x28-3x53-3x115-4x10-1x97s2x70-2x2-2x5-1x9"
            )
            TownHallLevel.TH17 -> FullArmyComposition(
                troops = listOf(
                    ArmyTroop("Root Rider", 8, 20, "🪵", "Ultra High HP Breaker"),
                    ArmyTroop("Super Witch", 2, 44, "🔮", "Big Boy Summoner DPS"),
                    ArmyTroop("Druid", 3, 16, "🌿", "Rejuvenating Field"),
                    ArmyTroop("Electro Titan", 1, 32, "⚡", "Aura Damage Core"),
                    ArmyTroop("Headhunter", 4, 6, "🎯", "Hero Assasination"),
                    ArmyTroop("Minion Prince / Clean", 4, 2, "👑", "Fast Corner Sweep")
                ),
                spells = listOf(
                    ArmySpell("Overgrowth Spell", 2, 2, "🌳", "Overgrowth Inferno Tower Complex"),
                    ArmySpell("Rage Spell", 3, 2, "🔥", "Maximum Damage Multiplier"),
                    ArmySpell("Freeze Spell", 2, 1, "❄️", "Freeze Giga Inferno TH17"),
                    ArmySpell("Poison Spell", 1, 1, "🧪", "Enemy Super Troops")
                ),
                clanCastle = ClanCastleComp("Battle Drill / Siege Barracks", "2x Root Riders + Super Minion", "1x Rage + 1x Overgrowth"),
                heroEquipments = listOf(
                    HeroEquipmentConfig("Barbarian King", "Giant Gauntlet", "Spiky Ball", "Phoenix"),
                    HeroEquipmentConfig("Archer Queen", "Frozen Arrow", "Giant Arrow", "Unicorn"),
                    HeroEquipmentConfig("Grand Warden", "Fireball", "Healing Tome", "Electro Owl"),
                    HeroEquipmentConfig("Royal Champion", "Haste Vial", "Rocket Spear", "Fox")
                ),
                cocArmyLink = "https://link.clashofclans.com/en?action=CopyArmy&army=u8x110-2x83-3x115-1x95-4x53-4x10s2x70-3x2-2x5-1x9"
            )
        }
    }

    private val allStrategies: List<AttackStrategy> by lazy {
        listOf(
            // ==================== TOWN HALL 12 ====================
            AttackStrategy(
                id = "th12_qc_hybrid",
                name = "Queen Charge Hybrid (Miner + Hog)",
                thLevel = TownHallLevel.TH12,
                category = "Ground Hybrid",
                difficulty = "Intermediate (High Consistency)",
                threeStarPotential = "100% Guaranteed 3-Star Meta",
                winRatePercent = 99,
                summary = "The absolute #1 most reliable 3-star strategy at TH12. Queen Charge eliminates Eagle Artillery & CC, followed by Miner-Hog wave supported by Grand Warden Eternal Tome.",
                whyItWorks = "Queen Charge creates an unpathable L-shaped corridor for the Miners and Hogs so they stick together and never disperse. Warden invulnerability cancels Town Hall Giga Tesla explosion.",
                army = FullArmyComposition(
                    troops = listOf(
                        ArmyTroop("Miner", 18, 6, "⛏️", "Core Sinking DPS"),
                        ArmyTroop("Hog Rider", 14, 5, "🐗", "Defense Targeter"),
                        ArmyTroop("Healer", 5, 14, "🧚‍♀️", "Queen Charge Sustain"),
                        ArmyTroop("Baby Dragon", 2, 10, "🐉", "Funnel Cutters"),
                        ArmyTroop("Super Wall Breaker", 2, 8, "🧨", "Queen Compartment Breach"),
                        ArmyTroop("Headhunter", 2, 6, "🎯", "Enemy Hero Killer"),
                        ArmyTroop("Wizard", 2, 4, "🧙‍♂️", "Perimeter Cleanup")
                    ),
                    spells = listOf(
                        ArmySpell("Heal Spell", 3, 2, "💚", "Heal through Giant Bomb Clusters"),
                        ArmySpell("Rage Spell", 2, 2, "🔥", "Boost Queen Charge & Core Push"),
                        ArmySpell("Freeze Spell", 2, 1, "❄️", "Freeze Single Inferno & Giga Tesla"),
                        ArmySpell("Poison Spell", 1, 1, "🧪", "Kill CC Electro Dragon / Super Minions")
                    ),
                    clanCastle = ClanCastleComp("Siege Barracks", "7x Hog Riders + 1x Valkyrie", "1x Rage Spell + 1x Freeze Spell"),
                    heroEquipments = listOf(
                        HeroEquipmentConfig("Barbarian King", "Giant Gauntlet", "Rage Vial"),
                        HeroEquipmentConfig("Archer Queen", "Frozen Arrow", "Invisibility Vial"),
                        HeroEquipmentConfig("Grand Warden", "Eternal Tome", "Healing Tome")
                    ),
                    cocArmyLink = "https://link.clashofclans.com/en?action=CopyArmy&army=u18x24-14x11-5x7-2x23-2x28-2x53-2x6s3x1-2x2-2x5-1x9"
                ),
                tacticalOverlay = BaseTacticalOverlay(
                    title = "TH12 Queen Charge Hybrid Blueprint",
                    summary = "Start Queen Charge at 12 o'clock towards Eagle Artillery. Deploy Siege Barracks & King at 3 o'clock to create a narrow corridor for Miners & Hogs.",
                    zones = listOf(
                        TacticalZone("funnel_1", PointF(0.25f, 0.15f), 0.07f, MarkerType.FUNNEL_ZONE, "1. Funnel Cut (Baby Drag)", 0xFF00E676, 1),
                        TacticalZone("qc_entry", PointF(0.48f, 0.12f), 0.08f, MarkerType.HERO_DEPLOY, "2. Queen + 5 Healers Drop", 0xFF00E5FF, 2),
                        TacticalZone("rage_qc", PointF(0.50f, 0.28f), 0.09f, MarkerType.SPELL_DROP, "3. Rage on Queen vs CC/Eagle", 0xFFD500F9, 3),
                        TacticalZone("siege_king", PointF(0.85f, 0.40f), 0.08f, MarkerType.SIEGE_DEPLOY, "4. Siege Barracks + King Cut", 0xFFFF9100, 4),
                        TacticalZone("hybrid_entry", PointF(0.68f, 0.22f), 0.09f, MarkerType.MAIN_ENTRY, "5. Main Miner + Hog Wave", 0xFFFF3D00, 5),
                        TacticalZone("warden_tome", PointF(0.50f, 0.50f), 0.10f, MarkerType.SPELL_DROP, "6. Warden Tome (Giga Tesla)", 0xFFFFD700, 6),
                        TacticalZone("heal_core", PointF(0.40f, 0.65f), 0.09f, MarkerType.SPELL_DROP, "7. Heal Spell on Bomb Tower", 0xFF00E676, 7)
                    ),
                    arrows = listOf(
                        TacticalArrow("arrow_qc", PointF(0.48f, 0.12f), PointF(0.48f, 0.30f), PointF(0.45f, 0.45f), "Queen Walk to Eagle & CC", 0xFF00E5FF, 7f),
                        TacticalArrow("arrow_king", PointF(0.85f, 0.40f), null, PointF(0.80f, 0.75f), "King + P.E.K.K.A Outer Funnel", 0xFFFF9100, 6f),
                        TacticalArrow("arrow_hybrid", PointF(0.68f, 0.22f), PointF(0.58f, 0.45f), PointF(0.35f, 0.78f), "Main Hybrid Core Path", 0xFFFF3D00, 8f)
                    ),
                    entryAngleDegree = 45f
                ),
                steps = listOf(
                    AttackStepTiming(
                        stepNumber = 1,
                        timeFormatted = "00:00 - 00:20 (Start)",
                        phaseName = "Phase 1: Funnel & Queen Charge",
                        actionTitle = "Keluarkan Baby Dragon di 11 o'clock & Queen + 5 Healer di 12 o'clock",
                        detailedInstructions = "Drop 1 Baby Dragon di sisi kiri untuk bersihkan trash building. Lalu turunkan Archer Queen + 5 Healer di belakangnya. Gunakan 1 Super Wall Breaker agar Queen masuk ke kompartemen Eagle Artillery.",
                        unitsToDeploy = listOf("1x Baby Dragon", "Archer Queen", "5x Healers", "1x Super Wall Breaker"),
                        spellsToUse = listOf("Siapkan 1x Rage Spell"),
                        tacticalTip = "Pastikan Healer tidak terkena Air Defense terluar!"
                    ),
                    AttackStepTiming(
                        stepNumber = 2,
                        timeFormatted = "00:25 - 00:45",
                        phaseName = "Phase 2: Lure CC & Queen Rage",
                        actionTitle = "Drop Rage Spell pada Queen & Poison pada Pasukan CC Lawan",
                        detailedInstructions = "Saat Clan Castle musuh keluar (misal E-Drag atau Super Minion), segera letakkan Poison Spell tepat di atas pasukan CC. Jatuhkan 1 Rage Spell di bawah Archer Queen agar damage & heal meningkat pesat.",
                        unitsToDeploy = listOf("1x Headhunter (jika ada Hero lawan)"),
                        spellsToUse = listOf("1x Rage Spell", "1x Poison Spell"),
                        tacticalTip = "Jangan gunakan ability Queen di sini! Biarkan Rage + Healer menjaga HP Queen tetap penuh."
                    ),
                    AttackStepTiming(
                        stepNumber = 3,
                        timeFormatted = "00:45 - 01:05",
                        phaseName = "Phase 3: Siege Barracks & King Funnel",
                        actionTitle = "Turunkan Siege Barracks & Barbarian King di 3 o'clock",
                        detailedInstructions = "Letakkan Siege Barracks di sudut 3 o'clock agar P.E.K.K.A dan Wizard membersihkan sisi kanan base. Turunkan Barbarian King bersamaan untuk mengunci funnel koridor tengah.",
                        unitsToDeploy = listOf("Siege Barracks", "Barbarian King"),
                        spellsToUse = emptyList(),
                        tacticalTip = "Ini membuat Miners dan Hogs TIDAK BISA belok keluar dan wajib masuk lurus ke jantung base."
                    ),
                    AttackStepTiming(
                        stepNumber = 4,
                        timeFormatted = "01:05 - 01:35 (MAIN SURGE)",
                        phaseName = "Phase 4: Hybrid Release & Warden Ability",
                        actionTitle = "Spam 18x Miner + 14x Hog Rider + Grand Warden di antara Queen & King",
                        detailedInstructions = "Turunkan semua Miner terlebih dahulu, disusul Hog Riders dan Grand Warden. Saat pasukan masuk ke area Giga Tesla Town Hall atau terkena Eagle Artillery, AKTIFKAN Grand Warden Eternal Tome + Heal Spell!",
                        unitsToDeploy = listOf("18x Miner", "14x Hog Rider", "Grand Warden"),
                        spellsToUse = listOf("1x Heal Spell", "1x Freeze pada Single Inferno / Giga Tesla"),
                        heroAbilities = listOf("Grand Warden Eternal Tome (WAJIB di detik 01:15-01:25)"),
                        tacticalTip = "Eternal Tome membuat semua Miner & Hog kebal total dari ledakan maut Giga Tesla TH12!"
                    ),
                    AttackStepTiming(
                        stepNumber = 5,
                        timeFormatted = "01:35 - 02:45",
                        phaseName = "Phase 5: Core Heal & 100% Cleanup",
                        actionTitle = "Gunakan sisa 2x Heal Spell di area Bomb Tower + Turunkan Wizard Cleanup",
                        detailedInstructions = "Perhatikan jalur Hog Riders di kompartemen belakang. Berikan Heal Spell di atas Wizard Tower atau Bomb Tower. Turunkan 2x Wizard dan Minion di pojok-pojok bangunan luar untuk 100% 3 Bintang sempurna!",
                        unitsToDeploy = listOf("2x Wizard", "Hogs dari Siege Barracks yang pecah"),
                        spellsToUse = listOf("2x Heal Spell", "1x Freeze"),
                        tacticalTip = "100% 3-Star Terjamin! Waktu tersisa sekitar 40 detik."
                    )
                ),
                simulationSteps = listOf(
                    AttackSimulationStep(0, "3:00", "Start", "Drop Baby Dragon & Queen", 0, 0, false, listOf(
                        SimulatedTroopUnit("Baby Dragon", "🐉", PointF(0.25f, 0.15f), 1),
                        SimulatedTroopUnit("Archer Queen", "👸", PointF(0.48f, 0.12f), 1, isHero = true),
                        SimulatedTroopUnit("Healers", "🧚‍♀️", PointF(0.50f, 0.08f), 5)
                    ), emptyList(), "Phase 1: Funneling dimulai di sudut 12 o'clock!"),
                    AttackSimulationStep(25, "2:35", "QC Push", "Rage Queen & Poison CC", 14, 0, false, listOf(
                        SimulatedTroopUnit("Archer Queen", "👸", PointF(0.48f, 0.28f), 1, isHero = true),
                        SimulatedTroopUnit("Healers", "🧚‍♀️", PointF(0.50f, 0.20f), 5)
                    ), listOf(
                        SimulatedSpellEffect("Rage", PointF(0.48f, 0.28f), 0.09f, 0x88D500F9),
                        SimulatedSpellEffect("Poison", PointF(0.45f, 0.32f), 0.07f, 0x889C27B0)
                    ), "Phase 2: Eagle Artillery & Clan Castle musuh hancur!"),
                    AttackSimulationStep(50, "2:10", "Funnel Cut", "Siege Barracks & King", 28, 0, false, listOf(
                        SimulatedTroopUnit("King", "🤴", PointF(0.85f, 0.45f), 1, isHero = true),
                        SimulatedTroopUnit("Siege Barracks", "🏰", PointF(0.85f, 0.40f), 1, isSiege = true)
                    ), emptyList(), "Phase 3: Jalur sisi kanan terkunci rapi oleh King!"),
                    AttackSimulationStep(75, "1:45", "Main Wave", "Hybrid + Grand Warden Eternal Tome", 58, 1, true, listOf(
                        SimulatedTroopUnit("Miners", "⛏️", PointF(0.55f, 0.48f), 18),
                        SimulatedTroopUnit("Hogs", "🐗", PointF(0.58f, 0.50f), 14),
                        SimulatedTroopUnit("Warden", "🧙‍♂️", PointF(0.60f, 0.42f), 1, isHero = true)
                    ), listOf(
                        SimulatedSpellEffect("Eternal Tome Gold Aura", PointF(0.52f, 0.50f), 0.12f, 0x88FFD700),
                        SimulatedSpellEffect("Heal", PointF(0.50f, 0.52f), 0.09f, 0x8800E676)
                    ), "Phase 4: Town Hall 12 Giga Tesla HANCUR! Bintang 1 & 2 tercapai!"),
                    AttackSimulationStep(115, "1:05", "Backend Sweep", "Heal on Bomb Tower & CC Hogs", 85, 2, true, listOf(
                        SimulatedTroopUnit("Hogs", "🐗", PointF(0.38f, 0.68f), 10),
                        SimulatedTroopUnit("Miners", "⛏️", PointF(0.40f, 0.72f), 12)
                    ), listOf(
                        SimulatedSpellEffect("Heal", PointF(0.38f, 0.68f), 0.09f, 0x8800E676)
                    ), "Phase 5: Semua defense musuh rata! Pasukan cleanup menyapu sisa bangunan."),
                    AttackSimulationStep(150, "0:30", "VICTORY", "100% 3-Star Destruction!", 100, 3, true, listOf(
                        SimulatedTroopUnit("Queen", "👸", PointF(0.30f, 0.85f), 1, isHero = true),
                        SimulatedTroopUnit("Miners", "⛏️", PointF(0.20f, 0.80f), 8)
                    ), emptyList(), "⭐️⭐️⭐️ VICTORY! 100% 3 Bintang Sempurna tercapai!")
                )
            ),

            // ==================== TH12 BLIZZARD YETI ====================
            AttackStrategy(
                id = "th12_blizzard_yeti",
                name = "Blizzard Super Wizard + Yeti Smash",
                thLevel = TownHallLevel.TH12,
                category = "Smash Attack",
                difficulty = "Advanced (High Destruction)",
                threeStarPotential = "100% Guaranteed 3-Star Meta",
                winRatePercent = 97,
                summary = "Battle Blimp containing Super Wizards + Invisibility spells drops in the core to wipe Town Hall, Eagle Artillery, and multi-infernos in 8 seconds.",
                whyItWorks = "Blizzard removes 35% of the base's highest DPS defenses before the main Yeti army is even dropped, turning the rest of the attack into an easy cleanup.",
                army = FullArmyComposition(
                    troops = listOf(
                        ArmyTroop("Yeti", 6, 18, "🦣", "Heavy Core Tank & Yetimites"),
                        ArmyTroop("Super Archer / Wizard", 6, 12, "🏹", "Ranged Smash DPS"),
                        ArmyTroop("Healer", 4, 14, "🧚‍♀️", "Warden Walk Sustain"),
                        ArmyTroop("Super Wall Breaker", 3, 8, "🧨", "Core Gate Openers"),
                        ArmyTroop("Baby Dragon", 2, 10, "🐉", "Flank Cut"),
                        ArmyTroop("Balloon", 3, 5, "🎈", "Blimp Mine Hunter")
                    ),
                    spells = listOf(
                        ArmySpell("Invisibility Spell", 5, 1, "👻", "Chain Invisibility for Super Wizards"),
                        ArmySpell("Rage Spell", 2, 2, "🔥", "Blizzard Core Burst & Yeti Rage"),
                        ArmySpell("Freeze Spell", 2, 1, "❄️", "Sweep & Single Inferno control")
                    ),
                    clanCastle = ClanCastleComp("Battle Blimp", "4x Super Wizards + 1x Super Goblin", "1x Rage + 1x Freeze"),
                    heroEquipments = listOf(
                        HeroEquipmentConfig("Barbarian King", "Giant Gauntlet", "Rage Vial"),
                        HeroEquipmentConfig("Archer Queen", "Frozen Arrow", "Invisibility Vial"),
                        HeroEquipmentConfig("Grand Warden", "Eternal Tome", "Healing Tome")
                    ),
                    cocArmyLink = "https://link.clashofclans.com/en?action=CopyArmy&army=u6x53-6x80-4x7-3x28-2x23-3x5s5x35-2x2-2x5"
                ),
                tacticalOverlay = BaseTacticalOverlay(
                    title = "TH12 Blizzard Yeti Smash Blueprint",
                    summary = "Launch Battle Blimp behind Lava Hound / Balloons into core. Chain Invisibility spells every 4 seconds. Send Yeti Smash down the remaining flank.",
                    zones = listOf(
                        TacticalZone("blimp_start", PointF(0.15f, 0.15f), 0.08f, MarkerType.SIEGE_DEPLOY, "1. Blimp Launch (Behind Loon)", 0xFFFF9100, 1),
                        TacticalZone("blizzard_drop", PointF(0.50f, 0.45f), 0.10f, MarkerType.SPELL_DROP, "2. Blizzard Landing (Rage + Invis x5)", 0xFFD500F9, 2),
                        TacticalZone("yeti_funnel_l", PointF(0.20f, 0.65f), 0.08f, MarkerType.FUNNEL_ZONE, "3. Left Funnel (King)", 0xFF00E676, 3),
                        TacticalZone("yeti_funnel_r", PointF(0.75f, 0.80f), 0.08f, MarkerType.FUNNEL_ZONE, "4. Right Funnel (Baby Dragon)", 0xFF00E676, 4),
                        TacticalZone("yeti_main_entry", PointF(0.48f, 0.85f), 0.09f, MarkerType.MAIN_ENTRY, "5. Main Yeti + Super Bowler Surge", 0xFFFF3D00, 5)
                    ),
                    arrows = listOf(
                        TacticalArrow("arrow_blimp", PointF(0.15f, 0.15f), null, PointF(0.50f, 0.45f), "Blimp Flight into Core", 0xFFFF9100, 8f, isDashed = true),
                        TacticalArrow("arrow_yeti", PointF(0.48f, 0.85f), null, PointF(0.50f, 0.55f), "Yeti Smash Push", 0xFFFF3D00, 8f)
                    ),
                    entryAngleDegree = 135f
                ),
                steps = listOf(
                    AttackStepTiming(
                        stepNumber = 1,
                        timeFormatted = "00:00 - 00:25",
                        phaseName = "Phase 1: Blizzard Bomb Drop",
                        actionTitle = "Keluarkan 2 Balloon + Battle Blimp mengarah ke Core Town Hall / Eagle",
                        detailedInstructions = "Kirim 2 Balloon sebagai tameng ranjau udara, langsung ikuti dengan Battle Blimp. Saat Blimp mendekati core, buka Blimp, letakkan 1 RAGE SPELL + 1 INVISIBILITY SPELL langsung!",
                        unitsToDeploy = listOf("2x Balloon", "Battle Blimp (isi Super Wizard)"),
                        spellsToUse = listOf("1x Rage Spell", "1x Invisibility Spell"),
                        tacticalTip = "PENTING: Jangan sampai Invisibility mengenai Town Hall atau pertahanan musuh!"
                    ),
                    AttackStepTiming(
                        stepNumber = 2,
                        timeFormatted = "00:25 - 00:45",
                        phaseName = "Phase 2: Invisibility Chaining",
                        actionTitle = "Jatuhkan Invisibility Spell setiap 4.2 detik (Total 5x Invis)",
                        detailedInstructions = "Perhatikan lingkaran kuning Super Wizard. Sebelum durasi Invisibility habis (tiap 4 detik), jatuhkan Invisibility berikutnya tepat di posisi Super Wizard. Core musuh akan rata total!",
                        unitsToDeploy = emptyList(),
                        spellsToUse = listOf("4x Invisibility Spell sisa"),
                        tacticalTip = "Dalam 15 detik, Town Hall, 2 Inferno, dan CC musuh lenyap seketika."
                    ),
                    AttackStepTiming(
                        stepNumber = 3,
                        timeFormatted = "00:45 - 01:15",
                        phaseName = "Phase 3: Side Funneling",
                        actionTitle = "Turunkan King & Baby Dragon di kedua sisi sayap",
                        detailedInstructions = "Gunakan Barbarian King di sisi kiri dan Baby Dragon di sisi kanan untuk memotong sisa bangunan luar agar Yeti tidak menyebar.",
                        unitsToDeploy = listOf("Barbarian King", "2x Baby Dragon"),
                        spellsToUse = emptyList(),
                        tacticalTip = "Funnel yang rapi menjamin 100% kemenangan."
                    ),
                    AttackStepTiming(
                        stepNumber = 4,
                        timeFormatted = "01:15 - 02:30",
                        phaseName = "Phase 4: Yeti Surge & 3-Star Cleanup",
                        actionTitle = "Turunkan 6x Yeti + Archer Queen + Grand Warden + Rage Spell",
                        detailedInstructions = "Lepaskan semua Yeti di jalur yang sudah bersih. Gunakan Super Wall Breaker untuk membuka dinding luar. Saat Yeti bertemu sisa pertahanan berat, berikan Rage Spell dan aktifkan Warden Eternal Tome!",
                        unitsToDeploy = listOf("6x Yeti", "6x Super Archer / Wizard", "Archer Queen", "Grand Warden"),
                        spellsToUse = listOf("1x Rage Spell", "2x Freeze Spell"),
                        heroAbilities = listOf("Grand Warden Eternal Tome", "Barbarian King Giant Gauntlet"),
                        tacticalTip = "⭐️⭐️⭐️ 3-Star dipastikan selesai dalam waktu kurang dari 2 menit 10 detik!"
                    )
                ),
                simulationSteps = listOf(
                    AttackSimulationStep(0, "3:00", "Start", "Blimp Launch towards Core", 0, 0, false, listOf(
                        SimulatedTroopUnit("Balloons", "🎈", PointF(0.18f, 0.18f), 2),
                        SimulatedTroopUnit("Battle Blimp", "🎈", PointF(0.15f, 0.15f), 1, isSiege = true)
                    ), emptyList(), "Phase 1: Battle Blimp meluncur menuju Core base!"),
                    AttackSimulationStep(15, "2:45", "Blizzard Explosion", "Super Wizards in Rage + Invis", 25, 1, true, listOf(
                        SimulatedTroopUnit("Super Wizards", "⚡", PointF(0.50f, 0.45f), 4)
                    ), listOf(
                        SimulatedSpellEffect("Rage", PointF(0.50f, 0.45f), 0.09f, 0x88D500F9),
                        SimulatedSpellEffect("Invisibility", PointF(0.50f, 0.45f), 0.08f, 0x8800E5FF)
                    ), "Phase 2: Core Town Hall 12 & Eagle Artillery RATA DALAM 10 DETIK!"),
                    AttackSimulationStep(45, "2:15", "Funnel", "King & Yeti deploy", 45, 1, true, listOf(
                        SimulatedTroopUnit("King", "🤴", PointF(0.20f, 0.65f), 1, isHero = true),
                        SimulatedTroopUnit("Yetis", "🦣", PointF(0.48f, 0.85f), 6)
                    ), emptyList(), "Phase 3: Pasukan Yeti menyapu sisa pertahanan dari bawah."),
                    AttackSimulationStep(90, "1:30", "Smash", "Rage & Warden Tome", 82, 2, true, listOf(
                        SimulatedTroopUnit("Yetis", "🦣", PointF(0.48f, 0.60f), 5),
                        SimulatedTroopUnit("Queen", "👸", PointF(0.50f, 0.70f), 1, isHero = true)
                    ), listOf(
                        SimulatedSpellEffect("Rage", PointF(0.48f, 0.60f), 0.09f, 0x88D500F9)
                    ), "Phase 4: Sisa Inferno membeku, Yetimites menghabisi seluruh pertahanan!"),
                    AttackSimulationStep(140, "0:40", "VICTORY", "100% 3 Bintang Terjamin!", 100, 3, true, listOf(
                        SimulatedTroopUnit("Yetis", "🦣", PointF(0.60f, 0.30f), 3)
                    ), emptyList(), "⭐️⭐️⭐️ VICTORY! 3-Star Sempurna!")
                )
            ),

            // ==================== TOWN HALL 14 ====================
            AttackStrategy(
                id = "th14_hydra_blizzard",
                name = "Blizzard Hydra (Dragon + Dragon Rider)",
                thLevel = TownHallLevel.TH14,
                category = "Air Smash",
                difficulty = "Intermediate",
                threeStarPotential = "100% Guaranteed 3-Star Meta",
                winRatePercent = 98,
                summary = "Unstoppable TH14 Air Attack. Blizzard takes Town Hall poison bomb and Scattershots, followed by Dragons + Dragon Riders with Warden Healing Tome.",
                whyItWorks = "Eliminating the TH14 Giga Inferno poison cloud with the Blimp protects the entire main dragon army from ever touching poison, guaranteeing high HP survival.",
                army = FullArmyComposition(
                    troops = listOf(
                        ArmyTroop("Dragon", 7, 20, "🐲", "Main Aerial HP Pool"),
                        ArmyTroop("Dragon Rider", 5, 25, "🚀", "Defense Targeting Core Breaker"),
                        ArmyTroop("Balloon", 9, 5, "🎈", "Trap Seeker & Air Defense Sniping"),
                        ArmyTroop("Baby Dragon", 2, 10, "🐉", "Funnel Outer Trash"),
                        ArmyTroop("Headhunter", 2, 6, "🎯", "Hero Assassins"),
                        ArmyTroop("Minion", 4, 2, "🦇", "Cleanup")
                    ),
                    spells = listOf(
                        ArmySpell("Invisibility Spell", 5, 1, "👻", "Blizzard Bomb Cover"),
                        ArmySpell("Freeze Spell", 3, 1, "❄️", "Single Infernos & Sweepers"),
                        ArmySpell("Rage Spell", 1, 2, "🔥", "Blizzard & Main Dragon Boost"),
                        ArmySpell("Poison Spell", 1, 1, "🧪", "Enemy Clan Castle")
                    ),
                    clanCastle = ClanCastleComp("Battle Blimp", "4x Super Wizards + 1x Super Goblin", "1x Rage + 1x Freeze"),
                    heroEquipments = listOf(
                        HeroEquipmentConfig("Barbarian King", "Giant Gauntlet", "Rage Vial", "Phoenix"),
                        HeroEquipmentConfig("Archer Queen", "Frozen Arrow", "Invisibility Vial", "Unicorn"),
                        HeroEquipmentConfig("Grand Warden", "Eternal Tome", "Healing Tome", "Electro Owl"),
                        HeroEquipmentConfig("Royal Champion", "Haste Vial", "Seeking Shield", "Diggy")
                    ),
                    cocArmyLink = "https://link.clashofclans.com/en?action=CopyArmy&army=u7x8-5x65-9x5-2x23-2x53-4x10s5x35-3x5-1x2-1x9"
                ),
                tacticalOverlay = BaseTacticalOverlay(
                    title = "TH14 Blizzard Hydra Attack Blueprint",
                    summary = "Blizzard directly into TH14 Giga Inferno compartment. King & Queen create hero dive on the right. Dragons & Dragon Riders charge down the middle with Grand Warden.",
                    zones = listOf(
                        TacticalZone("blimp_th14", PointF(0.50f, 0.40f), 0.10f, MarkerType.SPELL_DROP, "1. Blizzard TH14 Kill (Rage + 5x Invis)", 0xFFD500F9, 1),
                        TacticalZone("hero_dive", PointF(0.85f, 0.60f), 0.09f, MarkerType.HERO_DEPLOY, "2. King + Queen Hero Dive", 0xFFFF9100, 2),
                        TacticalZone("hydra_spread", PointF(0.20f, 0.75f), 0.12f, MarkerType.MAIN_ENTRY, "3. Hydra (Dragons + Riders) Line", 0xFFFF3D00, 3),
                        TacticalZone("warden_heal", PointF(0.40f, 0.55f), 0.10f, MarkerType.SPELL_DROP, "4. Warden Eternal + Healing Tome", 0xFFFFD700, 4),
                        TacticalZone("rc_flank", PointF(0.15f, 0.35f), 0.08f, MarkerType.HERO_DEPLOY, "5. Royal Champion Backend Flank", 0xFF00E5FF, 5)
                    ),
                    arrows = listOf(
                        TacticalArrow("hydra_push", PointF(0.20f, 0.75f), PointF(0.40f, 0.50f), PointF(0.60f, 0.25f), "Hydra Main Push Trajectory", 0xFFFF3D00, 8f),
                        TacticalArrow("hero_push", PointF(0.85f, 0.60f), null, PointF(0.70f, 0.30f), "King & Queen Flank Clear", 0xFFFF9100, 6f),
                        TacticalArrow("rc_push", PointF(0.15f, 0.35f), null, PointF(0.35f, 0.15f), "Royal Champion Cleanup", 0xFF00E5FF, 6f)
                    ),
                    entryAngleDegree = 225f
                ),
                steps = listOf(
                    AttackStepTiming(
                        stepNumber = 1,
                        timeFormatted = "00:00 - 00:25",
                        phaseName = "Phase 1: Blizzard on Town Hall 14",
                        actionTitle = "Kirim Lava Hound / Loons + Battle Blimp ke Town Hall 14",
                        detailedInstructions = "Jatuhkan Blimp tepat di atas Town Hall 14. Buka Blimp dan langsung lempar Rage + Invisibility Spell. Lanjutkan Invisibility tiap 4 detik sampai TH14 dan Scattershot di sekitarnya hancur tanpa menyentuh pasukan utama.",
                        unitsToDeploy = listOf("2x Balloon", "Battle Blimp"),
                        spellsToUse = listOf("1x Rage Spell", "5x Invisibility Spell"),
                        tacticalTip = "Racun TH14 akan hilang sebelum Dragon kita datang!"
                    ),
                    AttackStepTiming(
                        stepNumber = 2,
                        timeFormatted = "00:30 - 00:55",
                        phaseName = "Phase 2: Hero Dive Funnel",
                        actionTitle = "Turunkan Barbarian King & Archer Queen di sisi kanan (3 o'clock)",
                        detailedInstructions = "Biarkan King & Queen membersihkan 1 sisi luar base (Eagle atau Scattershot samping). Gunakan Giant Gauntlet King saat mendekati hero pertahanan musuh.",
                        unitsToDeploy = listOf("Barbarian King", "Archer Queen"),
                        spellsToUse = listOf("1x Poison Spell pada CC musuh"),
                        tacticalTip = "Hero Dive memastikan naga tetap berada di tengah jalur."
                    ),
                    AttackStepTiming(
                        stepNumber = 3,
                        timeFormatted = "00:55 - 01:30",
                        phaseName = "Phase 3: Hydra Main Army Surge",
                        actionTitle = "Sebar 7x Dragon + 5x Dragon Rider + Grand Warden berjejer dari 7 o'clock",
                        detailedInstructions = "Gelar Balloon terdepan, lalu barisan Dragon dan Dragon Rider di belakangnya bersama Grand Warden. Begitu barisan naga mendekati Single Inferno / Scattershot, AKTIFKAN Grand Warden Eternal Tome + Healing Tome!",
                        unitsToDeploy = listOf("7x Dragon", "5x Dragon Rider", "7x Balloon", "Grand Warden"),
                        spellsToUse = listOf("2x Freeze Spell pada Air Sweeper / Single Inferno"),
                        heroAbilities = listOf("Grand Warden Eternal Tome & Healing Tome"),
                        tacticalTip = "Healing Tome mengembalikan HP seluruh Dragon Rider menjadi 100%!"
                    ),
                    AttackStepTiming(
                        stepNumber = 4,
                        timeFormatted = "01:30 - 02:40",
                        phaseName = "Phase 4: Royal Champion & 3-Star Finish",
                        actionTitle = "Turunkan Royal Champion di sisi berlawanan untuk membersihkan defense belakang",
                        detailedInstructions = "Lepaskan Royal Champion bersama Diggy di sudut atas untuk menghabisi sisa Cannon dan Archer Tower. Gunakan Haste Vial + Seeking Shield untuk burst damage.",
                        unitsToDeploy = listOf("Royal Champion", "4x Minions"),
                        spellsToUse = listOf("1x Freeze Spell"),
                        heroAbilities = listOf("Royal Champion Haste Vial"),
                        tacticalTip = "⭐️⭐️⭐️ Kemenangan 100% 3 Bintang selesai dengan sisa waktu melimpah!"
                    )
                ),
                simulationSteps = listOf(
                    AttackSimulationStep(0, "3:00", "Start", "Blimp Blizzard into TH14", 0, 0, false, listOf(
                        SimulatedTroopUnit("Battle Blimp", "🎈", PointF(0.50f, 0.40f), 1, isSiege = true)
                    ), emptyList(), "Phase 1: Blimp Blizzard mendarat di kompartemen Town Hall 14!"),
                    AttackSimulationStep(20, "2:40", "TH Destroyed", "Super Wizards eradicate core", 28, 1, true, listOf(
                        SimulatedTroopUnit("Super Wizards", "⚡", PointF(0.50f, 0.40f), 4)
                    ), listOf(
                        SimulatedSpellEffect("Invis", PointF(0.50f, 0.40f), 0.08f, 0x8800E5FF)
                    ), "Phase 2: Town Hall 14 & Giga Poison meledak tanpa merugikan naga!"),
                    AttackSimulationStep(50, "2:10", "Hero Dive", "King & Queen clear right flank", 42, 1, true, listOf(
                        SimulatedTroopUnit("King", "🤴", PointF(0.85f, 0.60f), 1, isHero = true),
                        SimulatedTroopUnit("Queen", "👸", PointF(0.82f, 0.65f), 1, isHero = true)
                    ), emptyList(), "Phase 3: King & Queen mengunci satu sisi base."),
                    AttackSimulationStep(80, "1:40", "Hydra Surge", "Dragons + Dragon Riders in Warden Tome", 70, 2, true, listOf(
                        SimulatedTroopUnit("Dragons", "🐲", PointF(0.35f, 0.55f), 6),
                        SimulatedTroopUnit("Dragon Riders", "🚀", PointF(0.38f, 0.50f), 4),
                        SimulatedTroopUnit("Grand Warden", "🧙‍♂️", PointF(0.32f, 0.60f), 1, isHero = true)
                    ), listOf(
                        SimulatedSpellEffect("Eternal Tome", PointF(0.35f, 0.55f), 0.12f, 0x88FFD700)
                    ), "Phase 4: Hydra menghancurkan pertahanan udara dengan invulnerability!"),
                    AttackSimulationStep(130, "0:50", "VICTORY", "Royal Champion clears remaining defenses", 100, 3, true, listOf(
                        SimulatedTroopUnit("Royal Champion", "🛡️", PointF(0.45f, 0.20f), 1, isHero = true)
                    ), emptyList(), "⭐️⭐️⭐️ VICTORY! 3-Star 100% Hancur!")
                )
            ),

            // ==================== TOWN HALL 16 ====================
            AttackStrategy(
                id = "th16_root_rider_valk",
                name = "Root Rider Valkyrie + Overgrowth Meta",
                thLevel = TownHallLevel.TH16,
                category = "Root Rider Meta",
                difficulty = "Beginner Friendly (Overpowered Meta)",
                threeStarPotential = "100% Guaranteed 3-Star Meta",
                winRatePercent = 99,
                summary = "The reigning meta king of TH16. Overgrowth spell freezes half the base, while 7x Root Riders + 10x Valkyries obliterate the remaining half with unstoppable speed.",
                whyItWorks = "Root Riders bypass all walls automatically, allowing high-DPS Valkyries to spin through merged defenses under Rage and Warden Healing Tome without getting stuck.",
                army = FullArmyComposition(
                    troops = listOf(
                        ArmyTroop("Root Rider", 7, 20, "🪵", "Wall Destroyer & Meat Shield"),
                        ArmyTroop("Valkyrie", 10, 8, "🪓", "High Burst Cleave Damage"),
                        ArmyTroop("Druid", 3, 16, "🌿", "Continuous Team Regen & Bear Tanks"),
                        ArmyTroop("Headhunter", 3, 6, "🎯", "Hero Assassination"),
                        ArmyTroop("Apprentice Warden", 1, 20, "🪄", "HP Aura Buffer"),
                        ArmyTroop("Super Wall Breaker", 1, 8, "🧨", "Side Funnel Guide"),
                        ArmyTroop("Minion", 4, 2, "🦇", "Trash Cleanup")
                    ),
                    spells = listOf(
                        ArmySpell("Overgrowth Spell", 2, 2, "🌳", "Lockdown Monolith / Spell Towers"),
                        ArmySpell("Rage Spell", 2, 2, "🔥", "Valkyrie Rampage Speed"),
                        ArmySpell("Freeze Spell", 2, 1, "❄️", "Town Hall 16 Giga Inferno"),
                        ArmySpell("Poison Spell", 1, 1, "🧪", "Enemy Clan Castle")
                    ),
                    clanCastle = ClanCastleComp("Siege Barracks", "1x Root Rider + 3x Super Valks", "1x Rage + 1x Freeze"),
                    heroEquipments = listOf(
                        HeroEquipmentConfig("Barbarian King", "Giant Gauntlet", "Spiky Ball", "Phoenix"),
                        HeroEquipmentConfig("Archer Queen", "Frozen Arrow", "Healer Puppet", "Unicorn"),
                        HeroEquipmentConfig("Grand Warden", "Eternal Tome", "Healing Tome", "Electro Owl"),
                        HeroEquipmentConfig("Royal Champion", "Haste Vial", "Rocket Spear", "Fox")
                    ),
                    cocArmyLink = "https://link.clashofclans.com/en?action=CopyArmy&army=u7x110-10x12-3x115-3x53-1x97-1x28-4x10s2x70-2x2-2x5-1x9"
                ),
                tacticalOverlay = BaseTacticalOverlay(
                    title = "TH16 Root Rider Overgrowth Blueprint",
                    summary = "Drop 1st Overgrowth on the core/backside defenses (Monolith & Ricochet Cannons). Send the entire Root Rider + Valk army through the open corridor.",
                    zones = listOf(
                        TacticalZone("overgrowth_zone", PointF(0.35f, 0.35f), 0.14f, MarkerType.SPELL_DROP, "1. Overgrowth Spell (Lock Monolith/Core)", 0xFF00E676, 1),
                        TacticalZone("siege_side", PointF(0.85f, 0.45f), 0.08f, MarkerType.SIEGE_DEPLOY, "2. Siege Barracks + King Cut", 0xFFFF9100, 2),
                        TacticalZone("root_entry", PointF(0.55f, 0.85f), 0.12f, MarkerType.MAIN_ENTRY, "3. Root Riders + Valks + Heroes Line", 0xFFFF3D00, 3),
                        TacticalZone("rage_surge", PointF(0.55f, 0.60f), 0.10f, MarkerType.SPELL_DROP, "4. Rage + Warden Healing Tome", 0xFFD500F9, 4),
                        TacticalZone("th16_freeze", PointF(0.50f, 0.30f), 0.09f, MarkerType.SPELL_DROP, "5. Freeze Town Hall 16 Giga", 0xFF00E5FF, 5)
                    ),
                    arrows = listOf(
                        TacticalArrow("root_push", PointF(0.55f, 0.85f), PointF(0.55f, 0.55f), PointF(0.50f, 0.25f), "Root Rider Wall Crushing Path", 0xFFFF3D00, 9f),
                        TacticalArrow("siege_path", PointF(0.85f, 0.45f), null, PointF(0.70f, 0.20f), "Siege Barracks P.E.K.K.A & King", 0xFFFF9100, 6f)
                    ),
                    entryAngleDegree = 180f
                ),
                steps = listOf(
                    AttackStepTiming(
                        stepNumber = 1,
                        timeFormatted = "00:00 - 00:15",
                        phaseName = "Phase 1: Overgrowth Spell Lockdown",
                        actionTitle = "Jatuhkan 1x Overgrowth Spell pada kluster Monolith / Ricochet Cannon",
                        detailedInstructions = "Kunci area berbahaya (misal Monolith dan Spell Tower) dengan Overgrowth Spell. Seluruh bangunan di dalam lingkaran hijau akan tertidur selama 28 detik sehingga pasukanmu fokus menghabisi sisa base.",
                        unitsToDeploy = emptyList(),
                        spellsToUse = listOf("1x Overgrowth Spell"),
                        tacticalTip = "Ini memangkas 50% ancaman base musuh seketika!"
                    ),
                    AttackStepTiming(
                        stepNumber = 2,
                        timeFormatted = "00:15 - 00:35",
                        phaseName = "Phase 2: Siege Barracks & King Funnel",
                        actionTitle = "Turunkan Siege Barracks & Barbarian King di sudut kanan (4 o'clock)",
                        detailedInstructions = "Lepaskan Siege Barracks dan Barbarian King untuk menyapu perimeter luar. Gunakan Spiky Ball King untuk meremukkan pertahanan terluar.",
                        unitsToDeploy = listOf("Siege Barracks", "Barbarian King"),
                        spellsToUse = emptyList(),
                        heroAbilities = listOf("Barbarian King Spiky Ball"),
                        tacticalTip = "Funnel ini mengarahkan Root Rider lurus masuk ke dalam koridor."
                    ),
                    AttackStepTiming(
                        stepNumber = 3,
                        timeFormatted = "00:35 - 01:10 (MAIN DEPLOY)",
                        phaseName = "Phase 3: Root Rider & Valkyrie Stampede",
                        actionTitle = "Lepaskan 7x Root Rider, 10x Valkyrie, 3x Druid, Queen, Warden, dan RC berjejer",
                        detailedInstructions = "Turunkan Root Riders membentuk garis, ikuti dengan Valkyrie dan 4 Hero lengkap. Saat melewati pertahanan pertama, letakkan 1 RAGE SPELL dan AKTIFKAN GRAND WARDEN ETERNAL TOME + HEALING TOME!",
                        unitsToDeploy = listOf("7x Root Rider", "10x Valkyrie", "3x Druid", "Archer Queen", "Grand Warden", "Royal Champion", "3x Headhunter"),
                        spellsToUse = listOf("1x Rage Spell", "1x Poison Spell pada CC musuh"),
                        heroAbilities = listOf("Grand Warden Eternal Tome & Healing Tome"),
                        tacticalTip = "Druid akan menyembuhkan dan memanggil beruang tank!"
                    ),
                    AttackStepTiming(
                        stepNumber = 4,
                        timeFormatted = "01:10 - 02:20",
                        phaseName = "Phase 4: Second Overgrowth & TH16 Freeze",
                        actionTitle = "Jatuhkan Overgrowth kedua di kompartemen sisa + Freeze Town Hall 16",
                        detailedInstructions = "Saat pasukan mencapai Town Hall 16, bekukan TH16 dengan Freeze Spell agar Giga Inferno tidak membakar Valkyrie. Aktifkan Haste Vial Royal Champion dan Invisibility Queen.",
                        unitsToDeploy = listOf("4x Minion Cleanup"),
                        spellsToUse = listOf("1x Overgrowth Spell", "2x Freeze Spell", "1x Rage Spell"),
                        heroAbilities = listOf("Royal Champion Haste Vial", "Archer Queen Invisibility"),
                        tacticalTip = "⭐️⭐️⭐️ 3-Star Rata Bersih 100%!"
                    )
                ),
                simulationSteps = listOf(
                    AttackSimulationStep(0, "3:00", "Start", "Overgrowth Spell locks down core", 0, 0, false, emptyList(), listOf(
                        SimulatedSpellEffect("Overgrowth", PointF(0.35f, 0.35f), 0.14f, 0x8800E676)
                    ), "Phase 1: Monolith & Spell Towers terkunci oleh Overgrowth!"),
                    AttackSimulationStep(20, "2:40", "Funnel", "Siege Barracks & King", 18, 0, false, listOf(
                        SimulatedTroopUnit("King", "🤴", PointF(0.85f, 0.45f), 1, isHero = true),
                        SimulatedTroopUnit("Siege Barracks", "🏰", PointF(0.85f, 0.45f), 1, isSiege = true)
                    ), emptyList(), "Phase 2: King & Siege Barracks memotong sisi kanan base."),
                    AttackSimulationStep(45, "2:15", "Stampede", "Root Riders & Valks surge with Warden Tome", 52, 1, false, listOf(
                        SimulatedTroopUnit("Root Riders", "🪵", PointF(0.55f, 0.65f), 7),
                        SimulatedTroopUnit("Valkyries", "🪓", PointF(0.55f, 0.60f), 10),
                        SimulatedTroopUnit("Heroes", "👑", PointF(0.55f, 0.70f), 4, isHero = true)
                    ), listOf(
                        SimulatedSpellEffect("Eternal Tome", PointF(0.55f, 0.65f), 0.12f, 0x88FFD700),
                        SimulatedSpellEffect("Rage", PointF(0.55f, 0.60f), 0.09f, 0x88D500F9)
                    ), "Phase 3: Tembok hancur lebur! Valkyrie mencabik-cabik pertahanan musuh!"),
                    AttackSimulationStep(80, "1:40", "TH16 Down", "Town Hall 16 destroyed", 82, 2, true, listOf(
                        SimulatedTroopUnit("Root Riders", "🪵", PointF(0.50f, 0.30f), 5),
                        SimulatedTroopUnit("Royal Champion", "🛡️", PointF(0.48f, 0.25f), 1, isHero = true)
                    ), listOf(
                        SimulatedSpellEffect("Freeze", PointF(0.50f, 0.30f), 0.09f, 0x8800E5FF)
                    ), "Phase 4: Town Hall 16 HANCUR! Overgrowth selesai, sisa base langsung diserbu!"),
                    AttackSimulationStep(130, "0:50", "VICTORY", "100% 3 Bintang Sempurna!", 100, 3, true, listOf(
                        SimulatedTroopUnit("Valkyries", "🪓", PointF(0.35f, 0.35f), 6)
                    ), emptyList(), "⭐️⭐️⭐️ VICTORY! Base TH16 Rata 100% 3-Star!")
                )
            ),

            // ==================== TOWN HALL 17 ====================
            AttackStrategy(
                id = "th17_fireball_root_titan",
                name = "Fireball Warden + Root Rider Titan Rush",
                thLevel = TownHallLevel.TH17,
                category = "Hero Smash",
                difficulty = "Pro Meta",
                threeStarPotential = "100% Guaranteed 3-Star Meta",
                winRatePercent = 99,
                summary = "Grand Warden Fireball equipment instantly nukes a 15-tile cluster of TH17 ultra-merged defenses. Root Riders & Electro Titans charge the remaining base.",
                whyItWorks = "Max Fireball destroys 4-5 major defenses in 1 second from across the map, creating an instant funnel and eliminating Town Hall 17 defense clusters effortlessly.",
                army = FullArmyComposition(
                    troops = listOf(
                        ArmyTroop("Root Rider", 8, 20, "🪵", "Frontline Crusher"),
                        ArmyTroop("Super Witch", 2, 44, "🔮", "Infinite Big Boy Tanks"),
                        ArmyTroop("Druid", 3, 16, "🌿", "Team Sustain"),
                        ArmyTroop("Electro Titan", 1, 32, "⚡", "Anti-Skeleton & CC Zap"),
                        ArmyTroop("Headhunter", 4, 6, "🎯", "Hero Snipers"),
                        ArmyTroop("Minion Prince", 4, 2, "👑", "Speed Cleanup")
                    ),
                    spells = listOf(
                        ArmySpell("Overgrowth Spell", 2, 2, "🌳", "Lockdown Inferno Compartment"),
                        ArmySpell("Rage Spell", 3, 2, "🔥", "Maximum Damage Output"),
                        ArmySpell("Freeze Spell", 2, 1, "❄️", "Freeze TH17 Giga Core"),
                        ArmySpell("Poison Spell", 1, 1, "🧪", "Enemy CC")
                    ),
                    clanCastle = ClanCastleComp("Battle Drill", "2x Root Riders + Super Minion", "1x Rage + 1x Overgrowth"),
                    heroEquipments = listOf(
                        HeroEquipmentConfig("Barbarian King", "Giant Gauntlet", "Spiky Ball", "Phoenix"),
                        HeroEquipmentConfig("Archer Queen", "Frozen Arrow", "Giant Arrow", "Unicorn"),
                        HeroEquipmentConfig("Grand Warden", "Fireball", "Healing Tome", "Electro Owl"),
                        HeroEquipmentConfig("Royal Champion", "Haste Vial", "Rocket Spear", "Fox")
                    ),
                    cocArmyLink = "https://link.clashofclans.com/en?action=CopyArmy&army=u8x110-2x83-3x115-1x95-4x53-4x10s2x70-3x2-2x5-1x9"
                ),
                tacticalOverlay = BaseTacticalOverlay(
                    title = "TH17 Fireball Root Titan Blueprint",
                    summary = "Warden Walk at 12 o'clock -> Trigger FIREBALL into the cluster of merged defenses. Drop Root Riders and Super Witches to sweep through the core.",
                    zones = listOf(
                        TacticalZone("warden_fireball_target", PointF(0.40f, 0.25f), 0.12f, MarkerType.DEFENSE_TARGET, "1. Fireball Nuke Target (Merged Defenses)", 0xFFFF3D00, 1),
                        TacticalZone("overgrowth_th17", PointF(0.70f, 0.45f), 0.13f, MarkerType.SPELL_DROP, "2. Overgrowth Side Complex", 0xFF00E676, 2),
                        TacticalZone("root_titan_entry", PointF(0.25f, 0.70f), 0.12f, MarkerType.MAIN_ENTRY, "3. Root Rider + Super Witch Surge", 0xFFFF9100, 3),
                        TacticalZone("rage_core_17", PointF(0.45f, 0.50f), 0.10f, MarkerType.SPELL_DROP, "4. Core Rage + King Spiky Ball", 0xFFD500F9, 4),
                        TacticalZone("th17_giga_freeze", PointF(0.50f, 0.35f), 0.09f, MarkerType.SPELL_DROP, "5. Freeze TH17 Giga Core", 0xFF00E5FF, 5)
                    ),
                    arrows = listOf(
                        TacticalArrow("fireball_shot", PointF(0.20f, 0.15f), null, PointF(0.40f, 0.25f), "Warden Fireball Trajectory", 0xFFFF3D00, 8f, isDashed = true),
                        TacticalArrow("root_push_17", PointF(0.25f, 0.70f), PointF(0.45f, 0.50f), PointF(0.60f, 0.25f), "Root Rider & Titan Core March", 0xFFFF9100, 9f)
                    ),
                    entryAngleDegree = 240f
                ),
                steps = listOf(
                    AttackStepTiming(
                        stepNumber = 1,
                        timeFormatted = "00:00 - 00:20",
                        phaseName = "Phase 1: Grand Warden Fireball Nuke",
                        actionTitle = "Turunkan Grand Warden + 4 Healer di 11 o'clock & Aktifkan FIREBALL!",
                        detailedInstructions = "Arahkan Grand Warden pada bangunan pancingan. Begitu Warden membidik, AKTIFKAN Fireball. Ledakan raksasa akan menghancurkan 4-5 pertahanan terberat TH17 dalam 1 detik!",
                        unitsToDeploy = listOf("Grand Warden", "4x Healer"),
                        spellsToUse = listOf("1x Rage Spell pada Warden (bila perlu)"),
                        heroAbilities = listOf("Grand Warden Fireball (NUKIR INTELIJEN)"),
                        tacticalTip = "Nuklir Fireball membuka jalan bebas hambatan!"
                    ),
                    AttackStepTiming(
                        stepNumber = 2,
                        timeFormatted = "00:20 - 00:40",
                        phaseName = "Phase 2: Overgrowth Side Defense",
                        actionTitle = "Jatuhkan Overgrowth Spell di sisi samping yang tidak tersentuh Fireball",
                        detailedInstructions = "Kunci sisa kluster Inferno dan Scattershot dengan Overgrowth agar pasukan kita tidak terpecah dan tetap maju satu arah.",
                        unitsToDeploy = emptyList(),
                        spellsToUse = listOf("1x Overgrowth Spell"),
                        tacticalTip = "Base TH17 menyempit menjadi jalur lurus yang mudah dikuasai."
                    ),
                    AttackStepTiming(
                        stepNumber = 3,
                        timeFormatted = "00:40 - 01:20 (MAIN WAVE)",
                        phaseName = "Phase 3: Root Rider & Super Witch Smash",
                        actionTitle = "Lepaskan 8x Root Rider, 2x Super Witch, Electro Titan, dan Hero lainnya",
                        detailedInstructions = "Luncurkan Root Riders bersama Big Boy Super Witch dan Electro Titan. Turunkan Barbarian King (Giant Gauntlet + Spiky Ball) dan Archer Queen di belakangnya.",
                        unitsToDeploy = listOf("8x Root Rider", "2x Super Witch", "1x Electro Titan", "Barbarian King", "Archer Queen", "Battle Drill"),
                        spellsToUse = listOf("1x Rage Spell", "1x Poison Spell pada CC lawan"),
                        heroAbilities = listOf("Barbarian King Giant Gauntlet & Spiky Ball"),
                        tacticalTip = "Big Boy Super Witch menjadi tameng abadi di depan Root Rider."
                    ),
                    AttackStepTiming(
                        stepNumber = 4,
                        timeFormatted = "01:20 - 02:30",
                        phaseName = "Phase 4: Freeze TH17 & Rocket Spear RC Finish",
                        actionTitle = "Bekukan Town Hall 17 + Aktifkan Rocket Spear Royal Champion",
                        detailedInstructions = "Gunakan Freeze Spell pada TH17 saat hampir meledak. Kirim Royal Champion dengan Rocket Spear dari jarak jauh untuk membabat sisa defense tanpa terkena damage sama sekali.",
                        unitsToDeploy = listOf("Royal Champion", "4x Minion Prince Cleanup"),
                        spellsToUse = listOf("2x Freeze Spell", "1x Rage Spell"),
                        heroAbilities = listOf("Royal Champion Rocket Spear & Haste Vial"),
                        tacticalTip = "⭐️⭐️⭐️ 100% 3-Star Rata Total di Level Tertinggi TH17!"
                    )
                ),
                simulationSteps = listOf(
                    AttackSimulationStep(0, "3:00", "Start", "Warden deploys & launches FIREBALL", 0, 0, false, listOf(
                        SimulatedTroopUnit("Grand Warden", "🧙‍♂️", PointF(0.20f, 0.15f), 1, isHero = true)
                    ), listOf(
                        SimulatedSpellEffect("FIREBALL NUKE", PointF(0.40f, 0.25f), 0.15f, 0x88FF3D00)
                    ), "Phase 1: FIREBALL meledak! 5 Pertahanan utama TH17 hancur seketika!"),
                    AttackSimulationStep(25, "2:35", "Overgrowth", "Side complex locked", 24, 0, false, emptyList(), listOf(
                        SimulatedSpellEffect("Overgrowth", PointF(0.70f, 0.45f), 0.13f, 0x8800E676)
                    ), "Phase 2: Sisi kanan base tertidur oleh Overgrowth."),
                    AttackSimulationStep(55, "2:05", "Smash Surge", "Root Riders + Super Witches + King Gauntlet", 58, 1, false, listOf(
                        SimulatedTroopUnit("Root Riders", "🪵", PointF(0.40f, 0.50f), 8),
                        SimulatedTroopUnit("Big Boys", "🔮", PointF(0.38f, 0.48f), 2),
                        SimulatedTroopUnit("King", "🤴", PointF(0.42f, 0.52f), 1, isHero = true)
                    ), listOf(
                        SimulatedSpellEffect("Rage", PointF(0.45f, 0.50f), 0.10f, 0x88D500F9)
                    ), "Phase 3: King Giant Gauntlet meratakan dinding dan pertahanan tengah!"),
                    AttackSimulationStep(90, "1:30", "TH17 Falls", "Town Hall 17 Frozen and Destroyed", 86, 2, true, listOf(
                        SimulatedTroopUnit("Royal Champion", "🛡️", PointF(0.55f, 0.30f), 1, isHero = true),
                        SimulatedTroopUnit("Root Riders", "🪵", PointF(0.50f, 0.35f), 6)
                    ), listOf(
                        SimulatedSpellEffect("Freeze", PointF(0.50f, 0.35f), 0.09f, 0x8800E5FF)
                    ), "Phase 4: Town Hall 17 hancur! Rocket Spear RC menembak dari kejauhan!"),
                    AttackSimulationStep(135, "0:45", "VICTORY", "100% 3 Bintang Terjamin di TH17!", 100, 3, true, listOf(
                        SimulatedTroopUnit("Electro Titan", "⚡", PointF(0.65f, 0.20f), 1)
                    ), emptyList(), "⭐️⭐️⭐️ VICTORY! 100% 3-Star TH17 Sempurna!")
                )
            ),

            // ==================== TOWN HALL 9 ====================
            AttackStrategy(
                id = "th9_witch_slap",
                name = "Zap Witch Slap (3-Star Classic)",
                thLevel = TownHallLevel.TH9,
                category = "Ground Swarm",
                difficulty = "Beginner Friendly",
                threeStarPotential = "100% Guaranteed 3-Star Meta",
                winRatePercent = 100,
                summary = "The easiest and most devastating 3-star attack for TH9. Witches walk down the flanks while Golems, Heroes, and Bowlers blast through the center.",
                whyItWorks = "TH9 has no multi-infernos or scattershots to stop witch skeletons. Skeletons overwhelm all point defenses effortlessly.",
                army = generateAutoArmy(TownHallLevel.TH9, "Witch"),
                tacticalOverlay = BaseTacticalOverlay(
                    title = "TH9 Witch Slap Blueprint",
                    summary = "Zap Air Defenses / X-Bows. Drop 5 Witches at 10 o'clock and 5 Witches at 2 o'clock. Golems and Heroes down 12 o'clock.",
                    zones = listOf(
                        TacticalZone("witch_flank_1", PointF(0.20f, 0.20f), 0.08f, MarkerType.FUNNEL_ZONE, "1. Left Flank: 5x Witches + 2x Healers", 0xFF00E676, 1),
                        TacticalZone("witch_flank_2", PointF(0.80f, 0.20f), 0.08f, MarkerType.FUNNEL_ZONE, "2. Right Flank: 5x Witches + 2x Healers", 0xFF00E676, 2),
                        TacticalZone("golem_entry", PointF(0.50f, 0.15f), 0.10f, MarkerType.MAIN_ENTRY, "3. Golems + King + Queen + CC Bowlers", 0xFFFF3D00, 3),
                        TacticalZone("jump_core", PointF(0.50f, 0.40f), 0.09f, MarkerType.SPELL_DROP, "4. Jump / Rage in Center", 0xFFD500F9, 4)
                    ),
                    arrows = listOf(
                        TacticalArrow("witch_left", PointF(0.20f, 0.20f), null, PointF(0.15f, 0.70f), "Left Witch Walk", 0xFF00E676, 6f),
                        TacticalArrow("witch_right", PointF(0.80f, 0.20f), null, PointF(0.85f, 0.70f), "Right Witch Walk", 0xFF00E676, 6f),
                        TacticalArrow("golem_core", PointF(0.50f, 0.15f), null, PointF(0.50f, 0.60f), "Golem & Hero Core Drive", 0xFFFF3D00, 8f)
                    ),
                    entryAngleDegree = 0f
                ),
                steps = listOf(
                    AttackStepTiming(
                        stepNumber = 1,
                        timeFormatted = "00:00 - 00:20",
                        phaseName = "Phase 1: Zap Key Defenses",
                        actionTitle = "Zap 2x Air Defenses / Mortir musuh",
                        detailedInstructions = "Gunakan Lightning + Earthquake Spell untuk menghancurkan pertahanan kunci.",
                        unitsToDeploy = emptyList(),
                        spellsToUse = listOf("8x Lightning Spell", "2x Earthquake"),
                        tacticalTip = "Hilangkan ancaman healer!"
                    ),
                    AttackStepTiming(
                        stepNumber = 2,
                        timeFormatted = "00:20 - 00:45",
                        phaseName = "Phase 2: Deploy Witch Flanks",
                        actionTitle = "Turunkan 5 Witch di sudut kiri & 5 Witch di sudut kanan",
                        detailedInstructions = "Lepaskan Witch bersama Healer untuk menyapu kedua sisi luar base.",
                        unitsToDeploy = listOf("10x Witch", "4x Healer"),
                        spellsToUse = emptyList(),
                        tacticalTip = "Kerangka akan menarik perhatian defense luar."
                    ),
                    AttackStepTiming(
                        stepNumber = 3,
                        timeFormatted = "00:45 - 01:45",
                        phaseName = "Phase 3: Core Golem & Bowler Charge",
                        actionTitle = "Turunkan 2 Golem + King + Queen + CC Bowlers di tengah",
                        detailedInstructions = "Buka dinding dengan Wall Breaker, biarkan Bowlers memantul menghancurkan core Town Hall 9.",
                        unitsToDeploy = listOf("2x Golem", "Barbarian King", "Archer Queen", "CC Bowlers"),
                        spellsToUse = listOf("1x Rage Spell", "1x Poison Spell"),
                        tacticalTip = "⭐️⭐️⭐️ 3-Star 100% Sempurna!"
                    )
                ),
                simulationSteps = listOf(
                    AttackSimulationStep(0, "3:00", "Start", "Zap Spells destroy key defenses", 10, 0, false, emptyList(), listOf(
                        SimulatedSpellEffect("Lightning", PointF(0.35f, 0.35f), 0.06f, 0x8800E5FF)
                    ), "Pertahanan kunci hancur oleh Lightning!"),
                    AttackSimulationStep(30, "2:30", "Witches Spread", "Skeletons flood both flanks", 40, 1, false, listOf(
                        SimulatedTroopUnit("Witches", "🧙‍♀️", PointF(0.20f, 0.30f), 5),
                        SimulatedTroopUnit("Witches", "🧙‍♀️", PointF(0.80f, 0.30f), 5)
                    ), emptyList(), "Witches membersihkan kedua sisi base!"),
                    AttackSimulationStep(70, "1:50", "Core Ripped", "Bowlers & King crush Town Hall", 80, 2, true, listOf(
                        SimulatedTroopUnit("Bowlers", "🎳", PointF(0.50f, 0.45f), 5),
                        SimulatedTroopUnit("King", "🤴", PointF(0.50f, 0.40f), 1, isHero = true)
                    ), emptyList(), "Town Hall 9 Hancur!"),
                    AttackSimulationStep(110, "1:10", "VICTORY", "100% 3-Star Destruction", 100, 3, true, listOf(
                        SimulatedTroopUnit("Witches", "🧙‍♀️", PointF(0.50f, 0.70f), 8)
                    ), emptyList(), "⭐️⭐️⭐️ 3 Bintang TH9 Sempurna!")
                )
            ),

            // ==================== TOWN HALL 10 ====================
            AttackStrategy(
                id = "th10_zap_witch",
                name = "Zap Witches + Log Launcher",
                thLevel = TownHallLevel.TH10,
                category = "Ground Swarm",
                difficulty = "Beginner Friendly",
                threeStarPotential = "100% Guaranteed 3-Star Meta",
                winRatePercent = 99,
                summary = "Zap both Single/Multi Infernos at the start, then send Golems, Witches, and Log Launcher straight through the middle for an automatic 3-star.",
                whyItWorks = "Without Inferno Towers, TH10 has zero splash counters to stop 12 Witches and 40+ Skeletons.",
                army = generateAutoArmy(TownHallLevel.TH10, "Witch"),
                tacticalOverlay = BaseTacticalOverlay(
                    title = "TH10 Zap Witch Blueprint",
                    summary = "Zap Inferno 1 & Inferno 2. Drop Golems on wide spread, followed by line of Witches and Log Launcher down the center.",
                    zones = listOf(
                        TacticalZone("zap_inf_1", PointF(0.35f, 0.40f), 0.08f, MarkerType.SPELL_DROP, "1. Zap 4x + 1x EQ on Inferno 1", 0xFF00E5FF, 1),
                        TacticalZone("zap_inf_2", PointF(0.65f, 0.40f), 0.08f, MarkerType.SPELL_DROP, "2. Zap 4x + 1x EQ on Inferno 2", 0xFF00E5FF, 2),
                        TacticalZone("witch_line", PointF(0.50f, 0.15f), 0.15f, MarkerType.MAIN_ENTRY, "3. Line of 12x Witches + 2x Golems", 0xFFFF3D00, 3),
                        TacticalZone("log_launcher", PointF(0.50f, 0.10f), 0.08f, MarkerType.SIEGE_DEPLOY, "4. Log Launcher straight to TH", 0xFFFF9100, 4)
                    ),
                    arrows = listOf(
                        TacticalArrow("log_path", PointF(0.50f, 0.10f), null, PointF(0.50f, 0.70f), "Log Launcher Wall Breaching", 0xFFFF9100, 8f),
                        TacticalArrow("witch_surge", PointF(0.50f, 0.15f), null, PointF(0.50f, 0.65f), "Main Witch Swarm", 0xFFFF3D00, 8f)
                    ),
                    entryAngleDegree = 0f
                ),
                steps = listOf(
                    AttackStepTiming(
                        stepNumber = 1,
                        timeFormatted = "00:00 - 00:15",
                        phaseName = "Phase 1: Zap Both Infernos",
                        actionTitle = "Jatuhkan 4x Lightning + 1x EQ di tiap Inferno Tower",
                        detailedInstructions = "Hancurkan kedua Inferno Tower musuh di awal serangan.",
                        unitsToDeploy = emptyList(),
                        spellsToUse = listOf("8x Lightning Spell", "2x Earthquake"),
                        tacticalTip = "Kedua Inferno musuh langsung lenyap!"
                    ),
                    AttackStepTiming(
                        stepNumber = 2,
                        timeFormatted = "00:15 - 00:45",
                        phaseName = "Phase 2: Deploy Golems & Witches Line",
                        actionTitle = "Sebar 2 Golem dan 12 Witch membentuk garis lurus",
                        detailedInstructions = "Lepaskan Golem di kiri & kanan, lalu sebar 12 Witch di sepanjang garis.",
                        unitsToDeploy = listOf("2x Golem", "12x Witch"),
                        spellsToUse = emptyList(),
                        tacticalTip = "Garis lurus memastikan seluruh sisi base tersapu rata."
                    ),
                    AttackStepTiming(
                        stepNumber = 3,
                        timeFormatted = "00:45 - 02:00",
                        phaseName = "Phase 3: Log Launcher & Heroes Core March",
                        actionTitle = "Kirim Log Launcher + King + Queen di tengah",
                        detailedInstructions = "Log Launcher akan membuka semua dinding sampai ke Town Hall 10.",
                        unitsToDeploy = listOf("Log Launcher", "Barbarian King", "Archer Queen"),
                        spellsToUse = listOf("1x Rage Spell", "1x Poison Spell"),
                        tacticalTip = "⭐️⭐️⭐️ 3-Star 100% Dijamin!"
                    )
                ),
                simulationSteps = listOf(
                    AttackSimulationStep(0, "3:00", "Start", "Lightning destroys both Infernos", 15, 0, false, emptyList(), listOf(
                        SimulatedSpellEffect("Zap 1", PointF(0.35f, 0.40f), 0.08f, 0x8800E5FF),
                        SimulatedSpellEffect("Zap 2", PointF(0.65f, 0.40f), 0.08f, 0x8800E5FF)
                    ), "Kedua Inferno Tower TH10 HANCUR!"),
                    AttackSimulationStep(35, "2:25", "Witch Stampede", "Log Launcher opens core", 50, 1, false, listOf(
                        SimulatedTroopUnit("Witches", "🧙‍♀️", PointF(0.50f, 0.35f), 12),
                        SimulatedTroopUnit("Log Launcher", "🪵", PointF(0.50f, 0.40f), 1, isSiege = true)
                    ), emptyList(), "Log Launcher membelah seluruh tembok base!"),
                    AttackSimulationStep(80, "1:40", "Town Hall Falls", "CC Bowlers under Rage", 85, 2, true, listOf(
                        SimulatedTroopUnit("King", "🤴", PointF(0.50f, 0.55f), 1, isHero = true),
                        SimulatedTroopUnit("Queen", "👸", PointF(0.50f, 0.50f), 1, isHero = true)
                    ), listOf(
                        SimulatedSpellEffect("Rage", PointF(0.50f, 0.55f), 0.09f, 0x88D500F9)
                    ), "Town Hall 10 Hancur!"),
                    AttackSimulationStep(120, "1:00", "VICTORY", "100% 3 Bintang Sempurna", 100, 3, true, listOf(
                        SimulatedTroopUnit("Witches", "🧙‍♀️", PointF(0.50f, 0.75f), 10)
                    ), emptyList(), "⭐️⭐️⭐️ VICTORY! 3-Star 100%!")
                )
            ),

            // ==================== TOWN HALL 11 ====================
            AttackStrategy(
                id = "th11_zap_witch_golem",
                name = "Zap Witch 5-Icy-G + Grand Warden",
                thLevel = TownHallLevel.TH11,
                category = "Ground Swarm",
                difficulty = "Beginner Friendly",
                threeStarPotential = "100% Guaranteed 3-Star Meta",
                winRatePercent = 98,
                summary = "Zap the Eagle Artillery or Multi-Infernos, then send Ice Golems, Witches, and Grand Warden with Eternal Tome for a completely safe 3-star.",
                whyItWorks = "Warden Eternal Tome negates giant bombs and Eagle Artillery blasts, allowing 14 witches and ice golems to freeze and overwhelm the base.",
                army = generateAutoArmy(TownHallLevel.TH11, "Hybrid"),
                tacticalOverlay = BaseTacticalOverlay(
                    title = "TH11 Zap 5-Icy-G Blueprint",
                    summary = "Zap Eagle Artillery. Deploy Ice Golems and Witches with Log Launcher and Grand Warden.",
                    zones = listOf(
                        TacticalZone("zap_eagle", PointF(0.50f, 0.45f), 0.09f, MarkerType.SPELL_DROP, "1. Zap Eagle Artillery", 0xFF00E5FF, 1),
                        TacticalZone("icy_line", PointF(0.50f, 0.15f), 0.14f, MarkerType.MAIN_ENTRY, "2. Ice Golems + Witches Line", 0xFFFF3D00, 2),
                        TacticalZone("warden_tome_11", PointF(0.50f, 0.35f), 0.10f, MarkerType.SPELL_DROP, "3. Grand Warden Eternal Tome", 0xFFFFD700, 3)
                    ),
                    arrows = listOf(
                        TacticalArrow("main_push_11", PointF(0.50f, 0.15f), null, PointF(0.50f, 0.70f), "Ice Golem & Witch Surge", 0xFFFF3D00, 8f)
                    ),
                    entryAngleDegree = 0f
                ),
                steps = listOf(
                    AttackStepTiming(
                        stepNumber = 1,
                        timeFormatted = "00:00 - 00:15",
                        phaseName = "Phase 1: Zap Eagle Artillery",
                        actionTitle = "Jatuhkan Lightning + Earthquake di Eagle Artillery",
                        detailedInstructions = "Hilangkan Eagle Artillery sebelum pasukan diturunkan.",
                        unitsToDeploy = emptyList(),
                        spellsToUse = listOf("Lightning Spells", "Earthquake"),
                        tacticalTip = "Eagle Artillery langsung hancur tanpa sempat menembak!"
                    ),
                    AttackStepTiming(
                        stepNumber = 2,
                        timeFormatted = "00:15 - 00:45",
                        phaseName = "Phase 2: Ice Golems & Witches Swarm",
                        actionTitle = "Sebar Ice Golems dan Witches di garis depan",
                        detailedInstructions = "Ice Golem akan membekukan defense saat mati, memberi jalan bagi Witches.",
                        unitsToDeploy = listOf("5x Ice Golem", "12x Witch", "Log Launcher"),
                        spellsToUse = emptyList(),
                        tacticalTip = "Gunakan Grand Warden di tengah barisan."
                    ),
                    AttackStepTiming(
                        stepNumber = 3,
                        timeFormatted = "00:45 - 02:00",
                        phaseName = "Phase 3: Warden Eternal Tome & 3-Star",
                        actionTitle = "Aktifkan Grand Warden Eternal Tome di kompartemen tengah",
                        detailedInstructions = "Seluruh pasukan kebal dari damage saat menembus Town Hall 11.",
                        unitsToDeploy = listOf("Barbarian King", "Archer Queen", "Grand Warden"),
                        spellsToUse = listOf("Freeze Spells", "Poison Spell"),
                        heroAbilities = listOf("Grand Warden Eternal Tome"),
                        tacticalTip = "⭐️⭐️⭐️ 3-Star 100% Terjamin!"
                    )
                ),
                simulationSteps = listOf(
                    AttackSimulationStep(0, "3:00", "Start", "Eagle Artillery destroyed by Zap", 15, 0, false, emptyList(), listOf(
                        SimulatedSpellEffect("Zap Eagle", PointF(0.50f, 0.45f), 0.09f, 0x8800E5FF)
                    ), "Eagle Artillery TH11 Hancur!"),
                    AttackSimulationStep(40, "2:20", "Ice Swarm", "Ice Golems freeze core", 55, 1, false, listOf(
                        SimulatedTroopUnit("Ice Golems", "🧊", PointF(0.50f, 0.40f), 4),
                        SimulatedTroopUnit("Witches", "🧙‍♀️", PointF(0.50f, 0.30f), 12)
                    ), listOf(
                        SimulatedSpellEffect("Eternal Tome", PointF(0.50f, 0.40f), 0.12f, 0x88FFD700)
                    ), "Grand Warden Eternal Tome melindungi seluruh pasukan!"),
                    AttackSimulationStep(90, "1:30", "VICTORY", "100% 3 Bintang", 100, 3, true, listOf(
                        SimulatedTroopUnit("Witches", "🧙‍♀️", PointF(0.50f, 0.70f), 10)
                    ), emptyList(), "⭐️⭐️⭐️ VICTORY! 3-Star TH11!")
                )
            ),

            // ==================== TOWN HALL 13 ====================
            AttackStrategy(
                id = "th13_qc_hybrid_rc",
                name = "Queen Charge Hybrid + Royal Champion Flank",
                thLevel = TownHallLevel.TH13,
                category = "Ground Hybrid",
                difficulty = "Intermediate",
                threeStarPotential = "100% Guaranteed 3-Star Meta",
                winRatePercent = 98,
                summary = "Queen Charge eliminates Town Hall 13 and Scattershot. Hybrid wave pushes core while Royal Champion with Diggy clears backend defenses.",
                whyItWorks = "Town Hall 13 Giga Inferno slow bomb is neutralized by Queen Charge or Warden Tome, while Royal Champion clears scattershots with ease.",
                army = generateAutoArmy(TownHallLevel.TH13, "Hybrid"),
                tacticalOverlay = BaseTacticalOverlay(
                    title = "TH13 QC Hybrid Blueprint",
                    summary = "Queen Charge into TH13 Giga Inferno. Deploy Siege Barracks & King on flank. Hybrid through core with Warden Tome.",
                    zones = listOf(
                        TacticalZone("qc_th13", PointF(0.48f, 0.15f), 0.08f, MarkerType.HERO_DEPLOY, "1. Queen Charge into TH13", 0xFF00E5FF, 1),
                        TacticalZone("rage_qc_13", PointF(0.50f, 0.28f), 0.09f, MarkerType.SPELL_DROP, "2. Rage on Queen vs TH13 Giga", 0xFFD500F9, 2),
                        TacticalZone("siege_king_13", PointF(0.85f, 0.40f), 0.08f, MarkerType.SIEGE_DEPLOY, "3. Siege Barracks + King", 0xFFFF9100, 3),
                        TacticalZone("hybrid_13", PointF(0.65f, 0.25f), 0.10f, MarkerType.MAIN_ENTRY, "4. Miners + Hogs + Warden", 0xFFFF3D00, 4),
                        TacticalZone("rc_flank_13", PointF(0.20f, 0.65f), 0.08f, MarkerType.HERO_DEPLOY, "5. Royal Champion Backend", 0xFF00E676, 5)
                    ),
                    arrows = listOf(
                        TacticalArrow("hybrid_push_13", PointF(0.65f, 0.25f), PointF(0.50f, 0.50f), PointF(0.35f, 0.75f), "Main Hybrid Core Surge", 0xFFFF3D00, 8f)
                    ),
                    entryAngleDegree = 45f
                ),
                steps = listOf(
                    AttackStepTiming(
                        stepNumber = 1,
                        timeFormatted = "00:00 - 00:35",
                        phaseName = "Phase 1: Queen Charge Town Hall 13",
                        actionTitle = "Turunkan Queen + 5 Healers menuju Town Hall 13",
                        detailedInstructions = "Gunakan Super Wall Breaker dan Rage Spell untuk menghancurkan TH13 dan Scattershot.",
                        unitsToDeploy = listOf("Archer Queen", "5x Healers", "Super Wall Breaker"),
                        spellsToUse = listOf("1x Rage Spell", "1x Freeze"),
                        tacticalTip = "Gunakan Freeze jika Giga Inferno membakar Queen!"
                    ),
                    AttackStepTiming(
                        stepNumber = 2,
                        timeFormatted = "00:35 - 01:00",
                        phaseName = "Phase 2: Funneling with Siege Barracks & King",
                        actionTitle = "Turunkan Siege Barracks dan King di sisi kanan",
                        detailedInstructions = "Kunci koridor agar pasukan Hybrid masuk ke Scattershot kedua.",
                        unitsToDeploy = listOf("Siege Barracks", "Barbarian King"),
                        spellsToUse = emptyList(),
                        tacticalTip = "Koridor siap untuk Hybrid."
                    ),
                    AttackStepTiming(
                        stepNumber = 3,
                        timeFormatted = "01:00 - 02:30",
                        phaseName = "Phase 3: Hybrid Surge & RC Cleanup",
                        actionTitle = "Lepaskan Miner + Hogs + Warden di tengah & RC di sisi berlawanan",
                        detailedInstructions = "Gunakan Warden Eternal Tome saat melewati Eagle Artillery dan Heal Spells di area bom.",
                        unitsToDeploy = listOf("18x Miner", "14x Hog Rider", "Grand Warden", "Royal Champion"),
                        spellsToUse = listOf("3x Heal Spell", "1x Freeze"),
                        heroAbilities = listOf("Grand Warden Eternal Tome", "Royal Champion Seeking Shield"),
                        tacticalTip = "⭐️⭐️⭐️ 3-Star 100% Terjamin!"
                    )
                ),
                simulationSteps = listOf(
                    AttackSimulationStep(0, "3:00", "Start", "Queen Charge approaches TH13", 10, 0, false, listOf(
                        SimulatedTroopUnit("Queen", "👸", PointF(0.48f, 0.15f), 1, isHero = true)
                    ), emptyList(), "Queen Charge dimulai!"),
                    AttackSimulationStep(35, "2:25", "TH13 Down", "Town Hall 13 destroyed", 35, 1, true, listOf(
                        SimulatedTroopUnit("Queen", "👸", PointF(0.50f, 0.28f), 1, isHero = true)
                    ), listOf(
                        SimulatedSpellEffect("Rage", PointF(0.50f, 0.28f), 0.09f, 0x88D500F9)
                    ), "Town Hall 13 & Scattershot HANCUR!"),
                    AttackSimulationStep(75, "1:45", "Hybrid Push", "Miners & Hogs with Warden Tome", 70, 2, true, listOf(
                        SimulatedTroopUnit("Miners", "⛏️", PointF(0.50f, 0.50f), 18),
                        SimulatedTroopUnit("Hogs", "🐗", PointF(0.52f, 0.52f), 14)
                    ), listOf(
                        SimulatedSpellEffect("Heal", PointF(0.50f, 0.50f), 0.09f, 0x8800E676)
                    ), "Hybrid meratakan core base!"),
                    AttackSimulationStep(130, "0:50", "VICTORY", "100% 3 Bintang Sempurna", 100, 3, true, listOf(
                        SimulatedTroopUnit("Royal Champion", "🛡️", PointF(0.35f, 0.75f), 1, isHero = true)
                    ), emptyList(), "⭐️⭐️⭐️ VICTORY! 3-Star TH13!")
                )
            ),

            // ==================== TOWN HALL 15 ====================
            AttackStrategy(
                id = "th15_sarch_blimp_root",
                name = "Super Archer Blimp + Root Rider Smash",
                thLevel = TownHallLevel.TH15,
                category = "Root Rider Meta",
                difficulty = "Intermediate",
                threeStarPotential = "100% Guaranteed 3-Star Meta",
                winRatePercent = 99,
                summary = "Super Archer Blimp in Clone + Invisibility clears 45% of the base including Town Hall 15 and Monolith. Root Riders sweep the remaining defenses effortlessly.",
                whyItWorks = "TH15 Spell Towers and Monolith are erased by Super Archer piercing arrows from 10 tiles away under Invisibility.",
                army = generateAutoArmy(TownHallLevel.TH15, "Root Rider"),
                tacticalOverlay = BaseTacticalOverlay(
                    title = "TH15 S-Arch Blimp Root Blueprint",
                    summary = "Lava Hound + Battle Blimp into core. Clone + Rage + Invisibility on Super Archers. Deploy Root Riders on the cleanup flank.",
                    zones = listOf(
                        TacticalZone("sarch_core", PointF(0.50f, 0.45f), 0.12f, MarkerType.SPELL_DROP, "1. Super Archer Drop (Clone x2 + Rage + Invis x5)", 0xFFD500F9, 1),
                        TacticalZone("root_flank_15", PointF(0.25f, 0.75f), 0.12f, MarkerType.MAIN_ENTRY, "2. Root Riders + Valks + Heroes", 0xFFFF3D00, 2)
                    ),
                    arrows = listOf(
                        TacticalArrow("blimp_flight_15", PointF(0.10f, 0.10f), null, PointF(0.50f, 0.45f), "Blimp Flight to Monolith Core", 0xFFFF9100, 8f, isDashed = true),
                        TacticalArrow("root_march_15", PointF(0.25f, 0.75f), null, PointF(0.65f, 0.40f), "Root Rider Cleanup March", 0xFFFF3D00, 8f)
                    ),
                    entryAngleDegree = 135f
                ),
                steps = listOf(
                    AttackStepTiming(
                        stepNumber = 1,
                        timeFormatted = "00:00 - 00:30",
                        phaseName = "Phase 1: Super Archer Blimp Bomb",
                        actionTitle = "Kirim Lava Hound + Battle Blimp ke Jantung Base (Monolith / TH15)",
                        detailedInstructions = "Buka Blimp di core, langsung jatuhkan 1 Rage, 2 Clone, dan 1 Invisibility. Lanjutkan Invisibility tiap 4 detik.",
                        unitsToDeploy = listOf("1x Lava Hound", "Battle Blimp (3x Super Archer)"),
                        spellsToUse = listOf("2x Clone Spell", "1x Rage Spell", "5x Invisibility Spell"),
                        tacticalTip = "Monolith, Spell Towers, dan TH15 hancur dalam 15 detik!"
                    ),
                    AttackStepTiming(
                        stepNumber = 2,
                        timeFormatted = "00:30 - 02:00",
                        phaseName = "Phase 2: Root Rider & Heroes Clean Sweep",
                        actionTitle = "Lepaskan Root Riders + Valkyries + 4 Heroes di sisi yang tersisa",
                        detailedInstructions = "Root Rider akan menghancurkan seluruh dinding yang tersisa dan membawa seluruh hero menuju 3 Bintang sempurna.",
                        unitsToDeploy = listOf("6x Root Rider", "8x Valkyrie", "Barbarian King", "Archer Queen", "Grand Warden", "Royal Champion"),
                        spellsToUse = listOf("2x Freeze Spell", "1x Poison Spell"),
                        heroAbilities = listOf("Grand Warden Eternal Tome", "Barbarian King Giant Gauntlet"),
                        tacticalTip = "⭐️⭐️⭐️ 3-Star 100% Terjamin!"
                    )
                ),
                simulationSteps = listOf(
                    AttackSimulationStep(0, "3:00", "Start", "Blimp arrives at TH15 Core", 0, 0, false, listOf(
                        SimulatedTroopUnit("Blimp", "🎈", PointF(0.50f, 0.45f), 1, isSiege = true)
                    ), emptyList(), "Battle Blimp mendarat di core TH15!"),
                    AttackSimulationStep(20, "2:40", "Core Obliterated", "Super Archers clone & annihilate core", 45, 1, true, listOf(
                        SimulatedTroopUnit("Super Archers", "🏹", PointF(0.50f, 0.45f), 9)
                    ), listOf(
                        SimulatedSpellEffect("Clone", PointF(0.50f, 0.45f), 0.10f, 0x8800E5FF),
                        SimulatedSpellEffect("Invis", PointF(0.50f, 0.45f), 0.08f, 0x8800E5FF)
                    ), "Monolith, Spell Towers, & Town Hall 15 HANCUR!"),
                    AttackSimulationStep(60, "2:00", "Root Rider Sweep", "Root Riders crush outer defenses", 78, 2, true, listOf(
                        SimulatedTroopUnit("Root Riders", "🪵", PointF(0.40f, 0.65f), 6)
                    ), emptyList(), "Root Riders melibas sisa pertahanan!"),
                    AttackSimulationStep(110, "1:10", "VICTORY", "100% 3 Bintang Sempurna", 100, 3, true, listOf(
                        SimulatedTroopUnit("Heroes", "👑", PointF(0.65f, 0.40f), 4, isHero = true)
                    ), emptyList(), "⭐️⭐️⭐️ VICTORY! 3-Star TH15!")
                )
            )
        )
    }
}
