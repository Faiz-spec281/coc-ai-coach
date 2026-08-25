package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.AttackCoachViewModel
import com.example.ui.CoachTab
import com.example.ui.components.*
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    private val viewModel: AttackCoachViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                AttackCoachApp(viewModel = viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttackCoachApp(viewModel: AttackCoachViewModel) {
    val context = LocalContext.current

    val selectedTh by viewModel.selectedTh.collectAsState()
    val activeTab by viewModel.activeTab.collectAsState()
    val activeBaseBitmap by viewModel.activeBaseBitmap.collectAsState()
    val activeBaseDrawableRes by viewModel.activeBaseDrawableRes.collectAsState()
    val isAnalyzing by viewModel.isAnalyzing.collectAsState()
    val analysisResult by viewModel.analysisResult.collectAsState()
    val availableStrategies by viewModel.availableStrategies.collectAsState()
    val selectedStrategy by viewModel.selectedStrategy.collectAsState()

    val showZones by viewModel.showZones.collectAsState()
    val showArrows by viewModel.showArrows.collectAsState()
    val showSteps by viewModel.showSteps.collectAsState()

    val simulationSecond by viewModel.simulationSecond.collectAsState()
    val isSimPlaying by viewModel.isSimPlaying.collectAsState()
    val simSpeedMultiplier by viewModel.simSpeedMultiplier.collectAsState()

    val selectedCoreTroop by viewModel.selectedCoreTroop.collectAsState()
    val autoArmyComposition by viewModel.autoArmyComposition.collectAsState()

    val isVideoAnalyzing by viewModel.isVideoAnalyzing.collectAsState()
    val videoAnalysisResult by viewModel.videoAnalysisResult.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(38.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(com.example.ui.theme.GeoPrimary),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "AI",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Black,
                                color = com.example.ui.theme.GeoOnPrimary
                            )
                        }
                        Column {
                            Text(
                                text = "COC ATTACK COACH",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = com.example.ui.theme.GeoPrimary,
                                letterSpacing = 1.5.sp
                            )
                            Text(
                                text = "Geometric Analysis",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Black,
                                color = com.example.ui.theme.GeoTextPrimary
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = com.example.ui.theme.GeoSurface
                ),
                actions = {
                    Surface(
                        color = com.example.ui.theme.GeoBorder,
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.padding(end = 12.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = "TH ${selectedTh.level}",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Black,
                                color = Color.White
                            )
                            Box(
                                modifier = Modifier
                                    .size(7.dp)
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(com.example.ui.theme.GeoGreen)
                            )
                        }
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = com.example.ui.theme.GeoSurface,
                tonalElevation = 0.dp,
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = com.example.ui.theme.GeoBorder,
                        shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp)
                    )
                    .testTag("main_navigation_bar")
            ) {
                CoachTab.entries.forEach { tab ->
                    val isSelected = activeTab == tab
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = { viewModel.setTab(tab) },
                        icon = {
                            Text(
                                text = tab.iconEmoji,
                                fontSize = if (isSelected) 18.sp else 16.sp
                            )
                        },
                        label = {
                            Text(
                                text = when (tab) {
                                    CoachTab.TACTICAL_OVERLAY -> "Peta Overlay"
                                    CoachTab.STRATEGY_DETAILS -> "Waktu Pasukan"
                                    CoachTab.ATTACK_SIMULATOR -> "Simulasi B3"
                                    CoachTab.AUTO_ARMY -> "Auto Army"
                                    CoachTab.VIDEO_REPLAY_AI -> "AI Video"
                                },
                                fontSize = 10.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                color = if (isSelected) com.example.ui.theme.GeoPrimary else com.example.ui.theme.GeoTextMuted
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = com.example.ui.theme.GeoOnPrimary,
                            indicatorColor = com.example.ui.theme.GeoPrimaryContainer,
                            unselectedIconColor = com.example.ui.theme.GeoTextMuted
                        ),
                        modifier = Modifier.testTag("nav_tab_${tab.name}")
                    )
                }
            }
        },
        containerColor = com.example.ui.theme.GeoDarkBg
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Town Hall Selector pinned at top for instant switching TH9 - TH17
            TownHallSelector(
                selectedTh = selectedTh,
                onSelectTh = { viewModel.selectTownHall(it) }
            )

            // Screen Content Switcher
            Box(modifier = Modifier.fillMaxSize()) {
                when (activeTab) {
                    CoachTab.TACTICAL_OVERLAY -> {
                        TacticalOverlayDashboardView(
                            selectedTh = selectedTh,
                            baseBitmap = activeBaseBitmap,
                            baseDrawableRes = activeBaseDrawableRes,
                            strategy = selectedStrategy,
                            analysisResult = analysisResult,
                            isAnalyzing = isAnalyzing,
                            showZones = showZones,
                            showArrows = showArrows,
                            showSteps = showSteps,
                            onToggleZones = { viewModel.toggleZones() },
                            onToggleArrows = { viewModel.toggleArrows() },
                            onToggleSteps = { viewModel.toggleSteps() },
                            onImageSelected = { viewModel.loadCustomBaseBitmap(it) },
                            onNavigateToTimeline = { viewModel.setTab(CoachTab.STRATEGY_DETAILS) },
                            onNavigateToSimulator = { viewModel.setTab(CoachTab.ATTACK_SIMULATOR) }
                        )
                    }

                    CoachTab.STRATEGY_DETAILS -> {
                        StepTimelineView(
                            strategy = selectedStrategy,
                            allStrategies = availableStrategies,
                            onSelectStrategy = { viewModel.selectStrategy(it) },
                            onStartSimulation = { viewModel.setTab(CoachTab.ATTACK_SIMULATOR) },
                            onCopyArmyLink = { ctx, link -> viewModel.copyArmyLinkToClipboard(ctx, link) }
                        )
                    }

                    CoachTab.ATTACK_SIMULATOR -> {
                        AttackSimulatorView(
                            strategy = selectedStrategy,
                            simulationSecond = simulationSecond,
                            isPlaying = isSimPlaying,
                            speedMultiplier = simSpeedMultiplier,
                            baseBitmap = activeBaseBitmap,
                            baseDrawableRes = activeBaseDrawableRes,
                            onTogglePlay = { viewModel.togglePlaySimulation() },
                            onReset = { viewModel.resetSimulation() },
                            onSeekSecond = { viewModel.setSimulationSecond(it) },
                            onSetSpeed = { viewModel.setSpeedMultiplier(it) }
                        )
                    }

                    CoachTab.AUTO_ARMY -> {
                        AutoArmyBuilderView(
                            selectedTh = selectedTh,
                            selectedCoreTroop = selectedCoreTroop,
                            autoArmy = autoArmyComposition,
                            onSelectCoreTroop = { viewModel.setCoreTroopPreference(it) },
                            onCopyArmyLink = { ctx, link -> viewModel.copyArmyLinkToClipboard(ctx, link) }
                        )
                    }

                    CoachTab.VIDEO_REPLAY_AI -> {
                        VideoAnalyzerView(
                            selectedTh = selectedTh,
                            isAnalyzing = isVideoAnalyzing,
                            analysisResult = videoAnalysisResult,
                            onAnalyzeVideo = { viewModel.analyzeVideoInput(it) }
                        )
                    }
                }
            }
        }
    }
}
