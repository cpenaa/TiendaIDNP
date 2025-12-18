package com.example.tiendaidnp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.tiendaidnp.services.TimerService
import com.example.tiendaidnp.ui.components.buttons.PrimaryButton
import com.example.tiendaidnp.ui.navigation.AppNavigation
import com.example.tiendaidnp.ui.theme.TuAppTheme
import com.example.tiendaidnp.ui.viewmodel.ThemeViewModel
import com.example.tiendaidnp.workers.OfferWorker
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {

    private lateinit var themeViewModel: ThemeViewModel

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Log.d(
                "Permiso concedido",
                "Se habilitó el permiso para notificaciones"
            )
        } else {
            Log.d(
                "Permiso denegado",
                "No se habilitó el permiso para notificaciones"
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupPeriodicWork()
        askNotificationPermission()
        themeViewModel = ThemeViewModel(this)

        setContent {
            val themeMode by themeViewModel.themeMode.collectAsState()
            TuAppTheme(themeMode = themeMode) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        ControlPanelTimer()
                        AppNavigation()
                    }
                }
            }
        }
    }
    @androidx.compose.runtime.Composable
    fun ControlPanelTimer() {
        val isRunning by TimerService.isServiceRunning.collectAsState()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            PrimaryButton(
                text = "Iniciar Temporizador",
                enabled = !isRunning,
                onClick = {
                    Intent(
                        applicationContext,
                        TimerService::class.java
                    ).also { intent ->
                        intent.action = "START"
                        ContextCompat.startForegroundService(applicationContext, intent)
                    }
                }
            )

            PrimaryButton(
                text = "Detener",
                enabled = isRunning,
                onClick = {
                    Intent(
                        applicationContext,
                        TimerService::class.java
                    ).also { intent ->
                        intent.action = "STOP"
                        startService(intent)
                    }
                }
            )
        }
    }

    private fun setupPeriodicWork() {
        val workRequest = PeriodicWorkRequestBuilder<OfferWorker>(
            15,
            TimeUnit.MINUTES
        ).build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "CheckOffersWork",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }

    private fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }
}