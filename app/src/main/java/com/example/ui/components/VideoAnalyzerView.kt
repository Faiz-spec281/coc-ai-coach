package com.example.ui.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.TownHallLevel
import com.example.model.VideoAnalysisResult
import com.example.ui.theme.*

@Composable
fun VideoAnalyzerView(
    selectedTh: TownHallLevel,
    isAnalyzing: Boolean,
    analysisResult: VideoAnalysisResult?,
    onAnalyzeVideo: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var videoNameInput by remember { mutableStateOf("Replay_Serangan_Bintang3_TH${selectedTh.level}.mp4") }

    val videoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            val fileName = uri.lastPathSegment ?: "Replay_Upload_CoC.mp4"
            videoNameInput = fileName
            onAnalyzeVideo(fileName)
        }
    }

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
                        Icon(Icons.Default.VideoCameraBack, contentDescription = null, tint = GeoPrimary, modifier = Modifier.size(22.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "AI ANALISIS REPLAY VIDEO SERANGAN",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Black,
                            color = GeoPrimary,
                            letterSpacing = 0.5.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Upload video rekaman serangan Clash of Clans kamu. AI Coach akan membaca taktik, timing spell, pola funneling, dan mengekstrak strateginya agar kamu bisa menggunakannya di base lain!",
                        fontSize = 12.sp,
                        color = GeoTextSecondary,
                        lineHeight = 17.sp
                    )
                }
            }
        }

        // Upload Video Action Card
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = GeoSurface),
                shape = RoundedCornerShape(24.dp),
                border = androidx.compose.foundation.BorderStroke(1.5.dp, GeoBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(108.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(GeoSurfaceContainer)
                            .border(1.5.dp, GeoBorderSubtle, RoundedCornerShape(16.dp))
                            .clickable { videoPickerLauncher.launch("video/*") }
                            .padding(14.dp)
                            .testTag("upload_video_dropzone"),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(Icons.Default.CloudUpload, contentDescription = null, tint = GeoPrimary, modifier = Modifier.size(30.dp))
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "PILIH / UPLOAD FILE VIDEO REPLAY MP4",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Black,
                                color = GeoTextPrimary,
                                letterSpacing = 0.5.sp
                            )
                            Text(
                                text = "Klik di sini untuk memilih rekaman layar dari galeri HP",
                                fontSize = 11.sp,
                                color = GeoTextMuted
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Analyze Replay Button
                    Button(
                        onClick = { onAnalyzeVideo(videoNameInput) },
                        enabled = !isAnalyzing,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GeoPrimary,
                            contentColor = GeoOnPrimary
                        ),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier.fillMaxWidth().testTag("analyze_video_button")
                    ) {
                        if (isAnalyzing) {
                            CircularProgressIndicator(color = GeoOnPrimary, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("AI Membaca Rekaman Video...", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = GeoOnPrimary)
                        } else {
                            Icon(Icons.Default.Psychology, contentDescription = null, tint = GeoOnPrimary, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Mulai Analisis Video dengan AI Coach", fontSize = 12.sp, fontWeight = FontWeight.Black, color = GeoOnPrimary)
                        }
                    }
                }
            }
        }

        // Analysis Results
        if (analysisResult != null) {
            item {
                Card(
                    colors = CardDefaults.cardColors(containerColor = GeoSurface),
                    shape = RoundedCornerShape(24.dp),
                    border = androidx.compose.foundation.BorderStroke(1.5.dp, GeoGreenContainer),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "🎯 HASIL EKSTRAKSI TAKTIK AI",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Black,
                                color = GeoGreen,
                                letterSpacing = 0.5.sp
                            )
                            Surface(
                                color = GeoGreenContainer,
                                shape = RoundedCornerShape(8.dp),
                                border = androidx.compose.foundation.BorderStroke(1.dp, GeoGreen)
                            ) {
                                Text(
                                    text = analysisResult.attackDuration,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Black,
                                    color = GeoGreen,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Strategi Terdeteksi: ${analysisResult.detectedStrategy}",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Black,
                            color = GeoPrimaryLight
                        )
                        Text(
                            text = "Pasukan & Siege: ${analysisResult.armyUsed}",
                            fontSize = 12.sp,
                            color = GeoTextSecondary
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Key Technique
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(GeoSurfaceContainer)
                                .border(1.dp, GeoPrimary, RoundedCornerShape(12.dp))
                                .padding(12.dp)
                        ) {
                            Column {
                                Text(
                                    text = "💡 TEKNIK UTAMA DARI VIDEO:",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Black,
                                    color = GeoPrimary
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = analysisResult.keyTechniqueLearned,
                                    fontSize = 12.sp,
                                    color = GeoTextPrimary
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Funnel Cadence Timing
                        Text(
                            text = "⏱️ Timing Funneling: ${analysisResult.funnelCadenceTiming}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = GeoAmber
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Rating Efisiensi Spell: ${analysisResult.spellEfficiencyRating}",
                            fontSize = 12.sp,
                            color = GeoGreen
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // How to Apply to Other Bases
                        Text(
                            text = "📋 CARA MENERAPKAN STRATEGI INI KE BASE LAIN:",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Black,
                            color = GeoPrimary,
                            letterSpacing = 0.5.sp
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        analysisResult.howToApplyToOtherBases.forEach { tip ->
                            Text(
                                text = "• $tip",
                                fontSize = 12.sp,
                                color = GeoTextSecondary,
                                lineHeight = 17.sp,
                                modifier = Modifier.padding(vertical = 2.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Mistake to avoid
                        Surface(
                            color = GeoRedContainer,
                            shape = RoundedCornerShape(10.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, GeoRed),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text(
                                    text = "⚠️ KESALAHAN YANG HARUS DIHINDARI:",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Black,
                                    color = GeoRed
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = analysisResult.mistakeToAvoid,
                                    fontSize = 11.sp,
                                    color = GeoTextPrimary
                                )
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

