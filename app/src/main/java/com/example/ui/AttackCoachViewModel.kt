package com.example.ui

import android.app.Application
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.R
import com.example.data.GeminiAiCoach
import com.example.data.MetaStrategyRepository
import com.example.model.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

enum class CoachTab(val title: String, val iconEmoji: String) {
    TACTICAL_OVERLAY("Peta Taktis & Overlay", "🗺️"),
    STRATEGY_DETAILS("Langkah & Waktu Serang", "⏱️"),
    ATTACK_SIMULATOR("Simulasi 3-Star", "🎮"),
    AUTO_ARMY("Auto Buat Army", "⚡"),
    VIDEO_REPLAY_AI("AI Analisis Video", "📹")
}

class AttackCoachViewModel(application: Application) : AndroidViewModel(application) {

    private val _selectedTh = MutableStateFlow(TownHallLevel.TH12)
    val selectedTh: StateFlow<TownHallLevel> = _selectedTh.asStateFlow()

    private val _activeTab = MutableStateFlow(CoachTab.TACTICAL_OVERLAY)
    val activeTab: StateFlow<CoachTab> = _activeTab.asStateFlow()

    private val _activeBaseBitmap = MutableStateFlow<Bitmap?>(null)
    val activeBaseBitmap: StateFlow<Bitmap?> = _activeBaseBitmap.asStateFlow()

    private val _activeBaseDrawableRes = MutableStateFlow<Int>(R.drawable.sample_th12_base)
    val activeBaseDrawableRes: StateFlow<Int> = _activeBaseDrawableRes.asStateFlow()

    private val _isAnalyzing = MutableStateFlow(false)
    val isAnalyzing: StateFlow<Boolean> = _isAnalyzing.asStateFlow()

    private val _analysisResult = MutableStateFlow<BaseAnalysisResult>(
        GeminiAiCoach.generateExpertHeuristicAnalysis(TownHallLevel.TH12)
    )
    val analysisResult: StateFlow<BaseAnalysisResult> = _analysisResult.asStateFlow()

    private val _availableStrategies = MutableStateFlow<List<AttackStrategy>>(
        MetaStrategyRepository.getStrategiesForTH(TownHallLevel.TH12)
    )
    val availableStrategies: StateFlow<List<AttackStrategy>> = _availableStrategies.asStateFlow()

    private val _selectedStrategy = MutableStateFlow<AttackStrategy>(
        MetaStrategyRepository.getStrategiesForTH(TownHallLevel.TH12).first()
    )
    val selectedStrategy: StateFlow<AttackStrategy> = _selectedStrategy.asStateFlow()

    // Overlay Toggles
    private val _showZones = MutableStateFlow(true)
    val showZones: StateFlow<Boolean> = _showZones.asStateFlow()

    private val _showArrows = MutableStateFlow(true)
    val showArrows: StateFlow<Boolean> = _showArrows.asStateFlow()

    private val _showSteps = MutableStateFlow(true)
    val showSteps: StateFlow<Boolean> = _showSteps.asStateFlow()

    // Interactive Simulator State
    private val _simulationSecond = MutableStateFlow(0)
    val simulationSecond: StateFlow<Int> = _simulationSecond.asStateFlow()

    private val _isSimPlaying = MutableStateFlow(false)
    val isSimPlaying: StateFlow<Boolean> = _isSimPlaying.asStateFlow()

    private val _simSpeedMultiplier = MutableStateFlow(1f) // 1x, 2x, 4x
    val simSpeedMultiplier: StateFlow<Float> = _simSpeedMultiplier.asStateFlow()

    private var simJob: Job? = null

    // Auto Army Builder State
    private val _selectedCoreTroop = MutableStateFlow("Root Rider")
    val selectedCoreTroop: StateFlow<String> = _selectedCoreTroop.asStateFlow()

    private val _autoArmyComposition = MutableStateFlow(
        MetaStrategyRepository.generateAutoArmy(TownHallLevel.TH12, "Miner")
    )
    val autoArmyComposition: StateFlow<FullArmyComposition> = _autoArmyComposition.asStateFlow()

    // Video Replay AI State
    private val _isVideoAnalyzing = MutableStateFlow(false)
    val isVideoAnalyzing: StateFlow<Boolean> = _isVideoAnalyzing.asStateFlow()

    private val _videoAnalysisResult = MutableStateFlow<VideoAnalysisResult?>(null)
    val videoAnalysisResult: StateFlow<VideoAnalysisResult?> = _videoAnalysisResult.asStateFlow()

    init {
        updateForTownHall(TownHallLevel.TH12)
    }

    fun selectTownHall(th: TownHallLevel) {
        _selectedTh.value = th
        updateForTownHall(th)
    }

    private fun updateForTownHall(th: TownHallLevel) {
        val defaultDrawable = when (th) {
            TownHallLevel.TH14, TownHallLevel.TH13 -> R.drawable.sample_th14_base
            TownHallLevel.TH16, TownHallLevel.TH15, TownHallLevel.TH17 -> R.drawable.sample_th16_base
            else -> R.drawable.sample_th12_base
        }
        if (_activeBaseBitmap.value == null) {
            _activeBaseDrawableRes.value = defaultDrawable
        }

        val strats = MetaStrategyRepository.getStrategiesForTH(th)
        _availableStrategies.value = strats
        val best = strats.firstOrNull() ?: MetaStrategyRepository.getStrategyById("th12_qc_hybrid")
        _selectedStrategy.value = best
        _analysisResult.value = GeminiAiCoach.generateExpertHeuristicAnalysis(th)
        _autoArmyComposition.value = MetaStrategyRepository.generateAutoArmy(th, _selectedCoreTroop.value)
        resetSimulation()
    }

    fun selectStrategy(strategy: AttackStrategy) {
        _selectedStrategy.value = strategy
        resetSimulation()
    }

    fun setTab(tab: CoachTab) {
        _activeTab.value = tab
    }

    fun toggleZones() { _showZones.value = !_showZones.value }
    fun toggleArrows() { _showArrows.value = !_showArrows.value }
    fun toggleSteps() { _showSteps.value = !_showSteps.value }

    fun loadCustomBaseBitmap(bitmap: Bitmap) {
        _activeBaseBitmap.value = bitmap
        analyzeCurrentBase()
    }

    fun analyzeCurrentBase() {
        viewModelScope.launch {
            _isAnalyzing.value = true
            try {
                val result = GeminiAiCoach.analyzeBaseScreenshot(_activeBaseBitmap.value, _selectedTh.value)
                _analysisResult.value = result
                val strat = MetaStrategyRepository.getStrategyById(result.bestStrategyId)
                _selectedStrategy.value = strat
            } finally {
                _isAnalyzing.value = false
            }
        }
    }

    fun copyArmyLinkToClipboard(context: Context, link: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Clash of Clans Army Link", link)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(context, "✅ Link Pasukan Tersalin! Buka Clash of Clans untuk paste.", Toast.LENGTH_SHORT).show()

        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link)).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            // In case browser/app is not installed
        }
    }

    // Interactive Simulator Controls
    fun togglePlaySimulation() {
        if (_isSimPlaying.value) {
            pauseSimulation()
        } else {
            playSimulation()
        }
    }

    fun playSimulation() {
        _isSimPlaying.value = true
        simJob?.cancel()
        simJob = viewModelScope.launch {
            while (_simulationSecond.value < 150 && _isSimPlaying.value) {
                val delayTime = (400 / _simSpeedMultiplier.value).toLong()
                delay(delayTime)
                _simulationSecond.value += 5
                if (_simulationSecond.value >= 150) {
                    _isSimPlaying.value = false
                    break
                }
            }
        }
    }

    fun pauseSimulation() {
        _isSimPlaying.value = false
        simJob?.cancel()
    }

    fun setSimulationSecond(sec: Int) {
        _simulationSecond.value = sec.coerceIn(0, 150)
    }

    fun resetSimulation() {
        pauseSimulation()
        _simulationSecond.value = 0
    }

    fun setSpeedMultiplier(multiplier: Float) {
        _simSpeedMultiplier.value = multiplier
        if (_isSimPlaying.value) {
            playSimulation()
        }
    }

    // Auto Army Generator
    fun setCoreTroopPreference(troop: String) {
        _selectedCoreTroop.value = troop
        _autoArmyComposition.value = MetaStrategyRepository.generateAutoArmy(_selectedTh.value, troop)
    }

    // Video Replay AI
    fun analyzeVideoInput(videoName: String = "Serangan_CWL_Bintang3.mp4") {
        viewModelScope.launch {
            _isVideoAnalyzing.value = true
            try {
                delay(1200) // Realistic AI extraction cadence
                val result = GeminiAiCoach.analyzeVideoReplay(videoName, _selectedTh.value)
                _videoAnalysisResult.value = result
            } finally {
                _isVideoAnalyzing.value = false
            }
        }
    }
}
